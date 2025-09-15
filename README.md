# Spring 게시판 프로젝트

## 프로젝트 개요
이 프로젝트는 **Java와 Spring Framework**를 활용한 게시판 웹 애플리케이션입니다.  
사용자는 회원가입 및 로그인 후 게시글을 작성, 조회, 수정, 삭제할 수 있습니다.

## 주요 기능
- 회원 관리
  - 이메일 기반 로그인
  - 사용자 실명(username) 관리
  - 비밀번호 암호화 및 보안 처리
- 게시글 관리
  - 게시글 작성, 조회, 수정, 삭제(CRUD)
  - 제목 및 내용 최소/최대 길이 검증
- 프로젝트 구조
  - Spring Framework + MyBatis 기반
  - MVC 패턴 적용
  - RESTful API 설계

## 기술 스택
- Java 11+
- Spring Framework (Spring Boot, MVC)
- MyBatis
- HTML / CSS / JavaScript
- Tomcat 서버

## 설치 및 실행
1. 프로젝트 클론
   ```bash
   git clone https://github.com/사용자명/프로젝트명.git
````

2. IDE에서 프로젝트 열기 (STS, IntelliJ 등)
3. Maven 빌드 및 의존성 설치
4. MySQL 데이터베이스 설정 후 `application.properties` 수정
5. 서버 실행 후 `http://localhost:8080` 접속

## 향후 계획

* 회원가입 및 로그인 기능 구현
* 게시글 페이징 처리
* 댓글 기능 추가
* 프론트엔드 UI 개선

## 개발자

* 민 성원
* GitHub: [https://github.com/사용자명](https://github.com/seongwond)


