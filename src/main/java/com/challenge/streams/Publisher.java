package com.challenge.streams;

import java.util.concurrent.Flow.Subscriber;

//T – type of element Publisher sends

public interface Publisher<T> {
	void subscribe(Subscriber<? super T> s);
}
