package br.com.mercadoapp.ms.pagamentos.mapper;

import br.com.mercadoapp.ms.pagamentos.dto.PagamentoRequestDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoResponseDto;
import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//Permite injeção com @Autowired
@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    // Converte Request DTO para Entidade
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dataPagamento", ignore = true)
    @Mapping(target = "valor", ignore = true)
    Pagamento toEntity(PagamentoRequestDto dto);

    // Converte Entidade para Response DTO
    PagamentoResponseDto toDto(Pagamento pagamento);
}
