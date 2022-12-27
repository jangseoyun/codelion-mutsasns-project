# 📌 Likelion 개인 프로젝트

## 
🟧BackEnd<br>
- Language/Skills : JAVA11, JPA, JWT, Spring Security <br>
- Framework : Springboot 2.7.5<br>
- DB: Mysql<Br>
- Build tool: Gradle<br>
- 배포: AWS t3small<br>

🟧 CI / CD: gitlab, crontab <br>
🟧 API 문서: Swagger UI <br>

## 구현기능
### 1차 미션
- 배포 주소: [swagger 링크](http://ec2-3-39-233-233.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/)
#### User<br>
  ☑️ 회원가입<br>
  회원 인증,인가 - 회원가입 구현 완료<br>
  회원 가입 요청 아이디 중복 체크 후 저장하는 로직<br>
  springSecurity 회원가입<br>
  password encode -> DB 저장<br>
  ![img.png](join.png)
  <hr>
  ☑️ 로그인<br>
  로그인 요청시 아이디, 비밀번호 체크 후 일치하면 Token값 반환 <br>
  
  ![img.png](login.png)
  
<hr>
  
#### Post(CRUD) 공통 기능 Auditing 적용
: 게시글 작성, 조회(단건 상세조회, 전체 paging), 수정, 삭제 <br>

  ☑️ 전체 조회(페이징)
  ![img.png](select-all-1.png)
  ![img_1.png](select-all-2.png)
  
  ☑️ 게시글 상세조회
  ![img.png](postdetail.png)

  ☑️ 게시글 작성<br>
  Authentication 회원 제한 게시물 등록 접근 가능<br>
  Entity<->DTO 변환: PostsCreateFactory<br>
  ![img.png](post-add.png)

  ☑️ 게시글 삭제
  ![img.png](post-delete.png)

### swagger
![img.png](swagger.png)

![img.png](swagger-detail.png)