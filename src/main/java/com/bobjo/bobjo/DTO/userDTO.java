package com.bobjo.bobjo.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class userDTO {

    private int uSeq; // 회원번호
    private String uId; // id
    private String uPw; // pw
    private String uName; // 이름
    private String uAlias; // 닉네임
    private String uBirth; // 생일
    private String uMail; // 이메일
    private String uTel; // 전화번호
    private String uAddr; // 주소
    private String uGroup; // 그룹키
    private String uFriends; // 친구 회원번호
    private String uCert; // 인증 여부

    private String a3Nick; // 대표 동물 이름

    public userDTO(int uSeq, String uAlias) {
        this.uSeq = uSeq;
        this.uAlias = uAlias;
    }
}