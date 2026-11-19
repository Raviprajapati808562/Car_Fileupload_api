package com.boot.service;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

	private final ChatClient chatClient;

	public AIService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	public String askAI(String question) {

		@Nullable
		String content = chatClient.prompt().system("""
				You are a helpful assistant.
				Give concise and accurate answers.
				Use simple language.
				Keep answers within 150 words.
				""").user(question).call().content();
		return content;
	}
}