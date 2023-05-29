package com.bobjo.bobjo.diary;

import com.bobjo.bobjo.DTO.animal3DTO;
import com.bobjo.bobjo.DTO.calendarDTO;
import com.bobjo.bobjo.DTO.diaryDTO;
import com.bobjo.bobjo.DTO.userDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface diaryService {

    ArrayList<animal3DTO> selectAllAnimal3ByUSeq(int uSeq) throws Exception;

    ArrayList<diaryDTO> selectAllDiaryByUSeq(int uSeq) throws Exception;

    void insertDiary(diaryDTO diary, MultipartHttpServletRequest file, HttpServletRequest request) throws Exception;

    ArrayList<calendarDTO> selectAllCalendarByUSeq(int uSeq) throws Exception;

    ArrayList<calendarDTO> selectCalendarByUSeq(int uSeq) throws Exception;

    void insertCalendar(calendarDTO calendar) throws Exception;

    ArrayList<userDTO> selectAllUser() throws Exception;

    String selectA3NickByUSeq(int uSeq) throws Exception;

    void insertFriend(int uSeq, String uFriends) throws Exception;

    void insertFirstFriend(int uSeq, int newFriend) throws Exception;

    String selectUAliasByUSeq(int uSeq) throws Exception;

    void deleteDiary(int dSeq) throws Exception;
}
