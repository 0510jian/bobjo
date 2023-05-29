package com.bobjo.bobjo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class quizDTO {
    private int qSeq; // 퀴즈번호
    private String qQuiz; // 내용
    private int qAnswer; // 답

    private int a1Seq; // 대동물번호호
}
