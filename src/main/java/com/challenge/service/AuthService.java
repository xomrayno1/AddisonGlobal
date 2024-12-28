package com.challenge.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.challenge.model.Credentials;
import com.challenge.model.User;

@Service
public class AuthService {
	
	public CompletableFuture<User> authenticate(Credentials credentials) {
        return CompletableFuture.supplyAsync(() -> {
            // Giả lập độ trễ ngẫu nhiên từ 0 đến 5000 ms
            try {
                int delay = new Random().nextInt(5001); // Random delay between 0 and 5000 ms
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Kiểm tra xem mật khẩu có trùng với tên người dùng viết hoa không
            if (credentials.getUsername().toUpperCase().equals(credentials.getPassword())) {
                return new User(credentials.getUsername());
            } else {
                throw new IllegalArgumentException("Invalid credentials");
            }
        });
    }

}
