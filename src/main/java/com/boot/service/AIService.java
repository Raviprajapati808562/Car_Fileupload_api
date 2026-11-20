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

		System.out.println("Question received: " + question);

		String predefinedAnswer =
				PredefinedAnswers.getAnswer(question);

		if (predefinedAnswer != null) {
			System.out.println("Returning predefined answer");
			return predefinedAnswer;
		}

		String response = chatClient.prompt()
				.user(question)
				.call()
				.content();
		return response;
	}
}