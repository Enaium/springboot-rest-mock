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
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void register() throws Exception {
        mockMvc.perform(put("/members/").content("""
                                   {
                                       "username": "Enaium",
                                       "password": "123456"
                                   }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
