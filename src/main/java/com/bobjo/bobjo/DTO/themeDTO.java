package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class themeDTO {

    private int tSeq; // 카테고리 번호
    private String tName; // 카테고리 이름
    private boolean tRemove; // 카테고리 유무 여부
}
