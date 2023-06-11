package cn.enaium.restmock.controller;

import cn.enaium.restmock.SpringbootRestMockApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Enaium
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringbootRestMockApplication.class)
class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test login.
     */
    @Test
    void login() throws Exception {
        // Test login
        mockMvc.perform(put("/sessions/").content("""
                {
                    "username": "user1",
                    "password": "pass1"
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        // Test login with wrong password
        mockMvc.perform(put("/sessions/").content("""
                {
                    "username": "user1",
                    "password": "pass2"
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());

        // Test login with wrong username
        mockMvc.perform(put("/sessions/").content("""
                {
                    "username": "user2",
                    "password": "pass1"
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}