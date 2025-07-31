package br.com.mercadoapp.ms_pedidos.utils;

import br.com.mercadoapp.ms_pedidos.dto.ProdutoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mercadoapp")  //O nome do serviço de produtos no registro
public interface ProdutoClient {

    @GetMapping("/produtos/{id}")  //Caminho correto do endpoint no mercadoapp
    ProdutoResponseDto buscarPorId(@PathVariable Long id);
}

