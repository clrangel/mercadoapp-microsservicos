package br.com.mercadoapp.ms.pagamentos.controller;

import br.com.mercadoapp.ms.pagamentos.dto.PagamentoRequestDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoResponseDto;
import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import br.com.mercadoapp.ms.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponseDto> criarPagamento(@Valid @RequestBody PagamentoRequestDto dto) {
        Pagamento pagamentoCriado = pagamentoService.criarPagamento(dto);

        // Mapear entidade para DTO de resposta
        PagamentoResponseDto responseDto = new PagamentoResponseDto(
                pagamentoCriado.getId(),
                pagamentoCriado.getPedidoId(),
                pagamentoCriado.getValor(),
                pagamentoCriado.getFormaPagamento(),
                pagamentoCriado.getStatus(),
                pagamentoCriado.getDataPagamento()
        );

        return ResponseEntity.ok(responseDto);
    }
}
