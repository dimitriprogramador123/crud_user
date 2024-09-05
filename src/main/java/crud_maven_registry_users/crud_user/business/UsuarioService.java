package crud_maven_registry_users.crud_user.business;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import crud_maven_registry_users.crud_user.api.converter.UsuarioConverter;
import crud_maven_registry_users.crud_user.api.converter.UsuarioMapper;
import crud_maven_registry_users.crud_user.api.converter.UsuarioUpdateMapper;
import crud_maven_registry_users.crud_user.api.converter.UsuarioUpdateMapper.UsuarioRequestDTO;
import crud_maven_registry_users.crud_user.api.response.UsuarioResponse;
import crud_maven_registry_users.crud_user.infrastructure.entities.UsuarioEntity;
import crud_maven_registry_users.crud_user.infrastructure.exceptions.BusinessException;
import crud_maven_registry_users.crud_user.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final UsuarioUpdateMapper usuarioUpdateMapper;
    private final UsuarioMapper usuarioMapper;

    /**
     * Salva um usuário no banco de dados.
     *
     * @param usuarioEntity o usuário a ser salvo
     * @return o usuário salvo
     */
    public UsuarioEntity salvaUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.saveAndFlush(usuarioEntity);
    }

    /**
     * Grava um novo usuário.
     *
     * @param usuarioRequestDTO os dados do usuário a serem gravados
     * @return a resposta com os dados do usuário gravado
     */
    public UsuarioResponse gravarUsuarios(final UsuarioRequestDTO usuarioRequestDTO) {
        notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
        try {
            UsuarioEntity usuarioEntity = usuarioConverter.paraUsuarioEntity(usuarioRequestDTO);
            UsuarioEntity salvo = salvaUsuario(usuarioEntity);
            return usuarioMapper.paraUsuarioResponseDTO(salvo);
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário", e);
        }
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param usuarioRequestDTO os dados atualizados do usuário
     * @return a resposta com os dados do usuário atualizado
     */
    public UsuarioResponse atualizaCadastro(UsuarioRequestDTO usuarioRequestDTO) {
        notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
        try {
            UsuarioEntity usuario = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
            if (usuario == null) {
                throw new ResourceAccessException("Usuário não encontrado");
            }
            usuarioUpdateMapper.updateUsuarioFromDTO(usuarioRequestDTO, usuario);
            UsuarioEntity atualizado = salvaUsuario(usuario);
            return usuarioMapper.paraUsuarioResponseDTO(atualizado);
        } catch (ResourceAccessException e) {
            throw new BusinessException("Erro ao atualizar dados de usuário", e);
        }
    }

    /**
     * Busca um usuário por e-mail.
     *
     * @param email o e-mail do usuário a ser buscado
     * @return a resposta com os dados do usuário, ou null se não encontrado
     */
    public UsuarioResponse buscaDadosUsuario(String email) {
        UsuarioEntity entity = usuarioRepository.selectFromEmail(email);
        return entity != null ? usuarioMapper.paraUsuarioResponseDTO(entity) : null;
    }

    /**
     * Busca usuários por iniciais.
     *
     * @param iniciais as iniciais para filtrar usuários
     * @return a lista de usuários que correspondem às iniciais
     */
    public List<UsuarioResponse> buscaPorIniciais(String iniciais) {
        List<UsuarioEntity> entities = usuarioRepository.findByNomeOrEmailStartingWith(iniciais);
        return entities.isEmpty() ? Collections.emptyList() : usuarioMapper.paraListaUsuarioResponseDTO(entities);
    }

    /**
     * Atualiza o e-mail de um usuário.
     *
     * @param email o e-mail do usuário a ser atualizado
     * @param documento o novo documento do usuário
     */
    public void fazUpdateDeEmail(String email, String documento) {
        usuarioRepository.updateEmail(documento, email);
    }

    /**
     * Deleta um usuário pelo e-mail.
     *
     * @param email o e-mail do usuário a ser deletado
     */
    public void deletaDadosUsuario(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    private void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
