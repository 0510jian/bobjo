package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class replyDTO {
    private int rSeq; // 댓글번호
    private String rDate; // 작성날짜
    private boolean rPriv; // 비밀여부
    private String rContent; // 내용
    private String rCate; // 카테고리
    

    private int uSeq; // 회원번호
    private int coSeq; // 글번호
    
    private String uAlias; // 닉네임
}
