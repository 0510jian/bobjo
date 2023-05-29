package com.bobjo.bobjo.community;

import com.bobjo.bobjo.DTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface communityMapper {

    ArrayList<sosDTO> selectAllSos() throws Exception;

    String selectUAliasByUSeq(int uSeq) throws Exception;

    void insertSos(sosDTO sos) throws Exception;

    ArrayList<boardDTO> selectAllBoard() throws Exception;

    void insertBoard(boardDTO board) throws Exception;

    boardDTO selectBoardByBSeq(int bSeq) throws Exception;

    void updateBRemoveByBSeq(int bSeq) throws Exception;

    void updateBoard(boardDTO board) throws Exception;

    sosDTO selectSosBySSeq(int sSeq) throws Exception;

    void updateSosSFin(int sSeq) throws Exception;

    ArrayList<animal3DTO> selectAllAnimal3ByUSeq(int uSeq) throws Exception;

    void insertWalk(walkDTO walk) throws Exception;

    ArrayList<walkDTO> selectAllWalk() throws Exception;

    int selectA2SeqByA3Seq(int a3Seq) throws Exception;

    String selectA2SpeciesByA2Seq(int a2Seq) throws Exception;

    walkDTO selectWalkByWSeq(int wSeq) throws Exception;

    animal3DTO selectAnimal3ByA3Seq(int a3Seq) throws Exception;

    void deleteWalk(int wSeq) throws Exception;

    void insertFile(fileDTO file) throws Exception;

    void updateTableForImageSos(@Param("sSeq") int sSeq, @Param("sImage") String sImage) throws Exception;

    void updateTableForImageBoard(@Param("bSeq") int bSeq, @Param("bImage") String bImage) throws Exception;

    void deleteFile(int oSeq);

    ArrayList<replyDTO> selectReplyBySeq(@Param(value = "rCate") String rCate, @Param(value = "seq") int seq) throws Exception;

    void insertReply(replyDTO reply) throws Exception;

    void deleteReply(int rSeq) throws Exception;

    int selectUSeqByWSeq(int wSeq) throws Exception;

    void insertWalkmate(walkmateDTO walkmate) throws Exception;



    // =================================================

    ArrayList<walkmateDTO> selectWalkmateByUSeq(int uSeq) throws Exception;

    String selectA3NickByA3Seq(int a3Seq) throws Exception;

    void deleteWalkmate(int wmSeq) throws Exception;

    void updateWalkmate(int wmSeq) throws Exception;
}
