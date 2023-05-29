package com.bobjo.bobjo.login;

import com.bobjo.bobjo.DTO.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    @Autowired
    private loginService service;



    // 초기 페이지
    @GetMapping("/")
    public String start(
            HttpServletRequest request
    ) throws Exception {

        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null){
            // 세션 정보 O
            return "redirect:/community/freeboard";
        } else {
            // 세션 정보 X
            return "redirect:/main";
        }
    }

    // 기본 페이지
    @GetMapping("/main")
    public ModelAndView main(
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/login/main");
        return mv;
    }


    // 로그인  페이지
    @GetMapping("/login")
    public ModelAndView login(
            HttpServletRequest request
    ) throws Exception {

        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            // 세션 정보 O일 경우 세션 해제
            session.invalidate();
        }

        // 세션 정보 X
        ModelAndView mv = new ModelAndView("/login/login");
        return mv;
    }



    // 로그인 수행
    @PostMapping("/login")
    public String loginProcess(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "pw") String pw,
            HttpServletRequest request
    ) throws Exception {

        if(id.equals("admin") && pw.equals("admin")) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60*60*1); // 세션 만료 1시간으로 지정
            session.setAttribute("admin", "admin");

            return "redirect:/manage";
        }

        // 비밀번호 암호화
        sha256 sha256 = new sha256();
        String encryptPw = sha256.encrypt(pw);

        // 해당하는 계정 정보 가져오기
        userDTO user = service.selectUser(id, encryptPw);

        // 로그인 성공 시 회원정보를 넣어 페이지 이동
        if (user != null) {
            // 세션 생성
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60*60*1); // 세션 만료 1시간으로 지정
            session.setAttribute("user", user);

            return "redirect:/community/freeboard";
        }

        // 로그인 실패 시 로그인 창으로 재이동
        return "redirect:/login";
    }



    // 회원가입 페이지
    @GetMapping("/joinus")
    public ModelAndView joinus(
            HttpServletRequest request
    ) throws Exception {

        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            // 세션 정보 O일 경우 세션 해제
            session.invalidate();
        }

        // 세션 정보 X
        ModelAndView mv = new ModelAndView("/login/joinus");
        return mv;
    }


    // 회원가입 수행
    @PostMapping("/joinus")
    public String joinusProcess(
            userDTO user,
            HttpServletRequest request
    ) throws Exception {

        // 비밀번호 암호화
        sha256 sha256 = new sha256();
        String encryptPw = sha256.encrypt(user.getUPw());
        user.setUPw(encryptPw);

        service.insertUser(user);

        return "redirect:/login";
    }



    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/main";
    }
}
