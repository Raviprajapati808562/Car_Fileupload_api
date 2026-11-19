package com.boot.controller;

import com.boot.service.AIRequest;
import com.boot.service.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/api")
public class AiController {
    private final AIService aiService;

    public AiController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public String askAI(@RequestBody AIRequest request) {

        return aiService.askAI(request.getQuestion());
    }
}
