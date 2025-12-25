package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class SimpleTest extends AbstractTestNGSpringContextTests {
    
    @Test
    public void contextLoads() {
        // This test will pass if the Spring context loads successfully
        assert true;
    }
    
    @Test
    public void basicTest() {
        // Simple test to verify TestNG is working
        assert 1 + 1 == 2;
    }
}