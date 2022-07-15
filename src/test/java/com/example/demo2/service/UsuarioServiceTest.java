package com.example.demo2.service;

import com.example.demo2.service.response.UsuarioResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("")
class UsuarioServiceTest {

    private static final Long ID_ORGAO_INEXISTENTE = 999L;
    @Autowired
    private UsuarioService usuarioService;

    private static final Long ID_ORGAO_A = 1L;
    private static final Long ID_PERFIL_A = 1L;
    private static final Long ID_ORGAO_B = 2L;
    private static final Long ID_PERFIL_B = 2L;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Deve retornar os usuários para distribuição de processo do orgaoId igual 1 e perfilId igual a 1")
    void deveRetornarOsUsuariosDoOrgao1EPerfil1() {
        Optional<UsuarioDto> usuarioDto = usuarioService.obterUsuarios(ID_ORGAO_A, ID_PERFIL_A);
        assertTrue(usuarioDto.isPresent());
    }

    @Test
    @DisplayName("Deve retornar os usuários para distribuição de processo do orgaoId igual 2 e perfilId igual a 1")
    void deveRetornarOsUsuariosDoOrgao2EPerfil1() {
        Optional<UsuarioDto> usuarioDto = usuarioService.obterUsuarios(ID_ORGAO_B, ID_PERFIL_A);
        assertTrue(usuarioDto.isPresent());
    }

    @Test
    @DisplayName("Deve retornar nenhum usuário para distribuição de processo do orgaoId igual 99 e perfilId igual a 1")
    void deveRetornarNenhumUsuarioDoOrgao99EPerfilUm() {
        Optional<UsuarioDto> usuarioDto = usuarioService.obterUsuarios(ID_ORGAO_INEXISTENTE, ID_PERFIL_A);
        assertTrue(usuarioDto.isEmpty());
    }

    @Test
    @DisplayName("Deve verificar se lista de CPF é nulo")
    void deverVerificarSeListaDeCPFEhNulo() {
        Optional<UsuarioDto> usuarioDtoOptional = usuarioService.obterUsuarios(ID_ORGAO_A, ID_PERFIL_A);
        assertTrue(usuarioDtoOptional.isPresent());
        UsuarioDto usuarioDto = usuarioDtoOptional.get();
        assertNotNull(usuarioDto.getUsuarios());
        assertEquals(3, usuarioDto.getUsuarios().size());
    }

    @Test
    @DisplayName("Deve verificar se o CPF está cadastrado no sistema para o Órgão com os dados do CPF e nome cadastrado")
    void deverVerificaseOCPFestaCadastradoNoSistemaParaOOrgao1() {

        Optional<UsuarioDto> usuarioDtoOptional = usuarioService.obterUsuarios(ID_ORGAO_A, ID_PERFIL_A);
        assertTrue(usuarioDtoOptional.isPresent());
        UsuarioDto usuarioDto = usuarioDtoOptional.get();
        List<UsuarioResponse> usuarioList = usuarioDto.getUsuarios();
        boolean existeCPFOuNomeNulo = usuarioList.stream().anyMatch(usuarioResponse -> usuarioResponse.getCpf() == null || usuarioResponse.getNome() == null);
        assertFalse(existeCPFOuNomeNulo);

    }



}