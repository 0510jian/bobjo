# 올인원 펫 플랫폼 BOBJO
구현 결과 : [PPTX](https://docs.google.com/presentation/d/15EhzNTp1JdWkBd9qNnsH2CUY_Ks2LMa1/edit?usp=drive_link&ouid=106416664800223116599&rtpof=true&sd=true)  
(이미지 정상적으로 표시되지 않는 부분은 추후 수정 예정)

# 개발 참여
[@0510jian](https://github.com/0510jian)  
[@parksangau](https://github.com/parksangau)  
(기재 요청한 인원만 기재하였습니다)

# 기술 스택
<table>
  <tr>
    <td>언어</td>
    <td>Java, Javascript</td>
  </tr>
  <tr>
    <td>프론트엔드</td>
    <td>HTML, CSS, thymeleaf</td>
  </tr>
  <tr>
    <td>백엔드</td>
    <td>Spring Boot, Mybatis</td>
  </tr>
  <tr>
    <td>데이터베이스</td>
    <td>MySQL</td>
  </tr>
</table>

# 구현 기능

## 다이어리
### 다이어리
![003](https://github.com/0510jian/bobjo/assets/124128448/32cd582d-689b-4564-a70b-f3865bc775ee)
- 다이어리 작성, 조회, 삭제 기능(CRD)
- 등록한 반려동물 별로 다이어리 조회 가능
### 일정
![003-1](https://github.com/0510jian/bobjo/assets/124128448/aaeabd58-4f9f-4991-b69a-0fca6e2b2335)
- 일정 작성 및 조회 기능
- 일정의 공개/비공개 여부 선택 가능
- 날짜 클릭 시 해당 일자의 일정 조회 가능
### 친구
![003-2](https://github.com/0510jian/bobjo/assets/124128448/339b024e-24ae-498c-bb0e-71584b2c05c2)
- 친구 추가 및 친구 일정 조회 기능
- 친구로 등록된 사용자의 공개된 일정 조회 가능

## 커뮤니티
### 응급실
![003-3](https://github.com/0510jian/bobjo/assets/124128448/af471b41-3b44-402d-99a8-a68d0dd31c00)
- 응급실 게시글 작성, 조회 기능(CR)
- 댓글 작성, 조회 기능(CR)
- 답변 상태 표시 기능, 답변 대기 상태의 글이 최상위 노출
- Ckeditor 프레임워크 적용
### 밥줘게시판(자유게시판)
![003-4](https://github.com/0510jian/bobjo/assets/124128448/058a8572-123f-433b-8f43-5d0ee550a321)
- 밥줘 게시글 작성, 조회, 수정, 삭제 기능(CRUD)
- 댓글 작성, 조회, 삭제(CRD)
- Ckeditor 프레임워크 적용
### 산책메이트(함께 산책할 유저 찾기)
![003-5](https://github.com/0510jian/bobjo/assets/124128448/abfb246c-c67c-493d-876c-6a93b9ff6593)
- Kakao Maps API 사용
- 산책메이트 게시글 작성, 조회, 수정, 삭제 기능(CRUD)
- 산책메이트 신청 기능
![003-6](https://github.com/0510jian/bobjo/assets/124128448/1a07fe7a-58e3-4204-a522-f0aaa4d1ab30)
- 산책메이트 수락 기능
- 마이페이지-메이트결과에서 산책메이트 결과 조회 가능

## 입양/분양

## 마이페이지

## 관리자모드
