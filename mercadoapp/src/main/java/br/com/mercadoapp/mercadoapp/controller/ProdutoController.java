package br.com.mercadoapp.mercadoapp.controller;

import br.com.mercadoapp.mercadoapp.dto.ProdutoRequestDTO;
import br.com.mercadoapp.mercadoapp.dto.ProdutoResponseDTO;
import br.com.mercadoapp.mercadoapp.model.Produto;
import br.com.mercadoapp.mercadoapp.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //@Autowired
    //private PagedResourcesAssembler<ProdutoResponseDTO> pagedResourcesAssembler;

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

    @GetMapping("/paginado")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutos(
            @RequestParam(defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10); // 10 itens por página
        Page<ProdutoResponseDTO> pagina = produtoService.listarProdutos(pageable);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/buscar-frase")
    public ResponseEntity<Page<ProdutoResponseDTO>> buscarPorFrase(
            @RequestParam("q") String frase,
            Pageable pageable) {

        Page<ProdutoResponseDTO> page = produtoService.buscarPorFrase(frase, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscar-por-categoria")
    public ResponseEntity<Page<ProdutoResponseDTO>> buscarPorCategoria(
            @RequestParam Long categoriaId,
            Pageable pageable) {

        Page<ProdutoResponseDTO> page = produtoService.buscarPorCategoria(categoriaId, pageable);
        return ResponseEntity.ok(page);
    }


}
