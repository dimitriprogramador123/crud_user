package crud_maven_registry_users.crud_user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crud_maven_registry_users.crud_user.api.converter.UsuarioUpdateMapper.UsuarioRequestDTO;
import crud_maven_registry_users.crud_user.api.response.UsuarioResponse;
import crud_maven_registry_users.crud_user.business.UsuarioService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param usuarioRequestDTO os dados atualizados do usuário
     * @return a resposta com o status da operação
     */
    @PutMapping("/atualiza")
    public ResponseEntity<Void> atualizaDadosUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.atualizaCadastro(usuarioRequestDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Busca um usuário por e-mail.
     *
     * @param email o e-mail do usuário a ser buscado
     * @return a resposta com os dados do usuário
     */
    @GetMapping("/busca")
    public ResponseEntity<UsuarioResponse> buscaUsuarioPorEmail(@RequestParam("email") String email) {
        UsuarioResponse usuarioResponse = usuarioService.buscaDadosUsuario(email);
        return ResponseEntity.ok(usuarioResponse);
    }

    /**
     * Busca usuários por iniciais.
     *
     * @param iniciais as iniciais para filtrar usuários
     * @return a lista de usuários que correspondem às iniciais
     */
    @GetMapping("/busca-iniciais")
    public ResponseEntity<List<UsuarioResponse>> buscaUsuarioPorIniciais(@RequestParam("iniciais") String iniciais) {
        List<UsuarioResponse> usuarios = usuarioService.buscaPorIniciais(iniciais);
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Deleta um usuário pelo e-mail.
     *
     * @param email o e-mail do usuário a ser deletado
     * @return a resposta com o status da operação
     */
    @DeleteMapping("/deleta")
    public ResponseEntity<Void> deletaDadosUsuario(@RequestParam("email") String email) {
        usuarioService.deletaDadosUsuario(email);
        return ResponseEntity.accepted().build();
    }

    /**
     * Atualiza o e-mail de um usuário.
     *
     * @param email o e-mail do usuário a ser atualizado
     * @param documento o novo documento do usuário
     * @return a resposta com o status da operação
     */
    @PatchMapping("/atualiza-email")
    public ResponseEntity<Void> updateDeEmail(@RequestParam("email") String email,
            @RequestParam("documento") String documento) {
        usuarioService.fazUpdateDeEmail(email, documento);
        return ResponseEntity.accepted().build();
    }
}
