package com.bobjo.bobjo.community;

import com.bobjo.bobjo.DTO.*;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class communityController {

    @Autowired
    private communityService service;

    @Value("${spring.servlet.multipart.location}")
    String root;

    // sos 게시판 화면
    @GetMapping("/community/sos")
    public ModelAndView sosList(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // sos 리스트 가져오기
            ArrayList<sosDTO> sosList = service.selectAllSos();

            // uSeq으로 uAlias 반환하여 sos의 uAlias에 저장
            for (sosDTO sos : sosList) {
                sos.setUAlias(service.selectUAliasByUSeq(sos.getUSeq()));
            }

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = service.selectWalkmateByUSeq(user.getUSeq());
            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(service.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(service.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(service.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv = new ModelAndView("/community/sosList");
            mv.addObject("user", user);
            mv.addObject("sosList", sosList);
            mv.addObject("walkmateList", walkmateList);
        } else {
            // 세션 정보가 없을 경우

        }
        return mv;
    }

    // sos 글쓰기 화면
    @GetMapping("/community/sos/write")
    public ModelAndView sosWrite(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if (session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            mv = new ModelAndView("/community/sosWrite");
            mv.addObject("user", user);
        } else {

        }
        return mv;
    }

    // sos 글쓰기 프로세스
    @PostMapping("/community/sos/write")
    public String sosWriteProcess (
            HttpServletRequest request,
            MultipartHttpServletRequest multipartHttpServletRequest,
            sosDTO sos
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if (session != null) {

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            sos.setUSeq(user.getUSeq());

            // insert sosDTO
            service.insertSos(sos, multipartHttpServletRequest, request);
        } else {
            return "redirect:/";
        }
        return "redirect:/community/sos";
    }


    // 자유게시판
    @GetMapping("/community/freeboard")
    public ModelAndView freeboard(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // sos 리스트 가져오기
            ArrayList<boardDTO> boardList = service.selectAllBoard();

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = service.selectWalkmateByUSeq(user.getUSeq());

            // uSeq으로 uAlias 반환하여 sos의 uAlias에 저장
            for (boardDTO board : boardList) {
                board.setUAlias(service.selectUAliasByUSeq(board.getUSeq()));
            }

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(service.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(service.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(service.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv = new ModelAndView("/community/freeList");
            mv.addObject("user", user);
            mv.addObject("boardList", boardList);
            mv.addObject("walkmateList", walkmateList);
        } else {
            // 세션 정보가 없을 경우

        }
        return mv;
    }

    // 자유게시판 글쓰기 화면
    @GetMapping("/community/freeboard/write")
    public ModelAndView freeboardWrite(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if (session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            mv = new ModelAndView("/community/freeWrite");
            mv.addObject("user", user);
        } else {

        }
        return mv;
    }


    // 자유게시판 글쓰기 프로세스
    @PostMapping("/community/freeboard/write")
    public String freeboardWriteProcess (
            HttpServletRequest request,
            MultipartHttpServletRequest multipartHttpServletRequest,
            boardDTO board
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if (session != null) {

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            board.setUSeq(user.getUSeq());



            // insert sosDTO
            service.insertBoard(board, multipartHttpServletRequest, request);
        } else {
            return "redirect:/";
        }
        return "redirect:/community/freeboard";
    }

    // 자유게시판 디테일
    @GetMapping("/community/freeboard/{bSeq}")
    public ModelAndView freeboardDetail(
            HttpServletRequest request,
            @PathVariable(value = "bSeq") int bSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {

            // 세션에서 user 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            user.setUAlias(service.selectUAliasByUSeq(user.getUSeq()));

            boardDTO board = service.selectBoardByBSeq(bSeq);

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = service.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(service.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(service.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(service.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv = new ModelAndView("/community/freeDetail");

            mv.addObject("user", user);
            mv.addObject("board", board);
            mv.addObject("walkmateList", walkmateList);
        }

        return mv;
    }

    // 자유 게시글 삭제
    @DeleteMapping("/community/freeboard/{bSeq}")
    public String boardDelete(
            HttpServletRequest request,
            @PathVariable(value = "bSeq") int bSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            boardDTO board = service.selectBoardByBSeq(bSeq);

            Path filePath = Paths.get(request.getServletContext().getRealPath("/") + root + board.getBImage());
            try {
                Files.deleteIfExists(filePath);
            } catch (Exception e) {
            }
            service.deleteFile(board.getBSeq());
            service.updateBRemoveByBSeq(bSeq);
        }

        return "redirect:/community/freeboard";
    }

    // 자유 게시글 수정 페이지
    @GetMapping("/community/freeboard/update/{bSeq}")
    public ModelAndView updateBoard(
            HttpServletRequest request,
            @PathVariable(value = "bSeq") int bSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션에서 user 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            user.setUAlias(service.selectUAliasByUSeq(user.getUSeq()));

            boardDTO board = service.selectBoardByBSeq(bSeq);

            mv = new ModelAndView("/community/freeUpdate");

            mv.addObject("board", board);
            mv.addObject("user", user);
        }
        return mv;
    }

    // 자유 게시글 수정 프로세스
    @PutMapping("/community/freeboard/{bSeq}")
    public String updateBoardProcess(
            HttpServletRequest request,
            @PathVariable(value = "bSeq") int bSeq,
            boardDTO board
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.updateBoard(board);
        }

        return "redirect:/community/freeboard/" + bSeq;
    }


    // sos 디테일
    @GetMapping("/community/sos/{sSeq}")
    public ModelAndView sosDetail(
            HttpServletRequest request,
            @PathVariable(value = "sSeq") int sSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {

            // 세션에서 user 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            user.setUAlias(service.selectUAliasByUSeq(user.getUSeq()));

            sosDTO sos = service.selectSosBySSeq(sSeq);

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = service.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(service.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(service.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(service.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv = new ModelAndView("/community/sosDetail");

            mv.addObject("user", user);
            mv.addObject("sos", sos);
            mv.addObject("walkmateList", walkmateList);
        }

        return mv;
    }

    // sos 해결완료 프로세스
    @PutMapping("/community/sos/{sSeq}")
    public String sosCompleteProcess(
            HttpServletRequest request,
            @PathVariable(value = "sSeq") int sSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.updateSosSFin(sSeq);
        }

        return "redirect:/community/sos";
    }





    @GetMapping("/community/walkmate")
    public ModelAndView walkmateList(
            HttpServletRequest request
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // user get
            userDTO user = (userDTO) session.getAttribute("user");

            // walk list get
            ArrayList<walkDTO> walkList = service.selectAllWalk();

            for (walkDTO walk : walkList) {
                // 닉네임 넣어주기
                walk.setUAlias(service.selectUAliasByUSeq(walk.getUSeq()));
                // 종 넣어주기
                walk.setA2Species(service.selectA2SpeciesByA3Seq(walk.getA3Seq()));
            }

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = service.selectWalkmateByUSeq(user.getUSeq());
            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(service.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(service.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(service.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv = new ModelAndView("/community/mateList");
            mv.addObject("user", user);
            mv.addObject("walkList", walkList);
            mv.addObject("walkmateList", walkmateList);
        }
        return mv;
    }



    @GetMapping("/community/walkmate/write")
    public ModelAndView walkmateWrite(
            HttpServletRequest request
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // user get
            userDTO user = (userDTO) session.getAttribute("user");

            // animal3list get by useq
            ArrayList<animal3DTO> animal3List = service.selectAllAnimal3ByUSeq(user.getUSeq());

            mv = new ModelAndView("/community/mateWrite");
            mv.addObject("user", user);
            mv.addObject("animal3List", animal3List);
        }
        return mv;
    }



    @PostMapping("/community/walkmate/write")
    public String walkmateWriteProcess(
            HttpServletRequest request,
            walkDTO walk
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if(session!= null) {
            //user get
            userDTO user = (userDTO) session.getAttribute("user");
            walk.setUSeq(user.getUSeq());

            service.insertWalk(walk);
        }
        return "redirect:/community/walkmate";
    }



    @GetMapping("/community/walkmate/{wSeq}")
    public ModelAndView walkmateDetail(
            HttpServletRequest request,
            @PathVariable(value = "wSeq") int wSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // user get
            userDTO user = (userDTO) session.getAttribute("user");

            // walk get
            walkDTO walk = service.selectWalkByWSeq(wSeq);
            walk.setUAlias(service.selectUAliasByUSeq(walk.getUSeq()));
            walk.setA2Species(service.selectA2SpeciesByA3Seq(walk.getA3Seq()));

            // animal3 get
            animal3DTO animal3 = service.selectAnimal3ByA3Seq(walk.getA3Seq());

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = service.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(service.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(service.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(service.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv = new ModelAndView("/community/mateDetail");
            mv.addObject("user", user);
            mv.addObject("walk", walk);
            mv.addObject("animal3", animal3);
            mv.addObject("walkmateList", walkmateList);
        }
        return mv;
    }


    // 메이트 게시글 삭제
    @DeleteMapping("/community/walkmate/{wSeq}")
    public String walkmateDelete(
            HttpServletRequest request,
            @PathVariable(value = "wSeq") int wSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteWalk(wSeq);
        }

        return "redirect:/community/walkmate";
    }



    // 메이트 신청
    @GetMapping("/community/walkmate/{wSeq}/put")
    public ModelAndView putWalkmate(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션 정보가 있을 경우
            mv = new ModelAndView("/community/matePut");

            // 세션에서 user 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // uSeq으로 animal3 select
            ArrayList<animal3DTO> animal3List = service.selectAllAnimal3ByUSeq(user.getUSeq());

            mv.addObject("user", user);
            mv.addObject("animal3List", animal3List);
        }
        return mv;
    }


    // 메이트 신청
    @PostMapping("/community/walkmate/{wSeq}/put")
    public String putWalkmateProcess(
            HttpServletRequest request,
            walkmateDTO walkmate,
            @PathVariable(value = "wSeq") int wSeq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        if(session != null) {
            //get user
            userDTO user = (userDTO) session.getAttribute("user");
            walkmate.setWmPropose(user.getUSeq());

            walkmate.setWSeq(wSeq);
            walkmate.setWmAccept(service.selectUSeqByWSeq(wSeq));

            if(walkmate.getA3Seq() != -1) {
                service.insertWalkmate(walkmate);
            }
        }

        return "redirect:/community/walkmate";
    }















    // board 댓글
    @GetMapping("/community/freeboard/{bSeq}/comment")
    public ModelAndView boardComment(
            HttpServletRequest request,
            @PathVariable(value = "bSeq") int bSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/community/comment");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            ArrayList<replyDTO> replyList = service.selectReplyBySeq("board", bSeq);
            // uSeq으로 uAlias 반환하여 reply의 uAlias에 저장
            for (replyDTO reply : replyList) {
                reply.setUAlias(service.selectUAliasByUSeq(reply.getUSeq()));
            }
            mv.addObject("replyList", replyList);
        }

        return mv;
    }

    // 댓글 등록
    @PostMapping("/community/freeboard/{bSeq}/comment")
    public String boardCommentWrite(
            HttpServletRequest request,
            @PathVariable(value = "bSeq") int bSeq,
            replyDTO reply
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if (session != null) {
            //user get
            userDTO user = (userDTO) session.getAttribute("user");
            reply.setUSeq(user.getUSeq());
            reply.setRCate("board");
            reply.setCoSeq(bSeq);

            service.insertReply(reply);
        }

        return "redirect:/community/freeboard/"+bSeq+"/comment";
    }

    // 댓글 삭제
    @DeleteMapping("/community/freeboard/{bSeq}/comment/{rSeq}")
    public String boardCommentDelete(
            HttpServletRequest request,
            @PathVariable(value = "bSeq") int bSeq,
            @PathVariable(value = "rSeq") int rSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteReply(rSeq);
        }

        return "redirect:/community/freeboard/"+bSeq+"/comment";
    }





    // sos 댓글
    @GetMapping("/community/sos/{sSeq}/comment")
    public ModelAndView sosComment(
            HttpServletRequest request,
            @PathVariable(value = "sSeq") int sSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/community/comment");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            ArrayList<replyDTO> replyList = service.selectReplyBySeq("sos", sSeq);
            // uSeq으로 uAlias 반환하여 reply의 uAlias에 저장
            for (replyDTO reply : replyList) {
                reply.setUAlias(service.selectUAliasByUSeq(reply.getUSeq()));
            }
            mv.addObject("replyList", replyList);
        }

        return mv;
    }

    // 댓글 등록
    @PostMapping("/community/sos/{sSeq}/comment")
    public String sosCommentWrite(
            HttpServletRequest request,
            @PathVariable(value = "sSeq") int sSeq,
            replyDTO reply
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if (session != null) {
            //user get
            userDTO user = (userDTO) session.getAttribute("user");
            reply.setUSeq(user.getUSeq());
            reply.setRCate("sos");
            reply.setCoSeq(sSeq);

            service.insertReply(reply);
        }

        return "redirect:/community/sos/"+sSeq+"/comment";
    }








    // adopt out 댓글
    @GetMapping("/adopt/out/{nSeq}/comment")
    public ModelAndView adoptoutComment(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/community/comment");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            ArrayList<replyDTO> replyList = service.selectReplyBySeq("newfm", nSeq);
            // uSeq으로 uAlias 반환하여 reply의 uAlias에 저장
            for (replyDTO reply : replyList) {
                reply.setUAlias(service.selectUAliasByUSeq(reply.getUSeq()));
            }
            mv.addObject("replyList", replyList);
        }

        return mv;
    }

    // 댓글 등록
    @PostMapping("/adopt/out/{nSeq}/comment")
    public String adoptoutCommentWrite(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq,
            replyDTO reply
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if (session != null) {
            //user get
            userDTO user = (userDTO) session.getAttribute("user");
            reply.setUSeq(user.getUSeq());
            reply.setRCate("newfm");
            reply.setCoSeq(nSeq);

            service.insertReply(reply);
        }

        return "redirect:/adopt/out/"+nSeq+"/comment";
    }

    // 댓글 삭제
    @DeleteMapping("/adopt/out/{nSeq}/comment/{rSeq}")
    public String adoptoutCommentDelete(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq,
            @PathVariable(value = "rSeq") int rSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteReply(rSeq);
        }

        return "redirect:/adopt/out/"+nSeq+"/comment";
    }




    // adopt in 댓글
    @GetMapping("/adopt/in/{nSeq}/comment")
    public ModelAndView adoptinComment(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/community/comment");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            ArrayList<replyDTO> replyList = service.selectReplyBySeq("newfm", nSeq);
            // uSeq으로 uAlias 반환하여 reply의 uAlias에 저장
            for (replyDTO reply : replyList) {
                reply.setUAlias(service.selectUAliasByUSeq(reply.getUSeq()));
            }
            mv.addObject("replyList", replyList);
        }

        return mv;
    }

    // 댓글 등록
    @PostMapping("/adopt/in/{nSeq}/comment")
    public String adoptinCommentWrite(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq,
            replyDTO reply
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if (session != null) {
            //user get
            userDTO user = (userDTO) session.getAttribute("user");
            reply.setUSeq(user.getUSeq());
            reply.setRCate("newfm");
            reply.setCoSeq(nSeq);

            service.insertReply(reply);
        }

        return "redirect:/adopt/in/"+nSeq+"/comment";
    }

    // 댓글 삭제
    @DeleteMapping("/adopt/in/{nSeq}/comment/{rSeq}")
    public String adoptinCommentDelete(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq,
            @PathVariable(value = "rSeq") int rSeq
    ) throws Exception {
        // session get
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteReply(rSeq);
        }

        return "redirect:/adopt/in/"+nSeq+"/comment";
    }



















    // =======================================================================

    // 산책 메이트 거절
    @DeleteMapping("/walkmate/refuse/{wmSeq}")
    public String walkmateRefuse(
            HttpServletRequest request,
            @PathVariable(value = "wmSeq") int wmSeq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteWalkmate(wmSeq);
        }

        return "redirect:/community/freeboard";
    }

    // 산책 메이트 수락
    @PutMapping("/walkmate/permit/{wmSeq}")
    public String walkmatePermit(
            HttpServletRequest request,
            @PathVariable(value = "wmSeq") int wmSeq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.updateWalkmate(wmSeq);
        }

        return "redirect:/community/freeboard";
    }
}
