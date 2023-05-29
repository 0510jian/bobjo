package com.bobjo.bobjo.adopt;

import com.bobjo.bobjo.DTO.*;
import com.bobjo.bobjo.community.communityService;
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
import java.util.Arrays;
import java.util.List;

@Controller
public class adoptController {

    @Autowired
    private adoptService service;

    @Autowired
    private communityService comService;

    @Value("${spring.servlet.multipart.location}")
    String root;


    // 분양 페이지
    @GetMapping("/adopt/out")
    public ModelAndView adoptList(
            HttpServletRequest request
    ) throws Exception {

        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;
        if(session != null) {
            // 세션 정보가 있을 경우
            mv = new ModelAndView("/adopt/adoptList");

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            // 인증센터 확인
            String[] certList = null;
            if(user.getUCert() != null) {
                // 여러 개 인증됨
                if(user.getUCert().contains("/")) {
                    certList = user.getUCert().split("/");
                } else {
                    // 한개만 인증됨
                    certList = new String[] {user.getUCert()};
                }
            }
            mv.addObject("certList", certList);

            // get animal1List
            ArrayList<animal1DTO> animal1List = service.selectAllAnimal1();
            animal1List.remove(animal1List.size()-1);
            mv.addObject("animal1List", animal1List);

            // select 모든 입양 게시글(newfm)
            ArrayList<newfmDTO> newfmList = service.selectAllNewfmByNAdopt(false);
            // a2Seq으로 a2Species 반환하여 newfm의 a2Species에 저장
            for (newfmDTO newfm : newfmList) {
                newfm.setA2Species(service.selectA2SpeciesByA12Seq(newfm.getA1Seq(), newfm.getA2Seq()));
            }

            // animal1 별로 분류된 newfmList 생성
            ArrayList<ArrayList<newfmDTO>> finalNewfmList = new ArrayList<ArrayList<newfmDTO>>();
            for(animal1DTO animal1 : animal1List ) {
                ArrayList<newfmDTO> tempNewfm = new ArrayList<newfmDTO>();
                for(newfmDTO newfm : newfmList) {
                    if(newfm.getA1Seq() == animal1.getA1Seq()) {
                        tempNewfm.add(newfm);
                    }
                }
                finalNewfmList.add(tempNewfm);
            }
            mv.addObject("finalNewfmList", finalNewfmList);

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

    // 분양글 작성 페이지
    @GetMapping("/adopt/out/write")
    public ModelAndView adoptWrite(
            HttpServletRequest request
    ) throws Exception {

        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;
        if(session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // animal1 리스트 가져옴
            ArrayList<animal1DTO> animal1List = service.selectAllAnimal1();
            animal1List.remove(animal1List.size()-1);

            // animal1 개수대로 animal2 리스트 가져옴
            ArrayList<ArrayList<animal2DTO>> animal2ListList = new ArrayList<ArrayList<animal2DTO>>();
            for(int i=0; i<animal1List.size(); i++) {
                ArrayList<animal2DTO> animal2List = service.selectAllAnimal2ByA1Seq(animal1List.get(i).getA1Seq());
                animal2ListList.add(animal2List);
            }


            mv = new ModelAndView("/adopt/adoptWrite");

            mv.addObject("user", user);
            mv.addObject("animal1List", animal1List);
            mv.addObject("animal2ListList", animal2ListList);
        }
        return mv;
    }

    // 분양글 작성 프로세스
    @PostMapping("/adopt/out/write")
    public String adoptWriteProcess(
            HttpServletRequest request,
            MultipartHttpServletRequest multipartHttpServletRequest,
            newfmDTO newfm
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            newfm.setUSeq(user.getUSeq());
            newfm.setNAdopt(false);

            // insert newfm
            service.insertNewfm(newfm, multipartHttpServletRequest, request);

        } else {
            return "redirect:/";
        }
        return "redirect:/adopt/out";
    }

    @GetMapping("/adopt/out/{nSeq}")
    public ModelAndView adoptDetail(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션 정보가 있을 경우

            mv = new ModelAndView("/adopt/adoptDetail");

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // nSeq으로 현재 페이지의 newfm 가져오기
            newfmDTO newfm = service.selectNewfmByNSeq(nSeq);
            newfm.setA2Species(service.selectA2SpeciesByA12Seq(newfm.getA1Seq(), newfm.getA2Seq()));

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
            mv.addObject("newfm", newfm);
            mv.addObject("user", user);
        }

        return mv;
    }

    @DeleteMapping("/adopt/out/{nSeq}")
    public String adoptDelete(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if(session != null) {
            newfmDTO newfm = service.selectNewfmByNSeq(nSeq);

            Path filePath = Paths.get(request.getServletContext().getRealPath("/") + root + newfm.getNThumb());
            try {
                Files.deleteIfExists(filePath);
            } catch (Exception e) {
            }
            service.deleteFile(newfm.getNSeq());
            service.deleteAdopt(nSeq);
        }
        return "redirect:/adopt/out";
    }



    // 입양 =======================================================================

    // 입양 페이지
    @GetMapping("/adopt/in")
    public ModelAndView adoptListIn(
            HttpServletRequest request
    ) throws Exception {

        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;
        if(session != null) {
            // 세션 정보가 있을 경우
            mv = new ModelAndView("/adopt/adoptListIn");

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // 인증센터 확인
            String[] certList = null;
            if(user.getUCert() != null) {
                // 여러 개 인증됨
                if(user.getUCert().contains("/")) {
                    certList = user.getUCert().split("/");
                } else {
                    // 한개만 인증됨
                    certList = new String[] {user.getUCert()};
                }
            }
            mv.addObject("certList", certList);

            // get animal1List
            ArrayList<animal1DTO> animal1List = service.selectAllAnimal1();
            animal1List.remove(animal1List.size()-1);
            mv.addObject("animal1List", animal1List);

            // select 모든 입양 게시글(newfm)
            ArrayList<newfmDTO> newfmList = service.selectAllNewfmByNAdopt(true);
            // a2Seq으로 a2Species 반환하여 newfm의 a2Species에 저장
            for (newfmDTO newfm : newfmList) {
                newfm.setA2Species(service.selectA2SpeciesByA12Seq(newfm.getA1Seq(), newfm.getA2Seq()));
            }

            // animal1 별로 분류된 newfmList 생성
            ArrayList<ArrayList<newfmDTO>> finalNewfmList = new ArrayList<ArrayList<newfmDTO>>();
            for(animal1DTO animal1 : animal1List ) {
                ArrayList<newfmDTO> tempNewfm = new ArrayList<newfmDTO>();
                for(newfmDTO newfm : newfmList) {
                    if(newfm.getA1Seq() == animal1.getA1Seq()) {
                        tempNewfm.add(newfm);
                    }
                }
                finalNewfmList.add(tempNewfm);
            }
            mv.addObject("finalNewfmList", finalNewfmList);

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

    // 입양글 작성 페이지
    @GetMapping("/adopt/in/write")
    public ModelAndView adoptInWrite(
            HttpServletRequest request
    ) throws Exception {

        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;
        if(session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // animal1 리스트 가져옴
            ArrayList<animal1DTO> animal1List = service.selectAllAnimal1();
            animal1List.remove(animal1List.size()-1);

            // animal1 개수대로 animal2 리스트 가져옴
            ArrayList<ArrayList<animal2DTO>> animal2ListList = new ArrayList<ArrayList<animal2DTO>>();
            for(int i=0; i<animal1List.size(); i++) {
                ArrayList<animal2DTO> animal2List = service.selectAllAnimal2ByA1Seq(animal1List.get(i).getA1Seq());
                animal2ListList.add(animal2List);
            }

            mv = new ModelAndView("/adopt/adoptWriteIn");

            mv.addObject("user", user);
            mv.addObject("animal1List", animal1List);
            mv.addObject("animal2ListList", animal2ListList);
        }
        return mv;
    }

    // 입양글 작성 프로세스
    @PostMapping("/adopt/in/write")
    public String adoptInWriteProcess(
            HttpServletRequest request,
            newfmDTO newfm
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            // 세션 정보가 있을 경우

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            newfm.setUSeq(user.getUSeq());
            newfm.setNAdopt(true);

            // insert newfm
            service.insertNewfm(newfm, null, request);

        } else {
            return "redirect:/";
        }
        return "redirect:/adopt/in";
    }

    @GetMapping("/adopt/in/{nSeq}")
    public ModelAndView adoptInDetail(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션 정보가 있을 경우

            mv = new ModelAndView("/adopt/adoptDetailIn");

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // nSeq으로 현재 페이지의 newfm 가져오기
            newfmDTO newfm = service.selectNewfmByNSeq(nSeq);
            newfm.setA2Species(service.selectA2SpeciesByA12Seq(newfm.getA1Seq(), newfm.getA2Seq()));

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
            mv.addObject("newfm", newfm);
            mv.addObject("user", user);
        }

        return mv;
    }

    @DeleteMapping("/adopt/in/{nSeq}")
    public String adoptinDelete(
            HttpServletRequest request,
            @PathVariable(value = "nSeq") int nSeq
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteAdopt(nSeq);
        }
        return "redirect:/adopt/in";
    }




    // 인증센터 =================================================================================================

    @GetMapping("/adopt/certification")
    public ModelAndView certification(
            HttpServletRequest request
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/certification/certification");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            List<String> certList = Arrays.asList(new String[]{""});

            if(user.getUCert() != null) {
                if(user.getUCert().contains("/")) {
                    certList = Arrays.asList(user.getUCert().split("/"));
                } else {
                    certList = Arrays.asList(new String[]{user.getUCert()});
                }
            }

            ArrayList<animal1DTO> tempAnimal1List = service.selectAllAnimal1();

            ArrayList<animal1DTO> animal1List = new ArrayList<animal1DTO>();

            for (animal1DTO temp: tempAnimal1List) {
                if(!certList.contains(String.valueOf(temp.getA1Seq()))){
                    if(!(temp.getA1Seq() == 6))
                    {
                        animal1List.add(temp);
                    }
                }
            }
            mv.addObject("animal1List", animal1List);

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

    @PostMapping("/adopt/certification/quiz")
    public ModelAndView quiz(
            HttpServletRequest request,
            @RequestParam(name = "a1Seq") int a1Seq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/certification/certificationQuiz");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            ArrayList<quizDTO> quizList = service.select3Quiz(a1Seq);
            mv.addObject("quizList", quizList);

            animal1DTO animal1 = service.selectAnimal1ByA1Seq(a1Seq);
            mv.addObject("animal1", animal1);
        }

        return mv;
    }

    @PostMapping("/adopt/certification/quizscore")
    public ModelAndView quizscore(
            HttpServletRequest request,
            @RequestParam(name = "inputAnswers") String inputAnswers,
            @RequestParam(name = "ans1") String ans1,
            @RequestParam(name = "ans2") String ans2,
            @RequestParam(name = "ans3") String ans3,
            @RequestParam(name = "a1Seq") int a1Seq
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/certification/certificationScore");

            // 정답 대조
            String realAnswer = ans1 + ans2+ ans3;
            realAnswer = realAnswer.replace("1", "O");
            realAnswer = realAnswer.replace("0", "X");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            animal1DTO animal1 = service.selectAnimal1ByA1Seq(a1Seq);
            mv.addObject("animal1", animal1);

            // 정답일 경우
            if(realAnswer.equals(inputAnswers)) {
                mv.addObject("result", true);
            } else {
                mv.addObject("result", false);
            }
        }

        return mv;
    }

    @GetMapping("/adopt/certification/photo")
    public ModelAndView photo(
            HttpServletRequest request,
            @RequestParam(name = "a1Seq") int a1Seq
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            mv = new ModelAndView("/certification/certificationPhoto");

            userDTO user = (userDTO) session.getAttribute("user");
            mv.addObject("user", user);

            animal1DTO animal1 = service.selectAnimal1ByA1Seq(a1Seq);
            mv.addObject("animal1", animal1);

        }

        return mv;
    }

    @PostMapping("/adopt/certification/photo")
    public String photoProcess(
            HttpServletRequest request,
            MultipartHttpServletRequest multipartHttpServletRequest,
            certificationDTO certification
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if(session != null) {
            // 세션에서 user 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            certification.setUSeq(user.getUSeq());

            service.insertCertification(certification, multipartHttpServletRequest, request);
        }
        return "redirect:/adopt/certification";
    }

}
