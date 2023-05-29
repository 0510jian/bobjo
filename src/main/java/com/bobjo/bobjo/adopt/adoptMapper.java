package com.bobjo.bobjo.adopt;

import com.bobjo.bobjo.DTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface adoptMapper {

    ArrayList<newfmDTO> selectAllNewfmByNAdopt(boolean nAdopt) throws Exception;

    ArrayList<animal1DTO> selectAllAnimal1() throws Exception;

    ArrayList<animal2DTO> selectAllAnimal2() throws Exception;

    ArrayList<animal2DTO> selectAllAnimal2ByA1Seq(int a1Seq) throws Exception;

    void insertNewfm(newfmDTO newfm) throws Exception;

    String selectA2SpeciesByA12Seq(@Param("a1Seq") int a1Seq, @Param("a2Seq") int a2Seq) throws Exception;

    newfmDTO selectNewfmByNSeq(int nSeq);

    void deleteAdopt(int nSeq);

    void insertFile(fileDTO file) throws Exception;

    void updateTableForImage(@Param("nSeq") int nSeq, @Param("nThumb") String nThumb) throws Exception;
    void updateCertificationForImage1(@Param("ceSeq") int ceSeq, @Param("ceImage1") String ceImage1) throws Exception;
    void updateCertificationForImage2(@Param("ceSeq") int ceSeq, @Param("ceImage2") String ceImage2) throws Exception;

    void deleteFile(int oSeq);

    ArrayList<quizDTO> select3Quiz(int a1Seq) throws Exception;

    animal1DTO selectAnimal1ByA1Seq(int a1Seq) throws Exception;

    void insertCertification(certificationDTO certification) throws Exception;
}
