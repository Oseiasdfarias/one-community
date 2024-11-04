package br.com.one_community.controllers;


import br.com.one_community.entities.ValidationException;
import br.com.one_community.entities.answer.Answer;
import br.com.one_community.entities.answer.AnswerDetailsDto;
import br.com.one_community.entities.answer.AnswerDto;
import br.com.one_community.entities.answer.DataAnswerUpdateDto;
import br.com.one_community.entities.user.User;
import br.com.one_community.repositories.AnswerRepository;
import br.com.one_community.repositories.QuestionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping()
    @Transactional
    public ResponseEntity<AnswerDetailsDto> PostAnswer(
            @RequestBody @Valid AnswerDto data,
            UriComponentsBuilder uriBuilder){

        User user = getauthenticatedUser();

        if (!questionRepository.existsById(data.questionId())) {
            throw new ValidationException
                    ("Id da pergunta informado não existe!");
        }
        var question = questionRepository
                .findById(data.questionId())
                .get();

        var answer = new Answer(data, user, question);
        answerRepository.save(answer);

        var uri = uriBuilder
                .path("/answers/{id}")
                .buildAndExpand(answer.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new AnswerDetailsDto(answer));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<AnswerDetailsDto> updateAnswer(
            @RequestBody @Valid DataAnswerUpdateDto data){

        User user = getauthenticatedUser();

        if (!answerRepository.existsById(data.answerId())) {
            throw new ValidationException("ID da resposta não existe!");
        }
        var answer = answerRepository.getReferenceById(data.answerId());

        if (!user.getId().equals(answer.getUser().getId())) {
            throw new ValidationException("Resposta não pertence a esse usuário!");
        }
        answer.updateInfos(data);

        return ResponseEntity.ok(new AnswerDetailsDto(answer));
    }

    @GetMapping("list-answers-question/{question_id}")
    public ResponseEntity<Page<AnswerDetailsDto>> listAnswersQuestion(
            @PageableDefault(page = 0, size = 5, sort = {"body"})
            Pageable pageable, @PathVariable("question_id") Long questionId) {

        if (!questionRepository.existsById(questionId)) {
            throw new ValidationException
                    ("Id da pergunta informado não existe!");
        }

        var page = answerRepository
                .getAllAnswersQuestion(pageable, questionId)
                .map(AnswerDetailsDto::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAnswer(
            @PathVariable Long id) {

        User user = getauthenticatedUser();

        if (!answerRepository.existsById(id)) {
            throw new ValidationException("ID da resposta não existe!");
        }
        var answer = answerRepository.getReferenceById(id);
        if (!user.getId().equals(answer.getUser().getId())) {
            throw new ValidationException(
                    "Resposta não pode ser deletada," +
                    " não pertence a esse usuário!");
        }
        answer.deleteAnswer();

        return ResponseEntity.noContent().build();
    }

    private User getauthenticatedUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
