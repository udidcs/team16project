package com.example.team16project.controller.reply;

import com.example.team16project.repository.reply.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ReplyRepository replyRepository;

}
