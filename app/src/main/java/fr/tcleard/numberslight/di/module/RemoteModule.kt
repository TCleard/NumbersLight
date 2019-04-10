package fr.tcleard.numberslight.di.module

import android.os.Build
import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.BuildConfig
import fr.tcleard.numberslight.core.repo.remote.IItemRemoteRepo
import fr.tcleard.numberslight.core.repo.remote.retrofit.ItemRetrofitRemoteRepo
import fr.tcleard.numberslight.di.ApplicationScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

@Module
class RemoteModule(private val baseUrl: String = BuildConfig.BASE_URL) {

    @Provides
    @ApplicationScope
    fun provideRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .header("Content-AModel", "application/json")
                            .header("locale", Locale.getDefault().language)
                            .header("os", "${Build.VERSION.SDK_INT} ${BuildConfig.VERSION_CODE}")
                            .method(original.method(), original.body())
                            .build()
                    chain.proceed(request)
                }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpBuilder.build())
                .build()
    }

    @Provides
    @ApplicationScope
    fun provideItemRemoteRepo(retrofit: Retrofit): IItemRemoteRepo =
            ItemRetrofitRemoteRepo(retrofit)

}