package com.henrique.majesty.dto.auth;

import java.time.Instant;

public record TokenDto(String token, Instant expiresAt) {}
