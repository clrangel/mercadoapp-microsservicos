//package br.com.mercadoapp.ms_pedidos.client;
//
//import br.com.mercadoapp.ms_pedidos.dto.PagamentoRequestDto;
//import br.com.mercadoapp.ms_pedidos.dto.PagamentoResponseDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "ms-pagamentos", contextId = "pagamentoClient")
//public interface PagamentoClient {
//
//    @PostMapping("/pagamentos")
//    PagamentoResponseDto criarPagamento(@RequestBody PagamentoRequestDto dto);
//
//}
