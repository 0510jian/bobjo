package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class walkDTO {

    private int wSeq; // 산책메이트 글 번호
    private String wLatlng; // 위치정보
    private int wTime; // 시간대
    private String wContent; // 내용
    private String wUploadTime; // 업로드 시간
    
    private int uSeq; // 회원번호
    private int a3Seq; // 내동물번호

    private String uAlias; // 닉네임
    private String a2Species; // 종

    private int rCount;
}
