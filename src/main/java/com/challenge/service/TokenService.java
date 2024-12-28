package com.challenge.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.challenge.model.User;
import com.challenge.model.UserToken;


@Service
public class TokenService {
	
	
	private static final Logger log = LoggerFactory.getLogger(TokenService.class);

	
	public CompletableFuture<UserToken> requestToken(User user) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Giả lập độ trễ ngẫu nhiên từ 0 đến 5000 ms
                int delay = new Random().nextInt(5001); // Random delay between 0 and 5000 ms
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Nếu userId bắt đầu bằng "A", sẽ thất bại
            if (user.getUserId().startsWith("A")) {
                throw new IllegalArgumentException("User ID starts with A, cannot generate token");
            }

            // Tạo mã thông báo với định dạng UTC
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String token = user.getUserId() + "_" + sdf.format(new Date());
            
            UserToken userToken = new UserToken(token);
            
            log.info("user token {} ", userToken);
            
            return userToken;
        });
    }

}
