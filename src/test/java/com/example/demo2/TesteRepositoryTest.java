package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TesteRepositoryTest {

    @Autowired
    TesteRepository testeRepository;

    @Test
    @DisplayName("Os componentes não deve retornar nulo")
    void repositoryDeveRetornarNaoNull() {

        assertNotNull(testeRepository);

    }

    @Test
    @DisplayName("Deve registrar a entidade TesteEntity na base de dados")
    void deveRegistrarTeste1NaTabela() {

        TesteEntity entity = TesteEntity.builder().nome("Teste 1").build();
        testeRepository.save(entity);
        assertNotNull(entity.getId());

    }


    @Test
    @DisplayName("Deve retornar o registro de id 1 gravado na base")
    void deveRetornarRegistroTeste1() {

        Optional<TesteEntity> testeEntityOptional = testeRepository.findById(1L);
        assertTrue(testeEntityOptional.isPresent());

    }

    @Test
    @DisplayName("Deve retornar nenhum registro com id 0")
    void deveRetornarNenhumRegistroComId0() {

        Optional<TesteEntity> testeEntityOptional = testeRepository.findById(0L);
        assertFalse(testeEntityOptional.isPresent());

    }

    @Test
    @Sql("classpath:update_test_02.sql")
    @DisplayName("Deve ter \"Teste de carga 1\" como nome no registro 1")
    void deveConterOsTesteDeCarga1ComoNomeNoRegistro1() {

        Optional<TesteEntity> testeEntityOptional = testeRepository.findById(1L);
        assertFalse(testeEntityOptional.isEmpty());
        TesteEntity entity = testeEntityOptional.get();
        assertEquals("Teste de Carga 1", entity.getNome());

    }

    @Test
    @Sql( scripts = {"classpath:update_test.sql"})
    @DisplayName("Deve ter o nome \"Teste 1\" no registro da tabela 1")
    void deveTerONomeTeste1NoRegistroDaTabelaTeste() {

        Optional<TesteEntity> testeOptional = testeRepository.findById(1L);
        assertFalse(testeOptional.isEmpty());
        TesteEntity teste = testeOptional.get();
        assertEquals("Teste 1", teste.getNome());

    }

    @Test
    @DisplayName("Deve ter \"Teste de carga 1\" como nome no registro 1")
    @Sql( scripts = {"classpath:update_test.sql"})
    void deveAtualizarRegistroTeste1() {

        Optional<TesteEntity> testeOptional = testeRepository.findById(1L);
        assertFalse(testeOptional.isEmpty());
        TesteEntity teste = testeOptional.get();
        assertNotEquals("Teste de carga 1", teste.getNome());
        teste.setNome("Teste alteração 1");
        TesteEntity save = testeRepository.save(teste);
        assertEquals("Teste alteração 1", save.getNome());

    }



}