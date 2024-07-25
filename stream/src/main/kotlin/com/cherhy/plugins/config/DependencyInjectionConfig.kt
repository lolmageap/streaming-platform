package com.cherhy.plugins.config

import com.cherhy.plugins.repository.PostRepository
import com.cherhy.plugins.repository.PostRepositoryImpl
import com.cherhy.plugins.repository.VideoRepository
import com.cherhy.plugins.repository.VideoRepositoryImpl
import com.cherhy.plugins.service.ReadPostService
import com.cherhy.plugins.service.ReadVideoService
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.service.WriteVideoService
import com.cherhy.plugins.usecase.*
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureDependencyInjection() {
    val mongoUrl = environment.config.property("mongo.url").getString()
    val mongoDatabase = environment.config.property("mongo.database").getString()

    install(Koin) {
        modules(
            module {
                single { KMongo.createClient(mongoUrl).coroutine }
                single { get<CoroutineClient>().getDatabase(mongoDatabase) }
            }
        )
        modules(dependencyInjectionModule)
    }
}

val dependencyInjectionModule = module {
    single<CreatePostUseCase> { CreatePostUseCase(get(), get()) }
    single<GetPostUseCase> { GetPostUseCase(get()) }
    single<UpdatePostUseCase> { UpdatePostUseCase(get(), get(), get(), get()) }
    single<DeletePostUseCase> { DeletePostUseCase(get(), get(), get(), get()) }
    single<GetVideoUseCase> { GetVideoUseCase(get()) }

    single<ReadPostService> { ReadPostService(get()) }
    single<ReadVideoService> { ReadVideoService(get()) }
    single<WritePostService> { WritePostService(get()) }
    single<WriteVideoService> { WriteVideoService(get()) }

    single<PostRepository> { PostRepositoryImpl() }
    single<VideoRepository> { VideoRepositoryImpl() }
}