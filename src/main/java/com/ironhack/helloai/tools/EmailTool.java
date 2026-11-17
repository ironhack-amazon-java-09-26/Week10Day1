package com.ironhack.helloai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class EmailTool {

    @Tool
    public String sendEmail(String email) {
        System.out.println("Sending email to: " + email);
        return "Email sent to: " + email;
    }
}
