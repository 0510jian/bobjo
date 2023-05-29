package com.bobjo.bobjo.contact;

import com.bobjo.bobjo.DTO.contactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class contactController {
    @Autowired
    private contactService service;

    // 문의하기 버튼 클릭 시
    @PostMapping("/mypage/asking")
    public String send(contactDTO contact) throws Exception {

        service.sendEmail(contact);

        // 문의하기 종료 후 문의 폼으로 리다이렉트
        return "redirect:/mypage/asking";
    }
}
