package crud_maven_registry_users.crud_user.api.response;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String documento,
        EnderecoResponse endereco) {

}
