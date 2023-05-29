package com.bobjo.bobjo.mypage;

import com.bobjo.bobjo.DTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface mypageMapper {
    void updateUser(userDTO user) throws Exception;

    void updateUserNotPw(userDTO user) throws Exception;

    ArrayList<boardDTO> selectAllBoardByUSeq(int uSeq) throws Exception;

    ArrayList<sosDTO> selectAllSosByUSeq(int uSeq) throws Exception;

    ArrayList<walkDTO> selectAllWalkByUSeq(int uSeq) throws Exception;

    ArrayList<newfmDTO> selectAllNewfmByUSeq(int uSeq) throws Exception;


    void updateBRemoveByBSeq(int seq) throws Exception;

    void deleteSosBySSeq(int seq) throws Exception;

    void deleteWalkByWSeq(int seq) throws Exception;

    void deleteNewfmByNSeq(int seq) throws Exception;

    int selectRCount(@Param("cate") String cate, @Param("seq") int seq) throws Exception;




    ArrayList<walkmateDTO> selectWalkmateByUSeq(int uSeq) throws Exception;

    String selectAliasByUSeq(int uSeq) throws Exception;

    walkmateDTO selectWalkmateByWmSeq(int wmSeq) throws Exception;

    String selectTelByUSeq(int uSeq) throws Exception;

    void updateWalkmateByWmSeq(int wmSeq) throws Exception;
}
