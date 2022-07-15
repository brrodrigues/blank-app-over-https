package com.example.demo2.service;

import com.example.demo2.service.response.UsuarioResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long orgaoId;
    private List<UsuarioResponse> usuarios;

}
