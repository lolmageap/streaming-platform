# STREAMING SERVICE MODULE
youtube, netflix와 같은 영상 스트리밍 서비스를 구현하는 모듈입니다.

## STACK
- ORM : ktorm
- DB : postgresql
- DI : koin
- TEST : kotest
- DOCUMENT : restdocs
- STORAGE : minio

## TODO

- [ ] base entity 정의하기
- [ ] 영상 업로드, 스트리밍 구현
- [ ] 영상 카테고리, 좋아요, 조회수, 검색 구현 (동시성 고려)
- [ ] 영상 추천 알고리즘 구현
- [ ] 회원이 돈을 주고 영상을 구매하는 기능을 Kafka를 이용해서 구현(분산 트랜잭션 방식)