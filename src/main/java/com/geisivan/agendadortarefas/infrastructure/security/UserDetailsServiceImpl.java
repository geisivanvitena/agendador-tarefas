package com.geisivan.agendadortarefas.infrastructure.security;

import com.geisivan.agendadortarefas.business.dto.UsuarioDTO;
import com.geisivan.agendadortarefas.infrastructure.security.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UsuarioClient usuarioClient;

    public UserDetails carregarDadosUsuario(String email, String token){

        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.email()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.senha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
