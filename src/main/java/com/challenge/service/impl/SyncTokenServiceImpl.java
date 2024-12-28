package com.challenge.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.challenge.model.Credentials;
import com.challenge.model.User;
import com.challenge.model.UserToken;
import com.challenge.service.AuthService;
import com.challenge.service.ISyncTokenService;
import com.challenge.service.TokenService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SyncTokenServiceImpl implements ISyncTokenService{
	
	private final AuthService authService;
	private final TokenService tokenService;

	public SyncTokenServiceImpl(AuthService authService, TokenService tokenService) {
		this.authService = authService;
		this.tokenService = tokenService;
	}

	@Override
	public User authenticate(Credentials credentials) {
		//return authService.authenticate(credentials).join();
		return new User(credentials.getUsername());
	}

	@Override
	public UserToken requestToken(User user) {
		//return tokenService.requestToken(user).join();
        // Tạo mã thông báo với định dạng UTC
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String token = user.getUserId() + "_" + sdf.format(new Date());
        
        UserToken userToken = new UserToken(token);
        
        log.info("user token {} ", userToken);
        
        return userToken;
	}

}
