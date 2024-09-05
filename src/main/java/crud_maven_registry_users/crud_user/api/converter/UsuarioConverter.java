package crud_maven_registry_users.crud_user.api.converter;

import java.time.Clock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import crud_maven_registry_users.crud_user.api.converter.UsuarioUpdateMapper.UsuarioRequestDTO;
import crud_maven_registry_users.crud_user.infrastructure.entities.UsuarioEntity;

/**
 * Classe responsável pela conversão de DTOs para entidades e vice-versa.
 */
@Component
public class UsuarioConverter {

    @Autowired
    public UsuarioConverter(Clock clock) {
    }

    public UsuarioEntity paraUsuarioEntity(UsuarioRequestDTO usuarioRequestDTO) {
        // User
        throw new UnsupportedOperationException("Unimplemented method 'paraUsuarioEntity'");
    }
}
