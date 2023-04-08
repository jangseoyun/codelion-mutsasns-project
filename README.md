# 📌 Likelion 개인 프로젝트
> - 배포 주소: [swagger 링크](http://ec2-3-39-233-233.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/)
> - Swagger에서 JWT Token으로 테스트하는 방법: [블로그 링크](https://velog.io/@may_yun/swagger-JWT-%EC%9D%B8%EC%A6%9D%EC%9D%B8%EA%B0%80-%EC%9A%94%EC%B2%AD)

---

## 1. 요구사항 분석
### 1-1) 프로젝트 기술 스택
🟧 BackEnd<br>
- Language/Skills : JAVA11, SpringBoot Web, Spring Data JPA, Lombok, Spring Security, JWT <br>
- Framework : Springboot 2.7.5<br>
- DB: Mysql<Br>
- Build tool: Gradle 7.5.1<br>
- 서버: AWS EC2 ubuntu<br>
- 배포: Docker

🟧 CI / CD: gitlab, crontab <br>
🟧 API 문서: Swagger UI <br>

### 1-2) 기능 명세서
> 공통 URL: /api/v1

| 구분  | Method |              URI               |    Description     |             요구사항             |
|:---:|:------:|:------------------------------:|:------------------:|:----------------------------:|
| 회원  |  POST  |          /users/join           |        회원가입        |          아이디 중복 체크           |
|     |  POST  |          /users/login          |        로그인         |      로그인 JWT 토큰 발급, 인증       |
| 포스트 |  GET   |             /posts             |     포스트 전체 조회      |      Pageable(최신순, 20개)      |
|     |  GET   |        /posts/{postsId}        |    단일 포스트 상세 조회    |                              |
|     |  POST  |             /posts             |       포스트 등록       |        로그인 인증 후 작성 가능        |
|     |  PUT   |          /posts/{id}           |       포스트 수정       |      수정 권한: 작성자, ADMIN       |
|     | DELETE |          /posts/{id}           |       포스트 삭제       |      삭제 권한: 작성자, ADMIN       |
|     |  GET   |           /posts/my            |        마이피드        |   해당 유저의 포스트 작성 리스트 페이징 조회   |
| 댓글  |  POST  |   /posts/{postsId}/comments    |       댓글 등록        |        로그인 인증 후 작성 가능        |
|     |  GET   |   /posts/{postsId}/comments    |      댓글 단건 조회      |      Pageable(최신순, 10개)      |
|     |  PUT   | /posts/{postsId}/comments/{id} |       댓글 수정        |      수정 권한: 작성자, ADMIN       |
|     | DELETE | /posts/{postsId}/comments/{id} |       댓글 삭제        |      삭제 권한: 작성자, ADMIN       |
| 좋아요 |  POST  |     /posts{postsId}/likes      |    좋아요 등록 및 취소     |           중복 클릭 불가           |
|     |  GET   |     /posts/{postsId}/likes     | 포스트 게시글 좋아요 카운트 조회 |       단일 포스트 좋아요 개수 조회       |
| 알람  |  GET   |         /posts/alarms          |       알람 조회        | 특정 포스트에 새 댓글, 좋아요가 눌리면 알람 등록 |

---

## 2. 기능 구현 체크리스트

### 2-1) 기능 구현

☑️ 회원 인증·인가 필터 구현<br>
- 모든 회원은 회원가입을 통해 회원이 됩니다.
- 로그인을 하지 않으면 SNS 기능 중 피드를 보는 기능만 가능합니다.
- 로그인한 회원은 글쓰기, 수정, 댓글, 좋아요, 알림 기능이 가능합니다.

☑️ 포스트 조회 / 수정 / 삭제 API 구현<br>
- 포스트를 쓰려면 회원가입 후 로그인(Token받기)을 해야 합니다.
- 포스트의 길이는 총 300자 이상을 넘을 수 없습니다.
- 포스트의 한 페이지는 20개씩 보이고 총 몇 개의 페이지인지 표시가 됩니다.
- 로그인 하지 않아도 글 목록을 조회 할 수 있습니다.
- 수정 기능은 글을 쓴 회원만이 권한을 가집니다.
- 포스트의 삭제 기능은 글을 쓴 회원만이 권한을 가집니다.

☑️ 마이 피드<br>
- 로그인 한 회원은 자신이 작성한 글 목록을 볼 수 있습니다.

☑️ 댓글 기능 구현<br>
- 댓글은 회원만이 권한을 가집니다.
- 글의 길이는 총 100자 이상을 넘을 수 없습니다.
- 회원은 다수의 댓글을 달 수 있습니다.

☑️ 좋아요 기능 구현<br>
- 좋아요는 회원만 권한을 가집니다.
- 좋아요 기능은 취소가 가능합니다.

☑️ 알림 기능 구현<br>
- 알림은 회원이 자신이 쓴 글에 대해 다른회원의 댓글을 올리거나 좋아요시 받는 기능입니다.
- 알림 목록에서 자신이 쓴 글에 달린 댓글과 좋아요를 확인할 수 있습니다.

☑️ 회원가입 테스트 코드 작성<br>
☑️ Swagger를 이용하여 게시글 API 문서 자동화<br>
☑️ develop 브랜치에 push 할 경우 project-lion-api 애플리케이션 AWS EC2 서버에 자동으로 배포 되도록 CI/CD<br>

## 2-2) Return Example(Json 형태)타입
### 🔗 [Postman Example 문서화 Link](https://documenter.getpostman.com/view/21461415/2s93XsXR8m)

---

## 3. ERD

<img width="879" alt="image" src="https://user-images.githubusercontent.com/94329274/230714556-7ccd2194-549b-4dc8-b9eb-1bedeeaf8f73.png">

---

## 4. 예외처리
| HTTP Status Code |Error Message|   Descriptoion   |
|:----------------:|---|:----------------:|
|       409        |DUPLICATED_USER_NAME| UserName이 중복됩니다. |
|       404        |USERNAME_NOT_FOUND|   Not founded    |
|       401        |INVALID_PASSWORD|  패스워드가 잘못되었습니다.  |
|       401        |        INVALID_TOKEN        |    잘못된 토큰입니다.    |
|       401        |       INVALID_PERMISSION       |  사용자가 권한이 없습니다.  |
|       404        |       POST_NOT_FOUND       |  해당 포스트가 없습니다.   |
|       500        |      DATABASE_ERROR      |      DB 에러       |

---

## 5. 구현중 학습한 내용<br>
- 정적 팩토리 패턴에 대하여 공부: 메서드명, 사용하는 이유
- Spring Security

### 5-1) <br>
  
### 5-2) 테스트 코드 Jacoco 적용

---

## 6. 브랜치 생성 
> 이슈, Merge Request, branch 생성 후 작업  

<img width="483" alt="image" src="https://user-images.githubusercontent.com/94329274/230711676-0e568ab1-4e09-42bf-a4e2-542c39e26e68.png">

---

## 7. swagger-ui
![img.png](swagger.png)

![img.png](swagger-detail.png)