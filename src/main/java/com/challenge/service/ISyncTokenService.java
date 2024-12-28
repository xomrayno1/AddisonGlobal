package com.challenge.service;

import com.challenge.model.Credentials;
import com.challenge.model.User;
import com.challenge.model.UserToken;

public interface ISyncTokenService {
	
    User authenticate(Credentials credentials);

    UserToken requestToken(User user);

    default UserToken issueToken(Credentials credentials) {
        User user = authenticate(credentials);
        return requestToken(user);
    }

}
