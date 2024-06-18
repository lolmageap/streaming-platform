package cherhy.example.plugins.config

import cherhy.example.plugins.util.JwtManager
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

val dependencyInjectionModule = module {
    single { JwtManager() }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(dependencyInjectionModule)
    }
}