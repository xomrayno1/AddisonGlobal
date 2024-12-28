package com.challenge.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.challenge.model.Credentials;
import com.challenge.model.User;
import com.challenge.model.UserToken;
import com.challenge.service.AuthService;
import com.challenge.service.IAsyncTokenService;
import com.challenge.service.TokenService;

@Service
public class AsyncTokenServiceImpl implements IAsyncTokenService{
	
	private final AuthService authService;
	private final TokenService tokenService;

	public AsyncTokenServiceImpl(AuthService authService, TokenService tokenService) {
		this.authService = authService;
		this.tokenService = tokenService;
	}

	@Override
	public CompletableFuture<User> authenticate(Credentials credentials) {
		return authService.authenticate(credentials);
	}

	@Override
	public CompletableFuture<UserToken> requestToken(User user) {
		return tokenService.requestToken(user);
	}

}
