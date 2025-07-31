package br.com.mercadoapp.ms_pedidos.dto;

import java.math.BigDecimal;

public record ProdutoResponseDto(Long id, String nome, BigDecimal preco) {}

