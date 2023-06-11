package cn.enaium.restmock.controller;

import cn.enaium.restmock.SpringbootRestMockApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Enaium
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringbootRestMockApplication.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPosts() throws Exception {
        mockMvc.perform(get("/posts/")).andExpect(status().isOk());
    }

    @Test
    void getPost() throws Exception {
        mockMvc.perform(get("/posts/1")).andExpect(content().string("""
                {"id":1,"title":"title1","content":"content1","member":{"id":1}}"""));
    }

    @Test
    void publish() throws Exception {
        // Get the id of the post
        final int id = Integer.parseInt(mockMvc.perform(put("/posts/").content("""
                                {"title":"title1","content":"content1","memberId":1}""")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString());

        // Get the post by id
        mockMvc.perform(get("/posts/" + id)).andExpect(content().string("""
                {"id":%d,"title":"title1","content":"content1","member":{"id":1}}""".formatted(id)));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1")).andExpect(status().isOk());

        // Get the post by id
        mockMvc.perform(get("/posts/1")).andExpect(status().isNotFound());
    }
}