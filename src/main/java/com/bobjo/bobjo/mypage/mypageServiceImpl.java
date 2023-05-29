package com.bobjo.bobjo.mypage;

import com.bobjo.bobjo.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class mypageServiceImpl implements mypageService{

    @Autowired
    private mypageMapper mapper;

    @Override
    public void updateUser(userDTO user) throws Exception {
        mapper.updateUser(user);
    }

    @Override
    public void updateUserNotPw(userDTO user)throws Exception {
        mapper.updateUserNotPw(user);
    }

    @Override
    public ArrayList<boardDTO> selectAllBoardByUSeq(int uSeq) throws Exception {
        return mapper.selectAllBoardByUSeq(uSeq);
    }

    @Override
    public ArrayList<sosDTO> selectAllSosByUSeq(int uSeq) throws Exception {
        return mapper.selectAllSosByUSeq(uSeq);
    }

    @Override
    public ArrayList<walkDTO> selectAllWalkByUSeq(int uSeq) throws Exception {
        return mapper.selectAllWalkByUSeq(uSeq);
    }

    @Override
    public ArrayList<newfmDTO> selectAllNewfmByUSeq(int uSeq) throws Exception {
        return mapper.selectAllNewfmByUSeq(uSeq);
    }

    @Override
    public void updateBRemoveByBSeq(int seq) throws Exception {
        mapper.updateBRemoveByBSeq(seq);
    }

    @Override
    public void deleteSosBySSeq(int seq) throws Exception {
        mapper.deleteSosBySSeq(seq);
    }

    @Override
    public void deleteWalkByWSeq(int seq) throws Exception {
        mapper.deleteWalkByWSeq(seq);
    }

    @Override
    public void deleteNewfmByNSeq(int seq) throws Exception {
        mapper.deleteNewfmByNSeq(seq);
    }

    @Override
    public int selectRCount(String cate, int seq) throws Exception {
        return mapper.selectRCount(cate, seq);
    }






    @Override
    public ArrayList<walkmateDTO> selectWalkmateByUSeq(int uSeq) throws Exception {
        return mapper.selectWalkmateByUSeq(uSeq);
    }

    @Override
    public String selectAliasByUSeq(int uSeq) throws Exception {
        return mapper.selectAliasByUSeq(uSeq);
    }

    @Override
    public walkmateDTO selectWalkmateByWmSeq(int wmSeq) throws Exception {
        return mapper.selectWalkmateByWmSeq(wmSeq);
    }

    @Override
    public String selectTelByUSeq(int uSeq) throws Exception {
        return mapper.selectTelByUSeq(uSeq);
    }

    @Override
    public void updateWalkmateByWmSeq(int wmSeq) throws Exception {
        mapper.updateWalkmateByWmSeq(wmSeq);
    }
}
