package com.bobjo.bobjo.management;

import com.bobjo.bobjo.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class manageServiceImpl implements manageService{

    @Autowired
    private manageMapper mapper;

    @Override
    public void deleteUserByUSeq(int uSeq) throws Exception {
        mapper.deleteUserByUSeq(uSeq);
    }

    @Override
    public void deleteBoardByBSeq(int bSeq) throws Exception {
        mapper.deleteBoardByBSeq(bSeq);
    }

    @Override
    public void deleteSosBySSeq(int sSeq) throws Exception {
        mapper.deleteSosBySSeq(sSeq);
    }

    @Override
    public void deleteNewfmByNSeq(int nSeq) throws Exception {
        mapper.deleteNewfmByNSeq(nSeq);
    }

    @Override
    public ArrayList<certificationDTO> selectAllCertification() throws Exception {
        return mapper.selectAllCertification();
    }

    @Override
    public String selectUNickByUSeq(int uSeq) throws Exception {
        return mapper.selectUNickByUSeq(uSeq);
    }

    @Override
    public String selectA1SpeciesByA1Seq(int a1Seq) throws Exception {
        return mapper.selectA1SpeciesByA1Seq(a1Seq);
    }

    @Override
    public ArrayList<boardDTO> selectAllBoard() throws Exception {
        return mapper.selectAllBoard();
    }

    @Override
    public ArrayList<sosDTO> selectAllSos() throws Exception {
        return mapper.selectAllSos();
    }
    @Override
    public ArrayList<newfmDTO> selectAllNewfm() throws Exception {
        return mapper.selectAllNewfm();
    }

    @Override
    public String selectUAliasByUSeq(int uSeq) throws Exception {
        return mapper.selectUAliasByUSeq(uSeq);
    }

    @Override
    public ArrayList<userDTO> selectAllUser() throws Exception {
        return mapper.selectAllUser();
    }

    @Override
    public ArrayList<animal1DTO> selectAllAnimal1() throws Exception {
        return mapper.selectAllAnimal1();
    }

    @Override
    public ArrayList<quizDTO> selectAllQuiz() throws Exception {
        return mapper.selectAllQuiz();
    }

    @Override
    public void insertQuiz(quizDTO quiz) throws Exception {
        mapper.insertQuiz(quiz);
    }

    @Override
    public certificationDTO selectCertificationByCeSeq(int ceSeq) throws Exception {
        return mapper.selectCertificationByCeSeq(ceSeq);
    }

    @Override
    public animal1DTO selectAnimal1ByA1Seq(int a1Seq) throws Exception {
        return mapper.selectAnimal1ByA1Seq(a1Seq);
    }

    @Override
    public void updateCertification(int ceSeq) throws Exception {
        mapper.updateCertification(ceSeq);
    }

    @Override
    public void deleteCertification(int ceSeq) throws Exception {
        mapper.deleteCertification(ceSeq);
    }

    @Override
    public userDTO selectUserByUSeq(int uSeq) throws Exception {
        return mapper.selectUserByUSeq(uSeq);
    }

    @Override
    public void updateUserUCert(int uSeq, String uCert) throws Exception {
        mapper.updateUserUCert(uSeq, uCert);
    }

    @Override
    public void deleteQuizByQSeq(int qSeq) throws Exception {
        mapper.deleteQuizByQSeq(qSeq);
    }
}
