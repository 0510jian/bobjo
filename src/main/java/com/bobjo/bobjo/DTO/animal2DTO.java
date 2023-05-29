package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class animal2DTO {

    private int a2Seq; // 소동물번호
    private String a2Species; // 종 이름
    private String a2Lifespan; // 평균 수명
    private String a2Disease; // 유전병
    private String a2Vaccine; // 필요예방접종

    private int a1Seq; // 대동물번호
}
