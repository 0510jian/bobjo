package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class newfmDTO {

    private int nSeq; // 분양번호
    private String nDate; // 작성시기
    private boolean nFin; // 완료되었는지?
    private boolean nAdopt; // 입양인지?
    private String nBirth; // 생년월일
    private int nSex; // 성별
    private String nContent; // 내용
    private String nThumb; // 사진

    private int a1Seq; // 대동물번호
    private int a2Seq; // 소동물번호
    private int uSeq; // 회원번호
    private String a2Species; // 소동물 종

    private String uAlias;

    private int rCount;
}
