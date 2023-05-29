package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class calendarDTO {

    private int cSeq; // 캘린더번호
    private String cTitle; // 일정이름
    private String cSdate; // 일정시작날짜
    private String cEdate; // 일정종료날짜
    private int cCate; // 태그
    private boolean cShare; // 공유여부
    private int cCycle; // 반복 주기

    private int uSeq; // 회원번호
}
