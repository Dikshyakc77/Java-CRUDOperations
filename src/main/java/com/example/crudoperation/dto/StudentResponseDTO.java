package com.example.crudoperation.dto;

public record StudentResponseDTO(
        Long id,
        String name,
        String email,
        String address
) {}