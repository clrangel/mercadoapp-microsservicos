package br.com.mercadoapp.mercadoapp.controller;

import br.com.mercadoapp.mercadoapp.dto.ProdutoRequestDTO;
import br.com.mercadoapp.mercadoapp.dto.ProdutoResponseDTO;
import br.com.mercadoapp.mercadoapp.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.cadastrarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO atualizado = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        ProdutoResponseDTO dto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(dto);
    }
}
