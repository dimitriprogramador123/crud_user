package crud_maven_registry_users.crud_user.api.request;

import crud_maven_registry_users.crud_user.infrastructure.entities.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para o endereço.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class EnderecoRequestDTO {

    private String rua;
    private Long numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String cep;
    private String estado;  // Ajustado para o tipo String, se apropriado

    /**
     * Converte este DTO para uma entidade de endereço.
     *
     * @return a entidade de endereço
     */
    public EnderecoRequestDTO paraEnderecoEntity() {
        return ((EnderecoRequestDTOBuilder) EnderecoEntity.builder())
                .rua(getRua())
                .bairro(getBairro())
                .cep(getCep())
                .cidade(getCidade())
                .numero(getNumero())
                .complemento(getComplemento())
                .estado(getEstado())
                .build();
    }
}
