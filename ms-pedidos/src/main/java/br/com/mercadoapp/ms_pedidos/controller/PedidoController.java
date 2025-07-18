package br.com.mercadoapp.ms_pedidos.controller;

import br.com.mercadoapp.ms_pedidos.dto.PedidoRequestDto;
import br.com.mercadoapp.ms_pedidos.dto.PedidoResponseDto;
import br.com.mercadoapp.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(@Valid @RequestBody PedidoRequestDto pedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPedido(pedidoDto));
    }
}
