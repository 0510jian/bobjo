package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class animal3DTO {
    private int a3Seq; // 내동물번호
    private String a3Nick; // 애칭
    private String a3Birth; // 생년월일

    private int a2Seq; // 소동물번호
    private int uSeq; // 회원번호
}
