package com.bobjo.bobjo.diary;

import com.bobjo.bobjo.DTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface diaryMapper {

    ArrayList<animal3DTO> selectAllAnimal3ByUSeq(int uSeq) throws Exception;

    ArrayList<diaryDTO> selectAllDiaryByUSeq(int uSeq) throws Exception;

    void insertDiary(diaryDTO diary) throws Exception;

    ArrayList<calendarDTO> selectAllCalendarByUSeq(int uSeq) throws Exception;

    ArrayList<calendarDTO> selectCalendarByUSeq(int uSeq) throws Exception;

    void insertCalendar(calendarDTO calendar) throws Exception;

    ArrayList<userDTO> selectAllUser() throws Exception;

    String selectA3NickByUSeq(int uSeq) throws Exception;

    void insertFriend(@Param(value = "uSeq") int uSeq, @Param(value = "uFriends") String uFriends) throws Exception;

    void insertFirstFriend(@Param(value = "uSeq") int uSeq, @Param(value = "newFriend") int newFriend) throws Exception;

    String selectUAliasByUSeq(int uSeq) throws Exception;

    void insertFile(fileDTO file) throws Exception;

    void updateTableForImage(@Param("dSeq") int dSeq, @Param("dThumb") String dThumb) throws Exception;

    void deleteDiary(int dSeq) throws Exception;
}
