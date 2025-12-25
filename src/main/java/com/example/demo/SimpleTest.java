package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleTest {
    
    @Test
    public void contextLoads() {
        // This test will pass if the Spring context loads successfully
    }
    
    @Test
    public void basicTest() {
        // Simple test to verify JUnit is working
        assert 1 + 1 == 2;
    }
}