package br.com.mercadoapp.mercadoapp.service;

import br.com.mercadoapp.mercadoapp.dto.UsuarioDto;
import br.com.mercadoapp.mercadoapp.model.Usuario;
import br.com.mercadoapp.mercadoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> obterTodos(){
        return repository.findAll();
    }

    public Usuario cadastrarUsuario(Usuario obj) {
        return repository.save(obj);
    }
}
