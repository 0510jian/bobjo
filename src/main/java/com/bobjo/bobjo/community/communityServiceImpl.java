package com.bobjo.bobjo.community;

import com.bobjo.bobjo.DTO.*;
import com.bobjo.bobjo.file.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class communityServiceImpl implements communityService{

    @Autowired
    private communityMapper mapper;
    @Autowired
    private uploadFile uploadFile;

    @Override
    public ArrayList<sosDTO> selectAllSos() throws Exception {
        return mapper.selectAllSos();
    }

    @Override
    public String selectUAliasByUSeq(int uSeq) throws Exception {
        return mapper.selectUAliasByUSeq(uSeq);
    }

    @Override
    public void insertSos(sosDTO sos, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        mapper.insertSos(sos);

        if(multipartHttpServletRequest != null) {
            fileDTO file = uploadFile.parseFile("sos", sos.getSSeq(), multipartHttpServletRequest, request);
            if(file != null) {
                mapper.insertFile(file);
                mapper.updateTableForImageSos(sos.getSSeq(), file.getFilePath());
            }
        }
    }

    @Override
    public ArrayList<boardDTO> selectAllBoard() throws Exception {
        return mapper.selectAllBoard();
    }

    @Override
    public void insertBoard(boardDTO board, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        mapper.insertBoard(board);

        if(multipartHttpServletRequest != null) {
            fileDTO file = uploadFile.parseFile("board", board.getBSeq(), multipartHttpServletRequest, request);
            if(file != null) {
                mapper.insertFile(file);
                mapper.updateTableForImageBoard(board.getBSeq(), file.getFilePath());
            }
        }
    }

    @Override
    public boardDTO selectBoardByBSeq(int bSeq) throws Exception {
        return mapper.selectBoardByBSeq(bSeq);
    }

    @Override
    public void updateBRemoveByBSeq(int bSeq) throws Exception {
        mapper.updateBRemoveByBSeq(bSeq);
    }

    @Override
    public void updateBoard(boardDTO board) throws Exception {
        mapper.updateBoard(board);
    }

    @Override
    public sosDTO selectSosBySSeq(int sSeq) throws Exception {
        return mapper.selectSosBySSeq(sSeq);
    }

    @Override
    public void updateSosSFin(int sSeq) throws Exception {
        mapper.updateSosSFin(sSeq);
    }

    @Override
    public ArrayList<animal3DTO> selectAllAnimal3ByUSeq(int uSeq) throws Exception {
        return mapper.selectAllAnimal3ByUSeq(uSeq);
    }

    @Override
    public void insertWalk(walkDTO walk) throws Exception {
        mapper.insertWalk(walk);
    }

    @Override
    public ArrayList<walkDTO> selectAllWalk() throws Exception{
        return mapper.selectAllWalk();
    }

    @Override
    public String selectA2SpeciesByA3Seq(int a3Seq) throws Exception {
        int a2Seq = mapper.selectA2SeqByA3Seq(a3Seq);
        return mapper.selectA2SpeciesByA2Seq(a2Seq);
    }

    @Override
    public walkDTO selectWalkByWSeq(int wSeq) throws Exception {
        return mapper.selectWalkByWSeq(wSeq);
    }

    @Override
    public animal3DTO selectAnimal3ByA3Seq(int a3Seq) throws Exception {
        return mapper.selectAnimal3ByA3Seq(a3Seq);
    }

    @Override
    public void deleteWalk(int wSeq) throws Exception {
        mapper.deleteWalk(wSeq);
    }

    @Override
    public void deleteFile(int oSeq) throws Exception {
        mapper.deleteFile(oSeq);
    }

    @Override
    public ArrayList<replyDTO> selectReplyBySeq(String rCate, int seq) throws Exception {
        return mapper.selectReplyBySeq(rCate, seq);
    }

    @Override
    public void insertReply(replyDTO reply) throws Exception {
        mapper.insertReply(reply);
    }

    @Override
    public void deleteReply(int rSeq) throws Exception {
        mapper.deleteReply(rSeq);
    }

    @Override
    public int selectUSeqByWSeq(int wSeq) throws Exception {
        return mapper.selectUSeqByWSeq(wSeq);
    }

    @Override
    public void insertWalkmate(walkmateDTO walkmate) throws Exception {
        mapper.insertWalkmate(walkmate);
    }










    // ================================= 2022-2 발표 후 진행

    @Override
    public ArrayList<walkmateDTO> selectWalkmateByUSeq(int uSeq) throws Exception {
        return mapper.selectWalkmateByUSeq(uSeq);
    }

    @Override
    public String selectA3NickByA3Seq(int a3Seq) throws Exception {
        return mapper.selectA3NickByA3Seq(a3Seq);
    }

    @Override
    public void deleteWalkmate(int wmSeq) throws Exception {
        mapper.deleteWalkmate(wmSeq);
    }

    @Override
    public void updateWalkmate(int wmSeq) throws Exception {
        mapper.updateWalkmate(wmSeq);
    }
}
