package br.com.one_community.repositories;

import br.com.one_community.entities.answer.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a FROM Answer a WHERE a.question.id = :id")
    Page<Answer> getAllAnswersQuestion(Pageable pageable, @Param("id") Long questionId);
}
