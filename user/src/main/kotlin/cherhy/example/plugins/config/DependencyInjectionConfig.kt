package cherhy.example.plugins.config

import cherhy.example.plugins.repository.UserRepository
import cherhy.example.plugins.repository.UserRepositoryImpl
import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.service.WriteUserService
import cherhy.example.plugins.usecase.LoginUseCase
import cherhy.example.plugins.usecase.SignUpUseCase
import cherhy.example.plugins.util.JwtManager
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

val dependencyInjectionModule = module {
    single { JwtManager() }
    single { LoginUseCase(get()) }
    single { SignUpUseCase(get(), get()) }

    single { WriteUserService(get()) }
    single { ReadUserService(get()) }

    single<UserRepository> { UserRepositoryImpl() }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(dependencyInjectionModule)
    }
}