package com.boot.controller;

import com.boot.service.AIRequest;
import com.boot.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai/api")
@CrossOrigin
public class AiController {
    private final AIService aiService;

    public AiController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public String askAI(@RequestBody AIRequest request) {
System.out.println(request.toString());
        return aiService.askAI(request.getQuestion());
    }
}
