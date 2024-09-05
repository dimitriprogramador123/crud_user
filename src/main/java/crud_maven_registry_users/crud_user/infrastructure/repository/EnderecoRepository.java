package crud_maven_registry_users.crud_user.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crud_maven_registry_users.crud_user.infrastructure.entities.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
