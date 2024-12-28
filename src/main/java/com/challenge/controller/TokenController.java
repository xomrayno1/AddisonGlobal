package com.challenge.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.anotion.TimeMonitor;
import com.challenge.model.Credentials;
import com.challenge.model.UserToken;
import com.challenge.service.IAsyncTokenService;
import com.challenge.service.ISimpleAsyncTokenService;
import com.challenge.service.ISyncTokenService;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {
 
	private ISyncTokenService iSyncTokenService;
	
	private IAsyncTokenService iAsyncTokenService;
	
	private ISimpleAsyncTokenService iSimpleAsyncTokenService;
	
	public TokenController(IAsyncTokenService iAsyncTokenService, ISyncTokenService iSyncTokenService, ISimpleAsyncTokenService iSimpleAsyncTokenService) {
		this.iAsyncTokenService = iAsyncTokenService;
		this.iSyncTokenService = iSyncTokenService;
		this.iSimpleAsyncTokenService = iSimpleAsyncTokenService;
	}

	@PostMapping("/issue-simple-async")
	public CompletableFuture<UserToken> issueTokenSimpleAsync(@RequestBody Credentials credentials) {
		return iSimpleAsyncTokenService.issueToken(credentials);
	}
	
	@PostMapping("/issue-async")
	@TimeMonitor
	public Future<UserToken> issueTokenAsync(@RequestBody Credentials credentials) {
		return iAsyncTokenService.issueToken(credentials);
	}
	
	@PostMapping("/issue-sync")
	public UserToken issueTokenSync(@RequestBody Credentials credentials) {
		return iSyncTokenService.issueToken(credentials);
	}
}
