package crud_maven_registry_users.crud_user.api.converter;

// UsuarioUpdateMapper.java
import crud_maven_registry_users.crud_user.infrastructure.entities.UsuarioEntity;

public interface UsuarioUpdateMapper {

    void updateUsuarioFromDTO(UsuarioRequestDTO usuarioDTO, UsuarioEntity usuarioEntity);

    public interface UsuarioRequestDTO {

        String getEmail();
    }

}
