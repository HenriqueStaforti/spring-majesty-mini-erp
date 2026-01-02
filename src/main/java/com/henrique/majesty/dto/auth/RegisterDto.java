package com.henrique.majesty.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
        @NotBlank String username,
        @NotBlank String password
) {}
