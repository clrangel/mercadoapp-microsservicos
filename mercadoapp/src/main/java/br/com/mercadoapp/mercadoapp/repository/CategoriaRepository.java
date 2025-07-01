package br.com.mercadoapp.mercadoapp.repository;

import br.com.mercadoapp.mercadoapp.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
