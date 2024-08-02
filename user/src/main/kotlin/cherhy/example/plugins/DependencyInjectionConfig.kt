package cherhy.example.plugins

import cherhy.example.repository.AuthorityRepository
import cherhy.example.repository.AuthorityRepositoryImpl
import cherhy.example.repository.UserRepository
import cherhy.example.repository.UserRepositoryImpl
import cherhy.example.service.ReadAuthorityService
import cherhy.example.service.ReadUserService
import cherhy.example.service.WriteAuthorityService
import cherhy.example.service.WriteUserService
import cherhy.example.usecase.LoginUseCase
import cherhy.example.usecase.RefreshTokenUseCase
import cherhy.example.usecase.SignUpUseCase
import cherhy.example.util.JwtManager
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(dependencyInjectionModule)
    }
}

val dependencyInjectionModule = module {
    single { JwtManager() }
    single { LoginUseCase(get(), get(), get()) }
    single { SignUpUseCase(get(), get(), get()) }
    single { RefreshTokenUseCase(get(), get(), get()) }

    single { WriteUserService(get()) }
    single { ReadUserService(get()) }
    single { WriteAuthorityService(get()) }
    single { ReadAuthorityService(get()) }

    single<UserRepository> { UserRepositoryImpl() }
    single<AuthorityRepository> { AuthorityRepositoryImpl() }
}