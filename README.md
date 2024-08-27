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
 - 데이터베이스 : H2, PostgreSQL, Redis, MongoDB
 - 메시징 : Kafka
 - API Gateway : Spring Cloud Gateway
 - Mapper : Spring Data R2DBC, Exposed, Ktorm, JDBC Template, kMongo
 - CI/CD : Github Actions (예정)

## TODO
- [x] gateway 모듈 추가
- [x] common module에 header에 userId 가져오는 annotation 추가(interface 정의하기)
- [ ] kafka 연동
- [ ] 결제 워크 플로우 구상
- [x] 스케줄러 모듈 추가 (spring batch)
- [ ] restdocs 적용
- [ ] restdocs dsl 개발 및 적용하기
- [ ] bdd 방식으로 개발
- [x] 아키텍처, 개선한 코드 부분에서 별도의 markdown 파일에 정리해놓기
- [ ] common module에 exception 추가
- [x] common module에 spring annotation 제거 (ktor에서 사용을 못하기 때문에)

## Reference Documentation
[HELP.md](HELP.md)에 들어가면 설정에 대한 설명이 나와있습니다.