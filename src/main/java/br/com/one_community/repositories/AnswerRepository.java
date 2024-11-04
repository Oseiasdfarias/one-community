package br.com.one_community.repositories;

import br.com.one_community.entities.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwswerRepository extends JpaRepository<Answer, Long> {
}
