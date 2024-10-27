package br.com.one_community.controllers;

import br.com.one_community.entities.ValidationException;
import br.com.one_community.entities.user.User;
import br.com.one_community.entities.user.UserDetailsDto;
import br.com.one_community.entities.user.UserDto;
import br.com.one_community.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerUser(
            @RequestBody @Valid UserDto data,
            UriComponentsBuilder uriBuilder) {

        var user = new User(data);
        userRepository.save(user);

        var uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new UserDetailsDto(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository.findByName(username);

        if (!userRepository.existsById(id)) {
            throw new ValidationException("Id do autor informado não existe!");
        }

        if (!authenticatedUser.getId().equals(id)) {
            throw new ValidationException("Você só pode deletar o seu próprio usuário.");
        }

        userRepository.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
