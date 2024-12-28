package com.challenge.service;

import java.util.concurrent.CompletableFuture;

import com.challenge.model.Credentials;
import com.challenge.model.UserToken;

public interface ISimpleAsyncTokenService {
	
    default CompletableFuture<UserToken> issueToken(Credentials credentials) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

}
