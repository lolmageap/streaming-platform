package com.cherhy.plugins.config

import com.cherhy.plugins.repository.PostRepository
import com.cherhy.plugins.repository.PostRepositoryImpl
import com.cherhy.plugins.repository.VideoRepository
import com.cherhy.plugins.repository.VideoRepositoryImpl
import com.cherhy.plugins.service.ReadPostService
import com.cherhy.plugins.service.ReadVideoService
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.service.WriteVideoService
import com.cherhy.plugins.usecase.CreatePostUseCase
import com.cherhy.plugins.usecase.GetPostUseCase
import com.cherhy.plugins.usecase.UpdatePostUseCase
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(dependencyInjectionModule)
    }
}

val dependencyInjectionModule = module {
    single<CreatePostUseCase> { CreatePostUseCase(get()) }
    single<GetPostUseCase> { GetPostUseCase(get()) }
    single<UpdatePostUseCase> { UpdatePostUseCase(get()) }

    single<ReadPostService> { ReadPostService(get()) }
    single<ReadVideoService> { ReadVideoService(get()) }
    single<WritePostService> { WritePostService(get()) }
    single<WriteVideoService> { WriteVideoService(get()) }

    single<PostRepository> { PostRepositoryImpl() }
    single<VideoRepository> { VideoRepositoryImpl() }
}