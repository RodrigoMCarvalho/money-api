package com.rodrigo.moneyapi.controller;

import com.rodrigo.moneyapi.model.dto.TokenDTO;
import com.rodrigo.moneyapi.model.dto.UsuarioDTO;
import com.rodrigo.moneyapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsernamePasswordAuthenticationToken dadosLogin = usuarioDTO.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            //System.out.println(token);
            return ResponseEntity.ok().body(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
