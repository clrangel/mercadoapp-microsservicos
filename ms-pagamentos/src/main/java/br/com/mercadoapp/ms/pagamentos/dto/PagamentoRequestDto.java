package br.com.mercadoapp.ms.pagamentos.dto;

import br.com.mercadoapp.ms.pagamentos.model.FormaPagamento;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDto(
        @NotNull UUID pedidoId,
        @NotNull BigDecimal valor,
        @NotNull FormaPagamento formaPagamento
) {}