# Reference Documentation

### 각자 다른 framework의 http header에서 사용자 정보를 가져오는 방법
@[UserIdFromHeader](common%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcherhy%2Fcommon%2Fannotation%2FUserIdFromHeader.kt)가 붙어있다면 [UserIdFromHeaderAspect](common%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcherhy%2Fcommon%2Fconfig%2FUserIdFromHeaderAspect.kt)에서 @[UserIdResolver](common%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcherhy%2Fcommon%2Fconfig%2FUserIdResolver.kt) 인터페이스를 호출해서 http header에서 사용자 정보를 가져온다.  
이때, 각 framework마다 사용자 정보를 가져오는 방법이 다르기 때문에 각 framework마다 UserIdResolver를 구현해주어야 한다.  
> example
> - spring webflux framework : [UserIdResolverImpl.kt](payment%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcherhy%2Fpayment%2Fconfig%2FUserIdResolverImpl.kt)   
> - ktor framework : [UserIdResolverImpl.kt](user%2Fsrc%2Fmain%2Fkotlin%2Fcherhy%2Fexample%2Fplugins%2Fconfig%2FUserIdResolverImpl.kt)
