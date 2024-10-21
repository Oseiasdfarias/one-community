package br.com.one_community.repositories;

import br.com.one_community.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<User, Long> {
}
