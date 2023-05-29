package com.bobjo.bobjo.login;

import com.bobjo.bobjo.DTO.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService{

    @Autowired
    private loginMapper mapper;

    @Override
    public userDTO selectUser(String id, String pw) throws Exception {
        return mapper.selectUser(id, pw);
    }

    @Override
    public void insertUser(userDTO userDTO) throws Exception {
        mapper.insertUser(userDTO);
    }
}
