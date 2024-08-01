package br.com.emailmanagerapi.controllers;


import br.com.emailmanagerapi.entities.EmailMessage;
import br.com.emailmanagerapi.entities.EmailRequestSend;
import br.com.emailmanagerapi.entities.EmailRequestRead;
import br.com.emailmanagerapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestSend emailRequestSend) {

        emailService.sendSimpleMessage(emailRequestSend.getEmailConfig(), emailRequestSend.getTo(), emailRequestSend.getSubject(), emailRequestSend.getText(), emailRequestSend.getDisplayName());
        return ResponseEntity.ok().body("Email sent successfully");
    }

    @PostMapping("/read")
    public ResponseEntity<List<EmailMessage>> readEmails(@RequestBody EmailRequestRead emailRequestRead) {
        return ResponseEntity.ok().body(emailService.readEmails(emailRequestRead.getEmailConfig(), emailRequestRead.getFolder()));
    }

}

