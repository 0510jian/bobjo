package com.bobjo.bobjo.diary;

import com.bobjo.bobjo.DTO.*;
import com.bobjo.bobjo.community.communityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class diaryController {

    @Autowired
    private diaryService service;

    @Autowired
    private communityService comService;

    @GetMapping("/diary")
    public ModelAndView diaryList(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if (session != null) {
            // 세션 정보가 있을 경우

            mv = new ModelAndView("/diary/diary");

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // uSeq으로 animal3 select
            ArrayList<animal3DTO> animal3List = service.selectAllAnimal3ByUSeq(user.getUSeq());
            // uSeq으로 diary select
            ArrayList<diaryDTO> diaryList = service.selectAllDiaryByUSeq(user.getUSeq());

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = comService.selectWalkmateByUSeq(user.getUSeq());

            for (diaryDTO diary:diaryList) {
                String[] date = diary.getDDate().split(" ");
                diary.setDDate(date[0]);
            }

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(comService.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv.addObject("animal3List", animal3List);
            mv.addObject("diaryList", diaryList);

            // mv에 walkmateList 추가
            mv.addObject("walkmateList", walkmateList);
        }

        return mv;
    }

    @DeleteMapping("/diary/{dSeq}")
    public String diaryDelete(
            HttpServletRequest request,
            @PathVariable(value = "dSeq") int dSeq
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        if(session != null) {
            service.deleteDiary(dSeq);
        }

        return "redirect:/diary";
    }


    @GetMapping("/diary/schedule")
    public ModelAndView schedule(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if (session != null) {
            // 세션 정보가 있을 경우

            mv = new ModelAndView("/diary/schedule");

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

            // 해당 유저의 모든 schedule 정보 가져옴
            ArrayList<calendarDTO> calendarList = service.selectAllCalendarByUSeq(user.getUSeq());


            mv.addObject("calendarList", calendarList);
            mv.addObject("walkmateList", walkmateList);
        }

        return mv;
    }


    @GetMapping("/diary/friend")
    public ModelAndView friend(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if (session != null) {
            // 세션 정보가 있을 경우

            mv = new ModelAndView("/diary/friend");

            // 세션에서 userDTO 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = comService.selectWalkmateByUSeq(user.getUSeq());

            ArrayList<userDTO> friendList2 = new ArrayList<userDTO>();
            String[] friendList = null;

            // 모든 친구꺼
            ArrayList<calendarDTO> friendsCalendarList = new ArrayList<calendarDTO>();

            // 친구 목록 만들기
            if (user.getUFriends() != null && !user.getUFriends().equals("")) {
                if(user.getUFriends().contains("/")) {
                    friendList = user.getUFriends().split("/");
                } else {
                    friendList = new String[] {user.getUFriends()};
                }
                // 친구 이름 얻기
                for(int i=0; i< friendList.length; i++) {
                    // 친구별 공개된 calendar 얻어오기
                    // 한 친구 꺼
                    ArrayList<calendarDTO> friendCalendar = service.selectCalendarByUSeq(Integer.parseInt(friendList[i]));

                    for (calendarDTO calendar: friendCalendar) {
                        friendsCalendarList.add(calendar);
                    }

                    String tempAlias = service.selectUAliasByUSeq(Integer.parseInt(friendList[i]));
                    friendList2.add(new userDTO(Integer.parseInt(friendList[i]),tempAlias));
                }
            }

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(comService.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv.addObject("user", user);
            mv.addObject("friendList2", friendList2);
            mv.addObject("friendsCalendarList", friendsCalendarList);
            mv.addObject("walkmateList", walkmateList);
        }

        return mv;
    }


    @GetMapping("/diary/write")
    public ModelAndView diaryWrite(
            HttpServletRequest request
    ) throws Exception {
        // 세션 get
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // 세션 정보가 있을 경우
            mv = new ModelAndView("/diary/diaryWrite");

            // 세션에서 user 가져옴
            userDTO user = (userDTO) session.getAttribute("user");

            // uSeq으로 animal3 select
            ArrayList<animal3DTO> animal3List = service.selectAllAnimal3ByUSeq(user.getUSeq());

            mv.addObject("user", user);
            mv.addObject("animal3List", animal3List);
        }

        return mv;
    }

    @PostMapping("/diary/write")
    public String diaryWriteProcess(
            HttpServletRequest request,
            MultipartHttpServletRequest multipartHttpServletRequest,
            diaryDTO diary
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if(session != null) {
            // 세션에서 user 가져옴
            userDTO user = (userDTO) session.getAttribute("user");
            diary.setUSeq(user.getUSeq());

            service.insertDiary(diary, multipartHttpServletRequest, request);
        }
        return "redirect:/diary";
    }

    @GetMapping("/diary/schedule/write")
    public ModelAndView scheduleWrite(
            HttpServletRequest request
    ) throws Exception {
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session!= null) {
            // user get
            userDTO user = (userDTO) session.getAttribute("user");

            mv = new ModelAndView("/diary/scheduleAdd");
            mv.addObject("user", user);
        }

        return mv;
    }

    // calendar 등록
    @PostMapping("/diary/schedule/write")
    public String schedulkeWriteProcess(
            HttpServletRequest request,
            calendarDTO calendar
    ) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null) {
            // user get
            userDTO user = (userDTO) session.getAttribute("user");

            calendar.setUSeq(user.getUSeq());

            service.insertCalendar(calendar);
        }
        return "redirect:/diary/schedule";
    }












    // 친구

    // 친구 찾기
    @GetMapping("/diary/friend/follow")
    public ModelAndView addFriend(
            HttpServletRequest request
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        ModelAndView mv = null;

        if(session != null) {
            // get user
            userDTO user = (userDTO) session.getAttribute("user");

            ArrayList<userDTO> userList = service.selectAllUser();

            // 대표 동물 넣기
            for (userDTO tempUser: userList) {
                tempUser.setA3Nick(service.selectA3NickByUSeq(tempUser.getUSeq()));
                if(tempUser.getA3Nick() == null || tempUser.getA3Nick().equals("")) {
                    tempUser.setA3Nick("등록한 동물이 없습니다");
                }
            }

            // walkmate 정보 가져오기
            ArrayList<walkmateDTO> walkmateList = comService.selectWalkmateByUSeq(user.getUSeq());

            // walkmate 이름, 내동물 정보 가져오기
            for (walkmateDTO walkmate : walkmateList) {
                walkmate.setProposeAlias(comService.selectUAliasByUSeq(walkmate.getWmPropose()));
                walkmate.setA2Species(comService.selectA2SpeciesByA3Seq(walkmate.getA3Seq()));
                walkmate.setA3Nick(comService.selectA3NickByA3Seq(walkmate.getA3Seq()));
            }

            mv = new ModelAndView("/diary/friendAdd");

            mv.addObject("user", user);
            mv.addObject("userList", userList);
            // mv에 walkmateList 추가
            mv.addObject("walkmateList", walkmateList);
        }

        return mv;
    }

    @PostMapping("/diary/friend/follow")
    public String addFriendProcess(
            HttpServletRequest request,
            userDTO tempUser
    ) throws Exception {
        // get session
        HttpSession session = request.getSession(false);

        userDTO user = (userDTO) session.getAttribute("user");
        if (user.getUSeq() != tempUser.getUSeq()) {
            // 검색 목록 없을 시 return
            if (tempUser.getUSeq() == -1) {
                return "redirect:/diary/friend/";
            }

            // 친구 목록
            if (user.getUFriends() == null || user.getUFriends().equals("")) {
                service.insertFirstFriend(user.getUSeq(),tempUser.getUSeq());
                user.setUFriends(String.valueOf(tempUser.getUSeq()));
                return "redirect:/diary/friend/";
            }

            String[] friendList = null;
            // 친구가 이미 있는 경우 split 아니면 그냥 넣기
            if (user.getUFriends().contains("/")) {
                friendList = user.getUFriends().split("/");
            } else {
                friendList = new String[] {user.getUFriends()};
            }

            for (String friend : friendList) {
                if (Integer.parseInt(friend) == tempUser.getUSeq()) {
                    return "redirect:/diary/friend/";
                }
            }

            // 친구 목록에 없을 경우 친구 추가
            user.setUFriends(user.getUFriends()+ "/" + tempUser.getUSeq());
            service.insertFriend(user.getUSeq(), user.getUFriends());
        }

        return "redirect:/diary/friend/";
    }
}
