# video-platform

## 목표
동영상 플랫폼 개발

## 기능
 - 회원
 - 결제
 - 동영상 업로드
 - 스트리밍 서비스 제공

## 개발 환경
 - 아키텍처 : MSA, Hexagonal Architecture, DDD, CQRS, Event Sourcing, Saga Pattern, Multi Module
 - 인프라: Docker, Docker Compose
 - 언어 : Kotlin 2.0, Java 21
 - 프레임워크 : Spring Boot 3.2.5, Ktor, Axon Framework, Axon Server, Spring Batch
 - 데이터베이스 : H2, PostgreSQL, Redis
 - 메시징 : Kafka
 - API Gateway : Spring Cloud Gateway
 - Mapper : Spring Data R2DBC, Exposed, Ktorm, JDBC Template
 - CI/CD : Github Actions (예정)

## TODO
- [x] gateway 모듈로 뺄지 고민
- [ ] kafka 연동
- [ ] 결제 워크 플로우 구상
- [ ] 스케줄러 모듈 추가 (spring batch)
- [ ] restdocs 적용
- [ ] tdd 방식으로 개발