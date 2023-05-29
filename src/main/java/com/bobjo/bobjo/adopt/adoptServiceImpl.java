package com.bobjo.bobjo.adopt;

import com.bobjo.bobjo.DTO.*;
import com.bobjo.bobjo.file.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class adoptServiceImpl implements adoptService {

    @Autowired
    private adoptMapper mapper;

    @Autowired
    private uploadFile uploadFile;

    @Override
    public ArrayList<newfmDTO> selectAllNewfmByNAdopt(boolean nAdopt) throws Exception {
        // nAdopt로 newfm 검색하여 반환
        return mapper.selectAllNewfmByNAdopt(nAdopt);
    }

    @Override
    public ArrayList<animal1DTO> selectAllAnimal1() throws Exception {
        return mapper.selectAllAnimal1();
    }

    @Override
    public ArrayList<animal2DTO> selectAllAnimal2() throws Exception {
        return mapper.selectAllAnimal2();
    }

    @Override
    public ArrayList<animal2DTO> selectAllAnimal2ByA1Seq(int a1Seq) throws Exception {
        return mapper.selectAllAnimal2ByA1Seq(a1Seq);
    }

    @Override
    public void insertNewfm(newfmDTO newfm, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        mapper.insertNewfm(newfm);

        if(multipartHttpServletRequest != null) {
            fileDTO file = uploadFile.parseFile("newfm", newfm.getNSeq(), multipartHttpServletRequest, request);
            if(file != null) {
                mapper.insertFile(file);
                mapper.updateTableForImage(newfm.getNSeq(), file.getFilePath());
            }
        }

    }

    @Override
    public String selectA2SpeciesByA12Seq(int a1Seq, int a2Seq) throws Exception {
        return mapper.selectA2SpeciesByA12Seq(a1Seq, a2Seq);
    }

    @Override
    public newfmDTO selectNewfmByNSeq(int nSeq) throws Exception {
        return mapper.selectNewfmByNSeq(nSeq);
    }

    @Override
    public void deleteAdopt(int nSeq) throws Exception {
        mapper.deleteAdopt(nSeq);
    }

    @Override
    public void deleteFile(int oSeq) throws Exception {
        mapper.deleteFile(oSeq);
    }

    @Override
    public ArrayList<quizDTO> select3Quiz(int a1Seq) throws Exception {
        return mapper.select3Quiz(a1Seq);
    }

    @Override
    public animal1DTO selectAnimal1ByA1Seq(int a1Seq) throws Exception {
        return mapper.selectAnimal1ByA1Seq(a1Seq);
    }

    @Override
    public void insertCertification(certificationDTO certification, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        mapper.insertCertification(certification);

        fileDTO file1 = uploadFile.parseFile2("certification", certification.getCeSeq(), multipartHttpServletRequest, request, "chooseFile1");
        if(file1 != null) {
            mapper.insertFile(file1);
            mapper.updateCertificationForImage1(certification.getCeSeq(), file1.getFilePath());
        }

        fileDTO file2 = uploadFile.parseFile2("certification", certification.getCeSeq(), multipartHttpServletRequest, request, "chooseFile2");
        if(file2 != null) {
            mapper.insertFile(file2);
            mapper.updateCertificationForImage2(certification.getCeSeq(), file2.getFilePath());
        }
    }

}
