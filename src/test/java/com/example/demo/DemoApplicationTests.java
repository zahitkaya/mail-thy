package com.example.demo;

import com.example.demo.model.Mail;
import com.example.demo.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    EmailSenderService senderService;

    @Test
    void contextLoads() throws IOException, MessagingException {
        log.info("sending sample email");

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("name", "John Michel!");
        properties.put("location", "Sri Lanka");
        properties.put("sign", "Java Developer");

        Mail mail = Mail.builder()
                .from("zahitkaya58@gmail.com")
                .to("zahitkaya58@gmail.com")
                .htmlTemplate(new Mail.HtmlTemplate("sample", properties))
                .subject("This is sample email with spring boot and thymeleaf")
                .build();

        senderService.sendEmail(mail);
    }
}
