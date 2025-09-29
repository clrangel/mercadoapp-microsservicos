package br.com.mercadoapp.mercadoapp.service;

import br.com.mercadoapp.mercadoapp.dto.UsuarioDto;
import br.com.mercadoapp.mercadoapp.model.Usuario;
import br.com.mercadoapp.mercadoapp.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> obterTodos(){
        return repository.findAll();
    }

    public Usuario obterPorId(Long id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.get();
    }

    public UsuarioDto cadastrarUsuario(UsuarioDto usuario) {
        Usuario usuarioEntity = new Usuario();
        BeanUtils.copyProperties(usuario, usuarioEntity);
        repository.save(usuarioEntity);
        return new UsuarioDto(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getTelefone(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha());
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public  Usuario atualizar(Long id, Usuario obj){
        Usuario entityUsuario = repository.getReferenceById(id);
        updateData(entityUsuario, obj);
        return repository.save(entityUsuario);
    }

    private void updateData(Usuario entityUsuario, Usuario obj) {
        entityUsuario.setNome(obj.getNome());
        entityUsuario.setEmail(obj.getEmail());
        entityUsuario.setTelefone(obj.getTelefone());
    }
}
