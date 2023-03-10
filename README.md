# ๐ Likelion ๊ฐ์ธ ํ๋ก์ ํธ

## 
๐งBackEnd<br>
- Language/Skills : JAVA11, JPA, JWT, Spring Security <br>
- Framework : Springboot 2.7.5<br>
- DB: Mysql<Br>
- Build tool: Gradle<br>
- ๋ฐฐํฌ: AWS ubuntu<br>

๐ง CI / CD: gitlab, crontab <br>
๐ง API ๋ฌธ์: Swagger UI <br>

## ๊ตฌํ๊ธฐ๋ฅ ์ฒดํฌ๋ฆฌ์คํธ
โ๏ธ ์ธ์ฆ/์ธ๊ฐ ํํฐ ๊ตฌํ<br>
โ๏ธ ๊ฒ์๊ธ ์กฐํ / ์์  / ์ญ์  API ๊ตฌํ<br>
โ๏ธ ํ์๊ฐ์ ํ์คํธ ์ฝ๋ ์์ฑ<br>
โ๏ธ Swagger๋ฅผ ์ด์ฉํ์ฌ ๊ฒ์๊ธ API ๋ฌธ์ ์๋ํ<br>
โ๏ธ develop ๋ธ๋์น์ push ํ  ๊ฒฝ์ฐ project-lion-api ์ ํ๋ฆฌ์ผ์ด์ AWS EC2 ์๋ฒ์ ์๋์ผ๋ก ๋ฐฐํฌ ๋๋๋ก ๊ตฌํ<br>

[๊ตฌํ์ค ํ์ตํ ๋ด์ฉ]<br>
- stream: ์คํธ๋ฆผ ์ฒด์ธ ๋ฉ์๋ ๋ฐ ์ฌ์ฉ ๋ฐฉ๋ฒ ํ์ต 
- lambda: ๋๋ค ํํ
- ์ ์  ํฉํ ๋ฆฌ ํจํด์ ๋ํ์ฌ ๊ณต๋ถ: ๋ฉ์๋๋ช, ์ฌ์ฉํ๋ ์ด์ 
- spring security
### 1์ฐจ ๋ฏธ์
- ๋ฐฐํฌ ์ฃผ์: [swagger ๋งํฌ](http://ec2-3-39-233-233.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/)
- Swagger์์ Jwt Token์ผ๋ก ํ์คํธ: [๋งํฌ](https://velog.io/@may_yun/swagger-JWT-%EC%9D%B8%EC%A6%9D%EC%9D%B8%EA%B0%80-%EC%9A%94%EC%B2%AD)
#### User<br>
  โ๏ธ ํ์๊ฐ์<br>
  ๐ EndPoint: POST /api/v1/users/join <br>

  ํ์ ์ธ์ฆ,์ธ๊ฐ - ํ์๊ฐ์ ๊ตฌํ ์๋ฃ<br>
  ํ์ ๊ฐ์ ์์ฒญ ์์ด๋ ์ค๋ณต ์ฒดํฌ ํ ์ ์ฅํ๋ ๋ก์ง<br>
  springSecurity ํ์๊ฐ์<br>
  password encode -> DB ์ ์ฅ<br>
  ![img.png](join.png)
  <hr>
  โ๏ธ ๋ก๊ทธ์ธ<br>
  ๐EndPoint: POST /api/v1/users/login <br>

  ๋ก๊ทธ์ธ ์์ฒญ์ ์์ด๋, ๋น๋ฐ๋ฒํธ ์ฒดํฌ ํ ์ผ์นํ๋ฉด Token๊ฐ ๋ฐํ <br>
  
  ![img.png](login.png)
  
<hr>
  
#### Post(CRUD) ๊ณตํต ๊ธฐ๋ฅ Auditing ์ ์ฉ
: ๊ฒ์๊ธ ์์ฑ, ์กฐํ(๋จ๊ฑด ์์ธ์กฐํ, ์ ์ฒด paging), ์์ , ์ญ์  <br>

  โ๏ธ ์ ์ฒด ์กฐํ(ํ์ด์ง)<br>
  ๐ EndPoint: GET /api/v1/posts <br>

  ![img.png](select-all-1.png)
  ![img_1.png](select-all-2.png)

  <hr>

  โ๏ธ ๊ฒ์๊ธ ์์ธ์กฐํ<br>
  ๐ EndPoint: GET /api/v1/posts/{postsId} <br>
  ![img.png](postdetail.png)

  <hr>

  โ๏ธ ๊ฒ์๊ธ ์์ฑ<br>
  ๐ EndPoint: POST /api/v1/posts <br>
  Authentication ํ์ ์ ํ ๊ฒ์๋ฌผ ๋ฑ๋ก ์ ๊ทผ ๊ฐ๋ฅ<br>
  Entity<->DTO ๋ณํ: PostsCreateFactory<br>
  ![img.png](post-add.png)

  <hr>

  โ๏ธ ๊ฒ์๊ธ ์ญ์ <br>
  ๐ EndPoint: DELETE /api/v1/posts <br>
  ์์ฑ์์ ์ญ์  ์์ฒญ์(๋ก๊ทธ์ธ ์ ์ ) ์ผ์น ์ฒดํฌ ํ ์ญ์  
  ![img.png](post-delete.png)

  <hr>

  โ๏ธ ๊ฒ์๊ธ ์์ <br>
  ๐ EndPoint: PUT /api/v1/posts/{postsId} <br>
  ์์ฑ์์ ์์  ์์ฒญ์(๋ก๊ทธ์ธ ์ ์ ) ์ผ์น ์ฒดํฌ ํ ์ญ์ <br>
  update query
  ![img.png](modify.png)

### swagger
![img.png](swagger.png)

![img.png](swagger-detail.png)