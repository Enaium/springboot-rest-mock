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
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getComments() throws Exception {
        mockMvc.perform(get("/posts/1/comments/")).andExpect(status().isOk());
    }

    @Test
    void getComment() throws Exception {
        mockMvc.perform(get("/posts/comments/1/")).andExpect(content().string("""
                {"id":1,"content":"comment1-1","member":{"id":1},"post":{"id":1}}"""));
    }

    @Test
    void publish() throws Exception {
        // Get the id of the comment you just published
        final int id = Integer.parseInt(mockMvc.perform(put("/posts/1/comments/").content("""
                        {"content":"comment1-1","memberId":1,"postId":1}""").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        // Get the comment you just published
        mockMvc.perform(get("/posts/comments/" + id + "/")).andExpect(content().string("""
                {"id":%d,"content":"comment1-1","member":{"id":1},"post":{"id":1}}""".formatted(id)));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/comments/1/")).andExpect(status().isOk());
        mockMvc.perform(get("/posts/comments/1/")).andExpect(status().isNotFound());
    }
}