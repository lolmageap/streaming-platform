package com.cherhy.plugins

import com.cherhy.repository.PostRepository
import com.cherhy.repository.PostRepositoryImpl
import com.cherhy.repository.VideoRepository
import com.cherhy.repository.VideoRepositoryImpl
import com.cherhy.service.ReadPostService
import com.cherhy.service.ReadVideoService
import com.cherhy.service.WritePostService
import com.cherhy.service.WriteVideoService
import com.cherhy.usecase.*
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
    single<GetVideoUseCase> { GetVideoUseCase(get(), get()) }

    single<ReadPostService> { ReadPostService(get()) }
    single<ReadVideoService> { ReadVideoService(get()) }
    single<WritePostService> { WritePostService(get()) }
    single<WriteVideoService> { WriteVideoService(get()) }

    single<PostRepository> { PostRepositoryImpl() }
    single<VideoRepository> { VideoRepositoryImpl() }
}