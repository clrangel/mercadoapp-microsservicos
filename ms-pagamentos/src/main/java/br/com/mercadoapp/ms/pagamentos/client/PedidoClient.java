package br.com.mercadoapp.ms.pagamentos.client;

import br.com.mercadoapp.ms.pagamentos.client.dto.PedidoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ms-pedidos")
public interface PedidoClient {

    @GetMapping("/pedidos/{id}")
    PedidoResponseDto buscarPedidoPorId(@PathVariable UUID id);
}
