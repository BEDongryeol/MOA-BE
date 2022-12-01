# MOA-BE
Money Of the Army Backend
---
## 개요
군인을 위한 목표달성 펀세이빙 서비스입니다. 

홈 화면
- 현재까지 저축한 금액, 적금, 개인 목표, 비상금 계좌와 관련 정보를 조회할 수 있습니다.

모으기
- 군적금 상품 가입여부에 따른 간편 가입 서비스 제공
- 제공된 카테고리 별 자유 목표 설정 및 저축 서비스
- 입출금이 자유로운 비상금 만들기

챌린지
- 운영 측에서 제공하는 챌린지를 통한 리워드 제공
- 기능을 통한 지속적인 앱 접속 유도

리워드
- 받은 리워드를 통한 리워드 제공
---
## Skill Set
- **[언어]** Java 11
- **[프레임워크]** Spring Boot 2.6.3
- **[데이터베이스]** MySQL, JPA, H2
- **[서버]** Docker-compose, AWS EC2
- **[테스트]** JUnit5
---
- **금융 관련 ERD 설계 및 구축**
- **Server 구축 및 배포** (Docker-compose, AWS EC2)
    - Docker 볼륨 매핑을 통한 DB 컨테이너에 더미 데이터 추가
    - Docker-compose를 통해 Spring 어플리케이션과 DB 연동
- **API 개발**
    - LSP(Liskov Substitution Principle)를 활용한 request에 따라 다른 response 전달
        - Builder, AllArgsConstructor 활용
    - Presentational layer와 Service layer의 독립적인 구성을 위해 DTO, Mapper 활용
