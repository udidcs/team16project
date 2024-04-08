# team16project
QnA 커뮤니티 입니다.


|권현수|김민구|김중석|변재정|
|:---:|:---:|:---:|:---:|
|<a href="https://github.com/lastier">🔗 Gwon Hyun Su</a>|<a href="https://github.com/guzzang">🔗 Kim MIn Gu</a>|<a href="https://github.com/stein1213">🔗 Kim Jung Seok</a>|<a href="https://github.com/udidcs">🔗 Byun Jae Jeong</a>|
|* 마이페이지 화면구현<br>* 닉네임 변경 기능<br>* Flow-Chart 설계<br>* 프로젝트 구조도 작성|* 댓글 기능 구현<br>* 화면설계<br>* 시연영상|* 로그인/로그아웃 기능<br>* 회원가입/탈퇴 기능<br>* 회원관리 기능<br>*기능명세 작성|* AWS 배포<br>* 게시글 기능(toast api 활용)<br>* 프로필 사진 변경 기능<br>* 게시글 조회수 표시(redis 활용)|


## 목차
[1. 목표와 기능](#1-목표와-기능)  

[2. 개발 기술 및 환경](#2-개발-기술-및-환경)  

[3. 배포 URL 및 아키텍쳐](#3-배포-url-및-아키텍쳐)  

[4. 요구사항 및 기능 명세](#4-요구사항-및-기능-명세)   

[5. 데이터베이스 모델링(ER Diagram)](#5-데이터베이스-모델링er-diagram)

[6. API 명세서](#6-api-명세서)

[7. 프로젝트 구조](#7-프로젝트-구조)

[8. 화면설계](#8-화면설계)

******


## 1. 목표와 기능
### 1-1. 목표
  - 개발과 관련된 궁금증을 해소하며, 서로 소통 할 수 있는 플랫폼 조성

### 1-2. 기능
  - 회원가입 / 로그인
    유효성 검증
    - 회원가입시 이메일, 닉네임 중복시 생성 불가
    - 이메일 및 패스워드 틀릴시 로그인 불가

  - 게시글 작성/수정 시 에디터(toast UI editor)를 사용하여 편집 기능
    유효성 검증
    - 제목 1글자 이상 50자 이하 , 내용 3000자 이하 
    - 로그인 하지 않으면 게시글 작성, 수정, 삭제 불가
    - 본인이 작성한 게시글이 아니면 수정, 삭제 불가 
  
  - 댓글과 대댓글 작성/수정/삭제 기능
    유효성 검증
    - 댓글 내용 1글자 이상 1000자 이하
    - 로그인 하지 않으면 댓글,대댓글 작성, 수정, 삭제 불가
    - 본인이 작성한 댓글,대댓글이 아니면 수정, 삭제 불가 
  
  - 궁금한 내용 검색
    제목/내용으로 분류하여 검색어를 입력하면 선택한 조건에 맞게 게시글 조회
  
  - 회원 관리
    - 회원 목록 조회
    관리자 권한을 가지고 있으면 회원 목록 조회 가능
    - 회원 권한 조정
    관리자 권한을 가지고 있으면 회원 권한 조정 가능
    
  - 마이 페이지 관리
    - 프로필 이미지 변경 
    원하는 프로필 이미지가 있다면 변경 가능 
    - 닉네임 변경
    원하는 닉네임으로 변경 가능 단 이미 존재하는 닉네임이면 변경 불가
    - 패스워드 변경
    본인의 현재 패스워드와 변경할 패스워드를 입력하여 패스워드 변경 단 현재 패스워드를 틀리거나 변경할 패스워드 검증에 실패할 경우 변경 불가
    - 회원 탈퇴
    회원 탈퇴 기능 30일간의 유예 기간을 주고 유예 기간안에 회원 탈퇴 취소 시 계정 복구 가능

### flow-chart

![ask-hub_](https://github.com/oreumi16/ask-hub/assets/155411194/f40de24e-fbcd-439a-8ead-bd6b9586a5dd)

*******


## 2. 개발 기술 및 환경

### 2.1 개발 기술

[기술 - Front End]

<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">  <img  src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">

[기술 - Back End]

<img  src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">  <img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white">  <img  src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">  <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">  <img  src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

[기술 - DB]

<img  src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">

### 2.2 개발환경

<img  src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">  <img  src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">  <img  src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">  <img src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">  <img src="https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white">  <img src="https://img.shields.io/badge/Discord-%235865F2.svg?style=for-the-badge&logo=discord&logoColor=white">

******


## 3. 배포 및 아키텍쳐

#### 배포 URL : http://43.202.191.219:8080/articles

![image](https://github.com/oreumi16/ask-hub/assets/155411194/a5cf9798-b45d-4c08-88a3-0cf6048b1efc)

******


## 4. 요구사항 및 기능명세

![기능명세 drawio (1)](https://github.com/oreumi16/ask-hub/assets/155411194/bc2e1ca3-678f-4f93-a430-f79ec995c21c)

******


## 5. 데이터베이스 모델링

![d59byxyyDf3J6gmnC (1)](https://github.com/oreumi16/ask-hub/assets/155411194/502a9f60-2813-4ae5-b40b-7fda0fea4a4e)

******


## 6. API 명세


******


## 7. 프로젝트 구조
[Uploadin**📦ask-hub  
 ┣ 📂.git  
 ┣ 📂ask-hub  
 ┣ 📂gradle  
 ┃ ┗ 📂wrapper  
 ┃ ┃ ┣ 📜gradle-wrapper.jar  
 ┃ ┃ ┗ 📜gradle-wrapper.properties  
 ┣ 📂src  
 ┃ ┣ 📂main  
 ┃ ┃ ┣ 📂java  
 ┃ ┃ ┃ ┗ 📂com  
 ┃ ┃ ┃ ┃ ┗ 📂example  
 ┃ ┃ ┃ ┃ ┃ ┗ 📂team16project  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RedisConfig.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SwaggerConfig.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebSecurityConfig.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleController.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArticleImageController.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂recommend  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecommandController.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReplyController.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminController.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserController.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserViewController.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Article.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Reply.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleForm.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArticleWithIdForm.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleDto.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArticleImageDto.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReplyCreateForm.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReplyDeleteRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReplyUpdateRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReReplyCreateForm.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReReplyUpdateRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReplyDto.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AddUserRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserResponse.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteUserRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UpdateUserInfoRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UpdateUserPasswordRequest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserInfo.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ControllerAdvice.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Error.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Errors.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LoginRequiredException.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArticleRepository.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReplyRepository.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleImageService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleImageServiceLocalImpl.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArticleServiceImpl.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂recommend  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RedisSetService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReplyService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReplyServiceImpl.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserDetailsServiceImpl.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserInfoService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserProfileImageService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂util  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaginationUtil.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Team16projectApplication.java  
 ┃ ┃ ┗ 📂resources  
 ┃ ┃ ┃ ┣ 📂static  
 ┃ ┃ ┃ ┃ ┣ 📂css  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜articles.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜detail.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜edit.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜form.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜mypage.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜userinfo.css  
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜admin.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜adminuser.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜login.css  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜signup.css  
 ┃ ┃ ┃ ┃ ┣ 📂images  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂recommend  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜like-default.jpg  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜like-true.jpg  
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂image  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜default-profile.png  
 ┃ ┃ ┃ ┃ ┗ 📂js  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜detail.js  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜edit.js  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜form.js  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜mypage.js  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜reply.js  
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜userinfo.js  
 ┃ ┃ ┃ ┣ 📂templates  
 ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜articles.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜detail.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜edit.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜form.html  
 ┃ ┃ ┃ ┃ ┃ ┗ 📜notice.html  
 ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┗ 📜newDetail.html  
 ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜403error.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜admin.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜adminUser.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜login.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜myinfo.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜mypage.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜mypassword.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜myprofile.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜mywithdraw.html  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜recovery.html  
 ┃ ┃ ┃ ┃ ┃ ┗ 📜signup.html  
 ┃ ┃ ┃ ┣ 📜application.properties  
 ┃ ┃ ┃ ┗ 📜data.sql  
 ┃ ┗ 📂test  strong text
 ┃ ┃ ┗ 📂java  
 ┃ ┃ ┃ ┗ 📂com  
 ┃ ┃ ┃ ┃ ┗ 📂example  
 ┃ ┃ ┃ ┃ ┃ ┗ 📂team16project  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArticleControllerTest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reply  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReplyControllerTest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user  
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserControllerTest.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Team16projectApplicationTests.java  
 ┣ 📜.gitignore  
 ┣ 📜build.gradle  
 ┣ 📜gradlew  
 ┣ 📜gradlew.bat  
 ┣ 📜README.md  
 ┗ 📜settings.gradle

******


## 8. 화면설계

![화면설계](https://github.com/oreumi16/ask-hub/assets/155411194/d1f0ece0-0a39-451b-b8d4-42077e8b2be1)

******



