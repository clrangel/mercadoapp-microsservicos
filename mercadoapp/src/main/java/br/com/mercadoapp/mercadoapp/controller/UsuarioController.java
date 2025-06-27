package br.com.mercadoapp.mercadoapp.controller;

import br.com.mercadoapp.mercadoapp.dto.UsuarioDto;
import br.com.mercadoapp.mercadoapp.model.Usuario;
import br.com.mercadoapp.mercadoapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> list = service.obterTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable Long id){
        Usuario obj = service.obterPorId(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario obj){
        obj = service.cadastrarUsuario(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario obj){
        obj = service.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }


}
