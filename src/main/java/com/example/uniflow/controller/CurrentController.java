package com.example.uniflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrentController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/current")
    public Map<String, String> getCurrent() {
        String url = "http://localhost:8000/count";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Construct the response in the required format
        Map<String, String> result = new HashMap<>();
        result.put("current_gas", String.valueOf(response.get("current_gas")));
        result.put("current_diesel", String.valueOf(response.get("current_diesel")));

        return result;
    }
}
