package com.bobjo.bobjo.contact;

import com.bobjo.bobjo.DTO.contactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class contactService {

    @Autowired
    private MailSender sender;

    // application.properties에서 username(계정명) 가져옴
    @Value("${spring.mail.username}")
    private String admin;

    // 이메일 발송 service
    public void sendEmail(contactDTO dto) {
        SimpleMailMessage mail = new SimpleMailMessage();
        
        mail.setTo(admin);
        mail.setFrom(admin);
        mail.setSubject("bobjo / 새 문의 등록 (" + dto.getConAlias() + ")"); // 닉네임
        mail.setText(dto.getConMessage()); // 내용

        // 발송
        sender.send(mail);
    }
}
