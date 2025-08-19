package br.com.mercadoapp.ms.pagamentos.controller;

import br.com.mercadoapp.ms.pagamentos.dto.PagamentoRequestDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoResponseDto;
import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import br.com.mercadoapp.ms.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    //@Autowired
    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponseDto> criarPagamento(@Valid @RequestBody PagamentoRequestDto dto) {
        PagamentoResponseDto pagamento = pagamentoService.criarPagamento(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    //Verifica o balanceamento de carga entre as instâncias
    //Endpoint usado para identificar qual instância do microsserviço respondeu à requisição.
    //Útil para testes de balanceamento de carga em ambientes com múltiplas instâncias.
    @GetMapping("/response")
    public String obterPorta(@Value("${local.server.port}") String porta) {
        return String.format("Resposta vinda da porta %s", porta);

    }
}
