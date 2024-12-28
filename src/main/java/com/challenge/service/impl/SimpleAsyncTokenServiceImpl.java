package com.challenge.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.challenge.model.Credentials;
import com.challenge.model.UserToken;
import com.challenge.service.AuthService;
import com.challenge.service.ISimpleAsyncTokenService;
import com.challenge.service.TokenService;

@Service
public class SimpleAsyncTokenServiceImpl implements ISimpleAsyncTokenService{

	private final AuthService authService;
	private final TokenService tokenService;

	public SimpleAsyncTokenServiceImpl(AuthService authService, TokenService tokenService) {
		this.authService = authService;
		this.tokenService = tokenService;
	}

	@Override
	public CompletableFuture<UserToken> issueToken(Credentials credentials) {	
        return authService.authenticate(credentials) // Step 1: Authenticate the user
                .thenCompose(user -> tokenService.requestToken(user)); // Step 2: Generate the token
	}

}
