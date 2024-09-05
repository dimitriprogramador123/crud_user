package crud_maven_registry_users.crud_user.api.converter;

import java.util.List;

import crud_maven_registry_users.crud_user.api.response.UsuarioResponse;
import crud_maven_registry_users.crud_user.infrastructure.entities.UsuarioEntity;

public interface UsuarioMapper {

    UsuarioResponse paraUsuarioResponseDTO(UsuarioEntity usuario);

    List<UsuarioResponse> paraListaUsuarioResponseDTO(List<UsuarioEntity> usuarios);

}
