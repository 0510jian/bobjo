package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class animal1DTO {

    private int a1Seq; // 대동물번호
    private String a1Species; // 종 이름

    private String a1Supply1; // 필수물품
    private String a1Supply2; // 추가물품
}
