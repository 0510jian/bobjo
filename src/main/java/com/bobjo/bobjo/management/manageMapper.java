package com.bobjo.bobjo.management;

import com.bobjo.bobjo.DTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface manageMapper {
    void deleteUserByUSeq(int uSeq) throws Exception;

    void deleteBoardByBSeq(int bSeq) throws Exception;

    void deleteSosBySSeq(int sSeq) throws Exception;

    void deleteNewfmByNSeq(int nSeq) throws Exception;

    ArrayList<certificationDTO> selectAllCertification() throws Exception;

    String selectUNickByUSeq(int uSeq) throws Exception;

    String selectA1SpeciesByA1Seq(int a1Seq) throws Exception;

    ArrayList<boardDTO> selectAllBoard() throws Exception;

    ArrayList<sosDTO> selectAllSos() throws Exception;

    ArrayList<newfmDTO> selectAllNewfm() throws Exception;

    String selectUAliasByUSeq(int uSeq) throws Exception;

    ArrayList<userDTO> selectAllUser() throws Exception;

    ArrayList<animal1DTO> selectAllAnimal1() throws Exception;

    ArrayList<quizDTO> selectAllQuiz() throws Exception;

    void insertQuiz(quizDTO quiz) throws Exception;

    certificationDTO selectCertificationByCeSeq(int ceSeq) throws Exception;

    animal1DTO selectAnimal1ByA1Seq(int a1Seq) throws Exception;

    void updateCertification(int ceSeq) throws Exception;

    void deleteCertification(int ceSeq) throws Exception;

    userDTO selectUserByUSeq(int uSeq) throws Exception;

    void updateUserUCert(@Param(value = "uSeq") int uSeq, @Param(value = "uCert") String uCert) throws Exception;

    void deleteQuizByQSeq(int qSeq) throws Exception;
}
