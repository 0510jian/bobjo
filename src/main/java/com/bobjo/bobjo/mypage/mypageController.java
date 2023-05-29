package com.bobjo.bobjo.mypage;

import com.bobjo.bobjo.DTO.*;
import com.bobjo.bobjo.community.communityService;
import groovy.util.Eval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class mypageController {

    @Autowired
    private mypageService service;

    @Autowired
    private communityService comService;

    @GetMapping("/mypage/privacy")
    public ModelAndView privacy(
            HttpServletRequest request
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        // 로그인 ok
        if(session != null) {
            mv = new ModelAndView("mypage/privacy");

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = comService.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(comService.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            // mv에 walkmateList 추가
            mv.addObject("walkmateList", walkmateList);
            mv.addObject("user", user);
        }

        return mv;
    }

    @PostMapping("/mypage/privacy")
    public String privacyProcess(
            HttpServletRequest request,
            userDTO user
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if(session != null) {
            if(user.getUPw() == null) {
                // 비밀번호 변경 X
                service.updateUserNotPw(user);
            } else {
                // 비밀번호 변경 O
                service.updateUser(user);
            }
            session.setAttribute("user", user);
        }

        return "redirect:/mypage/privacy";
    }

    @GetMapping("/mypage/myboard")
    public ModelAndView myboard(
            HttpServletRequest request
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            mv = new ModelAndView("/mypage/manageBoard");

            ArrayList<boardDTO> boardList = service.selectAllBoardByUSeq(user.getUSeq());
            for (boardDTO board: boardList) {
                board.setRCount(service.selectRCount("board", board.getBSeq()));
            }
            mv.addObject("boardList", boardList);

            ArrayList<sosDTO> sosList = service.selectAllSosByUSeq(user.getUSeq());
            for (sosDTO sos: sosList) {
                sos.setRCount(service.selectRCount("sos", sos.getSSeq()));
            }
            mv.addObject("sosList", sosList);

            ArrayList<walkDTO> walkList = service.selectAllWalkByUSeq(user.getUSeq());
            for (walkDTO walk: walkList) {
                walk.setRCount(service.selectRCount("walk", walk.getWSeq()));
            }
            mv.addObject("walkList", walkList);

            ArrayList<newfmDTO> newfmList = service.selectAllNewfmByUSeq(user.getUSeq());
            for (newfmDTO newfm: newfmList) {
                newfm.setRCount(service.selectRCount("newfm", newfm.getNSeq()));
            }
            mv.addObject("newfmList", newfmList);

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = comService.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(comService.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            // mv에 walkmateList 추가
            mv.addObject("walkmateList", walkmateList);
        }

        return mv;
    }

    @GetMapping("/mypage/asking")
    public ModelAndView asking(
            HttpServletRequest request
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // userDTO
            userDTO user= (userDTO) session.getAttribute("user");

            mv = new ModelAndView("/mypage/asking");

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = comService.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(comService.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            // mv에 walkmateList 추가
            mv.addObject("walkmateList", walkmateList);
            mv.addObject("user", user);
        }
        return mv;
    }

    // 작성글관리 페이지에서 게시글 삭제
    @DeleteMapping("/mypage/myboard")
    public String deleteSelectBoard(
            HttpServletRequest request,
            @RequestParam(name="deleteList") String deleteList,
            @RequestParam(name="myCate") String myCate
    ) throws Exception {
        String[] deletes = deleteList.split(" ");
        for(int i=0; i<deletes.length; i++) {

            if(myCate.equals("board")) {
                service.updateBRemoveByBSeq(Integer.parseInt(deletes[i]));
            } else if(myCate.equals("sos")) {
                service.deleteSosBySSeq(Integer.parseInt(deletes[i]));
            } else if(myCate.equals("walk")) {
                service.deleteWalkByWSeq(Integer.parseInt(deletes[i]));
            } else {
                service.deleteNewfmByNSeq(Integer.parseInt(deletes[i]));
            }
        }

        return "redirect:/mypage/myboard";
    }










    // ===================================================================

    // 산책메이트 관리
    @GetMapping("/mypage/walkmate")
    public ModelAndView manageWalkmate(
            HttpServletRequest request
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // get userDTO
            userDTO user = (userDTO) session.getAttribute("user");

            mv = new ModelAndView("/mypage/manageMate");

            // select walkmate by uSeq
            ArrayList<walkmateDTO> walkmateList = service.selectWalkmateByUSeq(user.getUSeq());

            // select proposeAlias, acceptAlias
            for(walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(service.selectAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setAcceptAlias(service.selectAliasByUSeq(walkmate.getWmAccept()));
                walkmate.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv.addObject("user", user);
            mv.addObject("walkmateList", walkmateList);
        }
        return mv;
    }

    // 산책메이트 전화번호 창
    @GetMapping("/mypage/walkmate/{wmSeq}")
    public ModelAndView walkmateNumber(
            HttpServletRequest request,
            @PathVariable(value = "wmSeq") int wmSeq
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // get userDTO
            userDTO user = (userDTO) session.getAttribute("user");

            mv = new ModelAndView("/mypage/manageMateNumber");

            // select walkmate by wmSeq
            walkmateDTO walkmate = service.selectWalkmateByWmSeq(wmSeq);

            // set alias
            walkmate.setProposeAlias(service.selectAliasByUSeq(walkmate.getWmPropose()));
            walkmate.setAcceptAlias(service.selectAliasByUSeq(walkmate.getWmAccept()));

            // set number(tel)
            walkmate.setProposeNumber(service.selectTelByUSeq(walkmate.getWmPropose()));
            walkmate.setAcceptNumber(service.selectTelByUSeq(walkmate.getWmAccept()));

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = comService.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO wm : walkmateList) {
                wm.setProposeAlias(comService.selectUAliasByUSeq(walkmate.getWmPropose()));
                wm.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                wm.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            // mv에 walkmateList 추가
            mv.addObject("walkmateList", walkmateList);



            mv.addObject("user", user);
            mv.addObject("walkmate", walkmate);
        }
        return mv;
    }

    // 산책메이트 종료
    @PutMapping("/mypage/walkmate/{wmSeq}")
    public String walkmateComplete(
            HttpServletRequest request,
            @PathVariable(value = "wmSeq") int wmSeq
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.updateWalkmateByWmSeq(wmSeq);
        }

        return "redirect:/mypage/walkmate";
    }
}
