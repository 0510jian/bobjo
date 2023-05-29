package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class managerDTO {

    private int mSeq; // 관리자번호
    private String mId; // id
    private String mPw; // pw

}
