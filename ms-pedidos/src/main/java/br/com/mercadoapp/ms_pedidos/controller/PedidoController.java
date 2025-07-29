package br.com.mercadoapp.ms_pedidos.controller;

import br.com.mercadoapp.ms_pedidos.dto.PedidoRequestDto;
import br.com.mercadoapp.ms_pedidos.dto.PedidoResponseDto;
import br.com.mercadoapp.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(@Valid @RequestBody PedidoRequestDto pedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPedido(pedidoDto));
    }

    //Vizualizar todos os pedidos
    //Metodo para testar o API Gateway no navegador
    //Atualizar esse metodo futuramente
    @GetMapping
    public ResponseEntity<Page<PedidoResponseDto>> listarPedidos(
            @RequestParam(defaultValue = "0") int page, // Página atual
            @RequestParam(defaultValue = "10") int size, // Quantos pedidos por página
            @RequestParam(defaultValue = "dateMoment") String sort, // Campo para ordenar
            @RequestParam(defaultValue = "desc") String direction) { // Direção da ordenação

        Pageable pageable = PageRequest.of(page, size,
                direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending());

        return ResponseEntity.ok(service.obterTodos(pageable));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        service.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

}
