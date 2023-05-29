package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class sosDTO {
    private int sSeq; // sos번호
    private String sTitle; // 제목
    private String sContent; // 내용
    private boolean sFin; //완료여부
    private String sDate; // 작성시기
    private String sImage; // 이미지

    private int uSeq; // 회원번호
    private String uAlias; // 닉네임

    private int rCount;

}
