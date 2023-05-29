package com.bobjo.bobjo.diary;

import com.bobjo.bobjo.DTO.*;
import com.bobjo.bobjo.file.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class diaryServiceImpl implements diaryService {

    @Autowired
    private diaryMapper mapper;

    @Autowired
    private uploadFile uploadFile;

    @Override
    public ArrayList<animal3DTO> selectAllAnimal3ByUSeq(int uSeq) throws Exception {
        return mapper.selectAllAnimal3ByUSeq(uSeq);
    }

    @Override
    public ArrayList<diaryDTO> selectAllDiaryByUSeq(int uSeq) throws Exception{
        return mapper.selectAllDiaryByUSeq(uSeq);
    }

    @Override
    public void insertDiary(diaryDTO diary, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        mapper.insertDiary(diary);

        fileDTO file = uploadFile.parseFile("diary", diary.getDSeq(), multipartHttpServletRequest, request);
        if(file != null) {
            mapper.insertFile(file);
            mapper.updateTableForImage(diary.getDSeq(), file.getFilePath());
        }

    }

    @Override
    public ArrayList<calendarDTO> selectAllCalendarByUSeq(int uSeq) throws Exception {
        return mapper.selectAllCalendarByUSeq(uSeq);
    }

    @Override
    public ArrayList<calendarDTO> selectCalendarByUSeq(int uSeq) throws Exception {
        return mapper.selectCalendarByUSeq(uSeq);
    }

    @Override
    public void insertCalendar(calendarDTO calendar) throws Exception {
        mapper.insertCalendar(calendar);
    }

    @Override
    public ArrayList<userDTO> selectAllUser() throws Exception {
        return mapper.selectAllUser();
    }

    @Override
    public String selectA3NickByUSeq(int uSeq) throws Exception {
        return mapper.selectA3NickByUSeq(uSeq);
    }

    @Override
    public void insertFriend(int uSeq, String uFriends) throws Exception {
        mapper.insertFriend(uSeq, uFriends);
    }

    @Override
    public void insertFirstFriend(int uSeq, int newFriend) throws Exception {
        mapper.insertFirstFriend(uSeq, newFriend);
    }

    @Override
    public String selectUAliasByUSeq(int uSeq) throws Exception{
        return mapper.selectUAliasByUSeq(uSeq);
    }




    @Override
    public void deleteDiary(int dSeq) throws Exception {
        mapper.deleteDiary(dSeq);
    }
}
