package br.com.mercadoapp.mercadoapp.test;

import br.com.mercadoapp.mercadoapp.dto.EmailDto;
import br.com.mercadoapp.mercadoapp.service.UsuarioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TesteEnvioEmail {

    @Autowired
    private UsuarioService service;

    @PostConstruct
    public void testeEmail() {
        EmailDto dto = new EmailDto(
                "Carol L",
                "123.456.789-10",
                "teste123",
                "RECUSADO"
        );
        service.enviarMensagem(dto);
    }
}
