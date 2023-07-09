# 올인원 펫 플랫폼 BOBJO
![image](https://github.com/0510jian/bobjo/assets/124128448/9ab45cf7-14b1-4e7e-ba05-61053e258644)

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

## 메인화면
![image](https://github.com/0510jian/bobjo/assets/124128448/d46ccab4-cfc5-4e0c-a475-25bc38f090f1)


## 로그인, 회원가입
### 로그인
![image](https://github.com/0510jian/bobjo/assets/124128448/375328f2-8a56-4c51-828a-27add5d7e9da)
- 세션을 이용한 로그인 구현

### 회원가입
![image](https://github.com/0510jian/bobjo/assets/124128448/3c7e1878-a3e7-4932-87dd-ee1f9fc66753)
- Daum 주소찾기 API 적용

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
- Kakao Maps API 적용
- 산책메이트 게시글 작성, 조회, 수정, 삭제 기능(CRUD)
- 산책메이트 신청 기능
![003-6](https://github.com/0510jian/bobjo/assets/124128448/1a07fe7a-58e3-4204-a522-f0aaa4d1ab30)
- 산책메이트 수락 기능
- 마이페이지-메이트결과에서 산책메이트 결과 조회 가능

## 입양/분양
### 입양/분양 게시판
![003-7](https://github.com/0510jian/bobjo/assets/124128448/92a3eb15-1ed0-4dc7-a9ab-d5e19d809999)
- 입양/분양 게시글 작성, 조회, 수정, 삭제 기능(CRUD)
- 댓글 작성, 조회, 삭제(CRD)
- Ckeditor 프레임워크 적용
- 인증센터에서 인증된 사용자만 이용 가능
### 인증센터(입양/분양 게시판을 이용하기 전 인증 절차)
![003-8](https://github.com/0510jian/bobjo/assets/124128448/2c5d07d5-2ac8-4d97-ba38-6832e610b2e8)
![003-9](https://github.com/0510jian/bobjo/assets/124128448/e1be3e35-417a-4ee1-9663-c34edea7fb24)
- 인증할 종 선택
- O/X 퀴즈
- 구비 물품 사진 확인
- 관리자 승인 후 입양/분양 게시판 이용 가능

## 마이페이지
### 개인정보수정, 문의하기
![003-11](https://github.com/0510jian/bobjo/assets/124128448/10c1ae62-e1b7-4dfc-bb1f-bf569cb5c8bc)
- Daum 주소찾기 API 적용
- Google SMTP 적용(capstone.bobjo@gmail.com 로 문의 메일 전송 기능)
### 작성 글 관리, 로그아웃
![003-12](https://github.com/0510jian/bobjo/assets/124128448/9de5dea8-94ed-40fe-bc9b-89f8d4964463)
- 게시판 별 작성 글 목록 조회, 삭제 기능
- 로그아웃 기능

## 관리자모드
### 관리자모드 진입
![003-14](https://github.com/0510jian/bobjo/assets/124128448/e53a8f26-09c2-4f8f-bb39-925d0a8d70e4)
- ID/PW를 admin으로 입력 시 관리자모드 진입 가능
### 글 관리, 회원 관리
![003-15](https://github.com/0510jian/bobjo/assets/124128448/ba74a2a2-5863-478d-930b-8df4635ccdb4)
- 게시판 별 글 목록 조회, 삭제 기능
- 닉네임/아이디로 회원 검색 기능
- 회원 탈퇴 기능
### 인증센터 사진 관리, 퀴즈 관리
![003-16](https://github.com/0510jian/bobjo/assets/124128448/de3c4a41-a79d-40a6-90fe-18fc0bfbfe91)
- 인증센터 신청 목록 및 사진 관리
- 동물 별 퀴즈 등록, 삭제 기능
