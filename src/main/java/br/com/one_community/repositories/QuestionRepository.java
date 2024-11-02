package br.com.one_community.repositories;

import br.com.one_community.entities.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.user.id = :id AND q.status = true")
    Page<Question> getAllUserQuestions(Pageable pageable, @Param("id") Long userId);
}
