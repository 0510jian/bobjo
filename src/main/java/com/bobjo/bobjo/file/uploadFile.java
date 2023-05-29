package com.bobjo.bobjo.file;


import com.bobjo.bobjo.DTO.fileDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

@Component
public class uploadFile {

    @Value("${spring.servlet.multipart.location}")
    String root;

    public fileDTO parseFile2(String o_table, int o_seq, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request, String filename) throws Exception {
        MultipartFile file = multipartHttpServletRequest.getFile(filename);
        String uploadPath = request.getServletContext().getRealPath("/") + root;

        createDir(uploadPath);

        String newFilename = UUID.randomUUID().toString();

        String originalFilename = file.getOriginalFilename();
        String fileExtension = getExtension(file);

        if(fileExtension == "") {
            return null;
        }

        fileDTO newDTO = new fileDTO();
        newDTO.setOSeq(o_seq);
        newDTO.setOTable(o_table);
        newDTO.setFileSize((int)file.getSize());
        newDTO.setOriginalFilename(originalFilename);
        newDTO.setSaveFilename(newFilename + fileExtension);
        newDTO.setFilePath("static/upload/" + newFilename + fileExtension);

        file.transferTo(new File(uploadPath + newFilename + fileExtension));

        return newDTO;
    }

    public fileDTO parseFile(String o_table, int o_seq, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        MultipartFile file = multipartHttpServletRequest.getFile("uploadfile");
        String uploadPath = request.getServletContext().getRealPath("/") + root;

        createDir(uploadPath);

        String newFilename = UUID.randomUUID().toString();

        String originalFilename = file.getOriginalFilename();
        String fileExtension = getExtension(file);

        if(fileExtension == "") {
            return null;
        }

        fileDTO newDTO = new fileDTO();
        newDTO.setOSeq(o_seq);
        newDTO.setOTable(o_table);
        newDTO.setFileSize((int)file.getSize());
        newDTO.setOriginalFilename(originalFilename);
        newDTO.setSaveFilename(newFilename + fileExtension);
        newDTO.setFilePath("static/upload/" + newFilename + fileExtension);

        file.transferTo(new File(uploadPath + newFilename + fileExtension));

        return newDTO;
    }

    // 확장자 생성
    private String getExtension(MultipartFile file) {

        String contentType = file.getContentType();

        if (ObjectUtils.isEmpty(contentType)) {
            return "";
        } else {
            if(contentType.contains("image/jpeg")) {
                return ".jpg";
            } else if(contentType.contains("image/png")) {
                return ".png";
            } else if(contentType.contains("image/gif")) {
                return ".gif";
            } else {
                return "";
            }
        }
    }



    // 폴더 생성
    private void createDir(String uploadPath) {
        File dirPath = new File(uploadPath);
        if(dirPath.exists() == false) dirPath.mkdirs();
    }

}
