package br.com.mercadoapp.mercadoapp.repository;

import br.com.mercadoapp.mercadoapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
