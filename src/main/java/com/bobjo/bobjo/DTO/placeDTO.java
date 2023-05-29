package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class placeDTO {

    private int pSeq; // 위치정보번호
    private String pName; // 이름
    private String pLoc; // 위치
    private String pTel; // 전화번호
    private String pCate; // 분류번호
}
