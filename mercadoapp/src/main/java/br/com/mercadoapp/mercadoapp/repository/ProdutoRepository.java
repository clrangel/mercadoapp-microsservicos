package br.com.mercadoapp.mercadoapp.repository;

import br.com.mercadoapp.mercadoapp.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
