package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class vaccineDTO {
    private int vSeq; // 예방접종번호
    private String vName; // 접종명
    private String vDetail; // 설명

    private int a2Seq; // 대동물번호
}
