package br.com.mercadoapp.ms.pagamentos.controller;

import br.com.mercadoapp.ms.pagamentos.dto.PagamentoRequestDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoResponseDto;
import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import br.com.mercadoapp.ms.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        PagamentoResponseDto pagamento = pagamentoService.criarPagamento(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }
}
