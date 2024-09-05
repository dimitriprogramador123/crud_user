package crud_maven_registry_users.crud_user.api.response;

public record EnderecoResponse(
        String rua,
        Long numero,
        String bairro,
        String complement0,
        String cidade,
        String cep) {

}
