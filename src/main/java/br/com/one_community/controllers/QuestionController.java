package br.com.one_community.controllers;


import br.com.one_community.entities.ValidationException;
import br.com.one_community.entities.question.DataUpdateDto;
import br.com.one_community.entities.question.Question;
import br.com.one_community.entities.question.QuestionDetailsDto;
import br.com.one_community.entities.question.QuestionDto;
import br.com.one_community.entities.user.User;
import br.com.one_community.repositories.QuestionRepository;
import br.com.one_community.repositories.UserRepository;
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
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity PostQuestion(
            @RequestBody @Valid QuestionDto data,
            UriComponentsBuilder uriBuilder) {

        Long userId = getauthenticatedUser().getId();
        var user = userRepository
                .findById(userId).get();
        var question = new Question(data, user);
        questionRepository.save(question);

        var uri = uriBuilder
                .path("/questions/{id}")
                .buildAndExpand(question.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new QuestionDetailsDto(question));
    }

    @GetMapping("/list-questions-user")
    public ResponseEntity<Page<QuestionDetailsDto>> listQuestionsUser(
            @PageableDefault(page = 0, size = 5, sort = {"title"})
            Pageable pageable) {

        Long id = getauthenticatedUser().getId();
        var page = questionRepository.getAllUserQuestions(pageable, id)
                .map(QuestionDetailsDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateQuestion(
            @RequestBody @Valid DataUpdateDto data) {

        if (!questionRepository.existsById(data.id())) {
            throw new ValidationException
                    ("Id da pergunta informado não existe!");
        }

        Long userId = getauthenticatedUser().getId();
        var question = questionRepository.getReferenceById(data.id());

        if (!userId.equals(question.getUser().getId())){
            throw new ValidationException(
                    "Pergunta não pertence a esse usuário!");
        }
        question.updateInfos(data);

        return ResponseEntity.ok(new QuestionDetailsDto(question));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteQuestion(@PathVariable Long id) {

        Long userId = getauthenticatedUser().getId();

        if (!questionRepository.existsById(id)) {
            throw new ValidationException
                    ("Id da pergunta informado não existe!");
        }
        var question = questionRepository.getReferenceById(id);

        if (!userId.equals(question.getUser().getId())){
            throw new ValidationException(
                    "Pergunta não pertence a esse usuário!");
        }

        question.excluir();

        return ResponseEntity.noContent().build();
    }

    private User getauthenticatedUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
