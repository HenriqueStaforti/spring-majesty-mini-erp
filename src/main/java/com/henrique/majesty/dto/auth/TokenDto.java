package com.henrique.majesty.dto;

import java.time.Instant;

public record TokenDto(String token, Instant expiresAt) {}
