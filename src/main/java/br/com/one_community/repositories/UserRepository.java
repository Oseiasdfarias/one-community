package br.com.one_community.repositories;

import br.com.one_community.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository  extends JpaRepository<User, Long> {
    UserDetails findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.userName = :username")
    User findByName(String username);

    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deletarPorId(Long id);
}
