package com.bobjo.bobjo.community;

import com.bobjo.bobjo.DTO.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface communityService {

    ArrayList<sosDTO> selectAllSos() throws Exception;

    String selectUAliasByUSeq(int uSeq) throws Exception;

    void insertSos(sosDTO sos, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception;

    ArrayList<boardDTO> selectAllBoard() throws Exception;

    void insertBoard(boardDTO board, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception;

    boardDTO selectBoardByBSeq(int bSeq) throws Exception;

    void updateBRemoveByBSeq(int bSeq) throws Exception;

    void updateBoard(boardDTO board) throws Exception;

    sosDTO selectSosBySSeq(int sSeq) throws Exception;

    void updateSosSFin(int sSeq) throws Exception;

    ArrayList<animal3DTO> selectAllAnimal3ByUSeq(int uSeq) throws Exception;

    void insertWalk(walkDTO walk) throws Exception;

    ArrayList<walkDTO> selectAllWalk() throws Exception;

    String selectA2SpeciesByA3Seq(int a3Seq) throws Exception;

    walkDTO selectWalkByWSeq(int wSeq) throws Exception;

    animal3DTO selectAnimal3ByA3Seq(int a3Seq) throws Exception;

    void deleteWalk(int wSeq) throws Exception;

    void deleteFile(int oSeq) throws Exception;

    ArrayList<replyDTO> selectReplyBySeq(String rCate, int seq) throws Exception;

    void insertReply(replyDTO reply) throws Exception;

    void deleteReply(int rSeq) throws Exception;

    int selectUSeqByWSeq(int wSeq) throws Exception;

    void insertWalkmate(walkmateDTO walkmate) throws Exception;




    // =====================================================================

    ArrayList<walkmateDTO> selectWalkmateByUSeq(int uSeq) throws Exception;

    String selectA3NickByA3Seq(int a3Seq) throws Exception;

    void deleteWalkmate(int wmSeq) throws Exception;

    void updateWalkmate(int wmSeq) throws Exception;
}
