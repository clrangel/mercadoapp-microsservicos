package br.com.mercadoapp.mercadoapp.specification;

import br.com.mercadoapp.mercadoapp.model.Produto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProdutoSpecifications {

    public static Specification<Produto> nomeOuDescricaoContemTodasPalavras(String frase) {
        return (root, query, cb) -> {
            String[] palavras = frase.toLowerCase().split("\\s+");

            List<Predicate> predicates = new ArrayList<>();
            for (String palavra : palavras) {
                Predicate nomeLike = cb.like(cb.lower(root.get("nome")), "%" + palavra + "%");
                Predicate descricaoLike = cb.like(cb.lower(root.get("descricao")), "%" + palavra + "%");
                predicates.add(cb.or(nomeLike, descricaoLike));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Produto> buscarPorCategoria(Long categoriaId) {
        return (root, query, cb) -> cb.equal(
                root.join("categorias").get("id"), categoriaId
        );
    }
}