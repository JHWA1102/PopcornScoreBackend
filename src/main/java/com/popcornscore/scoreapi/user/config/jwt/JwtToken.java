package com.popcornscore.scoreapi.user.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
	
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String username;

}
