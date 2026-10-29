package com.example.homelab;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final JdbcTemplate jdbcTemplate;

    public MessageController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/messages")
    public List<Map<String, Object>> messages() {
        return jdbcTemplate.queryForList(
            "SELECT id, message, created_at " +
            "FROM messages ORDER BY id DESC LIMIT 20"
        );
    }
}