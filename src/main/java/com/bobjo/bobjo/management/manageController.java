package com.bobjo.bobjo.management;

import com.bobjo.bobjo.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class manageController {

    @Autowired
    private manageService service;

    @GetMapping("/manage")
    public ModelAndView manage(
            HttpServletRequest request
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/management/adminMain");

        return mv;
    }

    @GetMapping("/manage/member")
    public ModelAndView manageMember(
            HttpServletRequest request
    ) throws Exception {
        ModelAndView mv = null;

        HttpSession session = request.getSession(false);

        if(session != null) {
            mv = new ModelAndView("/management/adminMember");

            ArrayList<userDTO> userList = service.selectAllUser();
            mv.addObject("userList", userList);
        }
        return mv;
    }

    @GetMapping("/manage/board")
    public ModelAndView manageBoard(
            HttpServletRequest request
    ) throws Exception {
        ModelAndView mv = null;

        HttpSession session = request.getSession(false);

        if (session != null) {
            if(session.getAttribute("admin").equals("admin")) {
                mv = new ModelAndView("/management/adminBoard");

                ArrayList<boardDTO> boardList = service.selectAllBoard();
                for (boardDTO board : boardList) {
                    board.setUAlias(service.selectUAliasByUSeq(board.getUSeq()));
                }
                ArrayList<sosDTO> sosList = service.selectAllSos();
                for (sosDTO sos : sosList) {
                    sos.setUAlias(service.selectUAliasByUSeq(sos.getUSeq()));
                }
                ArrayList<newfmDTO> newfmList = service.selectAllNewfm();
                for (newfmDTO newfm : newfmList) {
                    newfm.setUAlias(service.selectUAliasByUSeq(newfm.getUSeq()));
                }

                mv.addObject("boardList", boardList);
                mv.addObject("sosList", sosList);
                mv.addObject("newfmList", newfmList);
            }
        }

        return mv;
    }

    @GetMapping("/manage/certification")
    public ModelAndView manageCertification(
            HttpServletRequest request
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/management/adminPhotoList");

            ArrayList<certificationDTO> certificationList = service.selectAllCertification();

            for (certificationDTO certification: certificationList) {
                certification.setUAlias(service.selectUAliasByUSeq(certification.getUSeq()));
                certification.setA1Species(service.selectA1SpeciesByA1Seq(certification.getA1Seq()));
            }
            mv.addObject("certificationList" ,certificationList);
        }
        return mv;
    }

    @GetMapping("/manage/certification/{ceSeq}")
    public ModelAndView manageCertificationDetail(
            HttpServletRequest request,
            @PathVariable(value = "ceSeq") int ceSeq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/management/adminPhotoDetail");

            certificationDTO certification = service.selectCertificationByCeSeq(ceSeq);
            certification.setUAlias(service.selectUAliasByUSeq(certification.getUSeq()));
            certification.setA1Species(service.selectA1SpeciesByA1Seq(certification.getA1Seq()));
            mv.addObject("certification", certification);

            animal1DTO animal1 = service.selectAnimal1ByA1Seq(certification.getA1Seq());
            mv.addObject("animal1", animal1);
        }
        return mv;
    }

    @PutMapping("/manage/certification/{ceSeq}")
    public String manageCertificationPut(
            HttpServletRequest request,
            @PathVariable(value = "ceSeq") int ceSeq,
            @RequestParam(name = "a1Seq") int a1Seq,
            @RequestParam(name = "uSeq") int uSeq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        if(session != null) {
            // certification의 ce_result를 1로 수정
            service.updateCertification(ceSeq);

            // user의 u_cert를 수정
            userDTO user = service.selectUserByUSeq(uSeq);
            String uCert= user.getUCert();
            String newUCert = "";

            // ucert가 비었을 경우
            if(uCert == null || uCert.equals("")) {
                newUCert = String.valueOf(a1Seq);
            } else if(uCert.equals(String.valueOf(a1Seq))) {
                // 하나만 있는데 같을 경우
                newUCert = uCert;
            } else if(!uCert.contains("/")) {
                // 하나만 있는데 다를 경우
                newUCert = uCert + "/" + String.valueOf(a1Seq);
            } else if(uCert.contains("/")) {
                // 여러개 있을 경우 분할
                String[] certList = uCert.split("/");

                // 없을 경우
                newUCert = uCert + "/" + String.valueOf(a1Seq);
            }

            service.updateUserUCert(uSeq, newUCert);

        }
        return "redirect:/manage/certification";
    }

    @DeleteMapping("/manage/certification/{ceSeq}")
    public String manageCertificationDelete(
            HttpServletRequest request,
            @PathVariable(value = "ceSeq") int ceSeq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteCertification(ceSeq);
        }
        return "redirect:/manage/certification";
    }


    @GetMapping("/manage/quiz")
    public ModelAndView manageCertification2(
            HttpServletRequest request
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/management/adminQuiz");

            ArrayList<animal1DTO> animal1List = service.selectAllAnimal1();
            ArrayList<quizDTO> quizList = service.selectAllQuiz();
            animal1List.remove(5);

            mv.addObject("animal1List", animal1List);
            mv.addObject("quizList", quizList);
        }

        return mv;
    }

    @PostMapping("/manage/quiz")
    public String manageQuiz(
            HttpServletRequest request,
            quizDTO quiz
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.insertQuiz(quiz);
        }

        return "redirect:/manage/quiz";
    }




    @DeleteMapping("/manage/board/{oCate}/{oSeq}")
    public String deleteBoard(
            HttpServletRequest request,
            @PathVariable(value = "oCate") String oCate,
            @PathVariable(value = "oSeq") int oSeq
    ) throws Exception {

        HttpSession session = request.getSession(false);

        if(session != null) {
            if(oCate.equals("board")) {
                // board 삭제
                service.deleteBoardByBSeq(oSeq);
            } else if(oCate.equals("sos")) {
                // delete sos
                service.deleteSosBySSeq(oSeq);
            } else {
                // delete newfm
                service.deleteNewfmByNSeq(oSeq);
            }
        }
        return "redirect:/manage/board";
    }

    @DeleteMapping("/manage/member/{uSeq}")
    public String deleteUser(
            HttpServletRequest request,
            @PathVariable(value = "uSeq") int uSeq
    ) throws Exception {

        HttpSession session = request.getSession(false);

        if(session != null) {
            // delete user
            service.deleteUserByUSeq(uSeq);
        }
        return "redirect:/manage/member";
    }

    @GetMapping("/manage/quiz/{qSeq}")
    public String deleteQuiz(
            HttpServletRequest request,
            @PathVariable(value = "qSeq") int qSeq
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null) {
            // delete quiz
            service.deleteQuizByQSeq(qSeq);
        }
        return "redirect:/manage/quiz";
    }
}
