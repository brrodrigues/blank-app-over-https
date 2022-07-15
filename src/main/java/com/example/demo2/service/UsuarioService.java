package com.example.demo2.service;

import com.example.demo2.service.response.UsuarioResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
public class UsuarioService {

    public Optional<UsuarioDto> obterUsuarios(Long orgaoId, Long perfilId) {

        if (orgaoId == null) {
            throw new IllegalArgumentException("O código do orgão não informado.");
        }

        if (perfilId == null) {
            throw new IllegalArgumentException("O código do perfil não foi informado.");
        }

        if (orgaoId == 1L && perfilId == 1L) {
            return Optional.ofNullable(UsuarioDto.builder().usuarios(
                    Arrays.asList(
                            UsuarioResponse.builder().cpf("00000000000").nome("Usuario 1").build(),
                            UsuarioResponse.builder().cpf("11111111111").nome("Usuario 2").build(),
                            UsuarioResponse.builder().cpf("22222222222").nome("Usuario 3").build()
                    )
            ).build());

        }

        if (orgaoId == 1L && perfilId == 2L) {
            return Optional.ofNullable(UsuarioDto.builder().usuarios(
                    Arrays.asList(
                            UsuarioResponse.builder().cpf("66666666666").nome("Usuario 7").build(),
                            UsuarioResponse.builder().cpf("77777777777").nome("Usuario 8").build()
                    )
            ).build());

        }

        if (orgaoId == 2L && perfilId == 1L) {
            return Optional.ofNullable(UsuarioDto.builder().usuarios(
                    Arrays.asList(
                            UsuarioResponse.builder().cpf("33333333333").nome("Usuario 4").build(),
                            UsuarioResponse.builder().cpf("44444444444").nome("Usuario 5").build(),
                            UsuarioResponse.builder().cpf("55555555555").nome("Usuario 6").build()
                    )
            ).build());

        }

        log.info("Usuário não localizados com os parâmetros informados: {} {}", orgaoId, perfilId);

        return Optional.empty();
    }
}
