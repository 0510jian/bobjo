package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class diaryDTO {

    private int dSeq; // 일기번호
    private String dThumb; // 썸네일
    private String dTitle; // 제목
    private String dContent; // 내용
    private String dDate; // 작성날짜
    private int dWeather; // 날씨

    private int uSeq; // 회원번호
    private int a3Seq; // 소동물번호
}
