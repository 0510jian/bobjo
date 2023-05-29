package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class boardDTO {
    private int bSeq; // 글번호
    private String bTitle; // 제목
    private String bContent; // 내용
    private int bCate; // 게시글종류
    private boolean bRemove; // 글유무여부
    private String bDate; // 작성시기
    private String bImage; // 이미지

    private int tSeq; // 카테고리 번호
    private int uSeq; // 회원번호
    private String uAlias; // 닉네임

    private int rCount;
}
