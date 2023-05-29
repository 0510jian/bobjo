package com.bobjo.bobjo.adopt;

import com.bobjo.bobjo.DTO.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface adoptService {
    ArrayList<newfmDTO> selectAllNewfmByNAdopt(boolean nAdopt) throws Exception;

    ArrayList<animal1DTO> selectAllAnimal1() throws Exception;

    ArrayList<animal2DTO> selectAllAnimal2() throws Exception;

    ArrayList<animal2DTO> selectAllAnimal2ByA1Seq(int a1Seq) throws Exception;

    void insertNewfm(newfmDTO newfm, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception;

    String selectA2SpeciesByA12Seq(int a1Seq, int a2Seq) throws Exception;

    newfmDTO selectNewfmByNSeq(int nSeq) throws Exception;

    void deleteAdopt(int nSeq) throws Exception;

    void deleteFile(int oSeq) throws Exception;

    ArrayList<quizDTO> select3Quiz(int a1Seq) throws Exception;

    animal1DTO selectAnimal1ByA1Seq(int a1Seq) throws Exception;

    void insertCertification(certificationDTO certification, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception;

}
