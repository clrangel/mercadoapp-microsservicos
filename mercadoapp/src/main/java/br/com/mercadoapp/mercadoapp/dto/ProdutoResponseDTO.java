package br.com.mercadoapp.mercadoapp.dto;

import java.util.Set;

public record ProdutoResponseDTO(
        //Long id,
        String nome,
        String descricao,
        Double preco,
        Set<String> categorias
) {}

