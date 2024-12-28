package com.challenge.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.challenge.model.Credentials;
import com.challenge.model.User;
import com.challenge.model.UserToken;

public interface IAsyncTokenService {
	
    CompletableFuture<User> authenticate(Credentials credentials);

    CompletableFuture<UserToken> requestToken(User user);

    default Future<UserToken> issueToken(Credentials credentials) {
    	return  authenticate(credentials).thenCompose(user -> requestToken(user));
    }

}
