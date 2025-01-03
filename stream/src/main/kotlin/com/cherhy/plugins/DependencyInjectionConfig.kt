package com.cherhy.plugins

import com.cherhy.external.VideoStorage
import com.cherhy.repository.*
import com.cherhy.service.*
import com.cherhy.usecase.*
import com.cherhy.util.constant.MongoConst.MONGO_DATABASE
import com.cherhy.util.constant.MongoConst.MONGO_URL
import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureDependencyInjection() {
    val mongoUrl = HoconApplicationConfig(ConfigFactory.load()).property(MONGO_URL).getString()
    val mongoDatabase = HoconApplicationConfig(ConfigFactory.load()).property(MONGO_DATABASE).getString()

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
    single<ObjectMapper> { ObjectMapper() }
    single<VideoStorage> { VideoStorage() }
    single<KafkaPublisher> { KafkaPublisher(get()) }

    single<CreatePostUseCase> { CreatePostUseCase(get(), get(), get()) }
    single<GetPostUseCase> { GetPostUseCase(get()) }
    single<UpdatePostUseCase> { UpdatePostUseCase(get(), get(), get(), get(), get()) }
    single<DeletePostUseCase> { DeletePostUseCase(get(), get(), get(), get(), get()) }
    single<GetVideoUseCase> { GetVideoUseCase(get(), get(), get()) }
    single<PurchaseVideoUseCase> { PurchaseVideoUseCase(get(), get(), get(), get()) }

    single<ReadCacheService> { ReadCacheService() }
    single<WriteCacheService> { WriteCacheService() }
    single<ReadPostService> { ReadPostService(get()) }
    single<ReadVideoService> { ReadVideoService(get()) }
    single<WritePostService> { WritePostService(get()) }
    single<WriteVideoService> { WriteVideoService(get()) }
    single<ReadPurchasedVideoService> { ReadPurchasedVideoService(get()) }
    single<WritePurchasedVideoService> { WritePurchasedVideoService(get()) }

    single<PostRepository> { PostRepositoryImpl() }
    single<VideoRepository> { VideoRepositoryImpl() }
    single<PurchasedVideoRepository> { PurchasedVideoRepositoryImpl() }
}