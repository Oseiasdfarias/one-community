package br.com.one_community.controllers;



import br.com.one_community.entities.ValidationException;
import br.com.one_community.entities.user.DataAutentication;
import br.com.one_community.entities.user.User;
import br.com.one_community.infra.security.DataTokenJWT;
import br.com.one_community.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DataTokenJWT> efetuarLogin(
            @RequestBody @Valid DataAutentication data) {

        var autenticationToken = new UsernamePasswordAuthenticationToken(
                data.login(), data.senha());
        var authentication = manager.authenticate(autenticationToken);

        if (authentication.getName() == null) {
            throw new ValidationException("Login ou senha incorreto!");
        }

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        var user = (User) authentication.getPrincipal();

        if (!user.getStatus()) {
            throw new ValidationException("Usuário Deletado!");
        }

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
