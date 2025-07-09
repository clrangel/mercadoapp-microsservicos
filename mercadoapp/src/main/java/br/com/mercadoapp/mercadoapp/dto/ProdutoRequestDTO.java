package br.com.mercadoapp.mercadoapp.dto;

import java.util.Set;

public record ProdutoRequestDTO(
        String nome,
        String descricao,
        Double preco,
        Set<Long> categoriaIds
) {}

