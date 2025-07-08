package br.com.mercadoapp.mercadoapp.controller;

import br.com.mercadoapp.mercadoapp.model.Categoria;
import br.com.mercadoapp.mercadoapp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria categoria) {
        categoria = service.cadastrarCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
                .buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        service.deletarCategoria(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = service.atualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = service.listarCategorias();
        return ResponseEntity.ok(categorias);
    }


}
