package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class certificationDTO {

    private int ceSeq; // 인증번호
    private String ceImage1; // 필수물품 이미지
    private String ceImage2; // 추가물품 이미지
    private boolean ceResult; // 완료여부

    private int uSeq; // 회원번호
    private int a1Seq; // 대동물번호

    private String uAlias;
    private String a1Species;
}
