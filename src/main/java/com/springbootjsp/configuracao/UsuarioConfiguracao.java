package com.springbootjsp.configuracao;

import com.springbootjsp.modelo.Usuario;
import com.springbootjsp.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioConfiguracao implements UserDetailsService {

    @Autowired
    private UsuarioServico servico;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = servico.buscar(null,login);
        if (usuario == null)
            throw new UsernameNotFoundException("Usuário não encontrado.");
        return usuario;
    }

}
