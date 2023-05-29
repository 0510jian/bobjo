package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class walkmateDTO {

    private int wmSeq; // 산책메이트 신청 번호
    private int wmPropose; // 신청회원번호
    private int wmAccept; // 수락회원번호
    private int wmRes; // 메이트성사여부
    private String wmContent; // 내용

    private int wSeq; // 산책메이트 글 번호
    private int a3Seq; // 내동물번호
    private String a2Species; // 신청한 사람 동물 종
    private String a3Nick; // 신청한 사람 동물 이름

    private String proposeAlias; // 신청한 사람 닉네임
    private String acceptAlias; // 신청받은 사람 닉네임
    private String proposeNumber; // 신청한 사람 전화번호
    private String acceptNumber; // 신청받은 사람 전화번호
}
