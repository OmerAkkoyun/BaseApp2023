package com.omerakkoyun.mybaseapp1.di

import com.omerakkoyun.mybaseapp1.BuildConfig
import com.omerakkoyun.mybaseapp1.network.ApiFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //intercaptor oluşturalım, servis işlemlerinde loglama, ne gidiyor ne geliyor vs.
    @Singleton
    @Provides
    fun provideHttpLoggerIntercaptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }


    // Clint oluşturalım okuma ve bekleme sürelerini ve intercaptor'u verelim.
    @Singleton
    @Provides
    fun provideHttpClint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        // içerideki parametreyi (httpLoggingInterceptor) yukarıdaki provider bizim yerimize dolduruyor.
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    // Servisten gelen data json geldiği için GsonConverter gerekecek, oluşturalım.
    @Singleton
    @Provides
    fun provideConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }


    // Servis cagrisini yapabilmek icin RETROFIT kullanacagiz. Provider'ini oluşturalım.
    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory): Retrofit {

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient).addConverterFactory(gsonConverterFactory).build()

    }


    //Servis çağrılarının ENDPOINT'lerini çağırmak için "Api factory" diye bir interface oluşturmuştuk.
    // Erişmek için, onun providerini oluşturalım.
    @Singleton
    @Provides
    fun provideApiFactory(retrofit: Retrofit):ApiFactory{
        return retrofit.create(ApiFactory::class.java)

    }


}



