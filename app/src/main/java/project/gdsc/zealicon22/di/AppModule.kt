package project.gdsc.zealicon22.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Database
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.database.RoomDB
import project.gdsc.zealicon22.network.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Nakul
 * on 07,April,2022
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = RoomDB.invoke(context)

    @Provides
    @Singleton
    fun provideEventsDao(db: RoomDB) = db.getEventsDao()

    @Provides
    @Singleton
    fun provideRetrofitClient(@ApplicationContext context: Context): Retrofit {
        val client = OkHttpClient.Builder().connectTimeout(0, TimeUnit.SECONDS).readTimeout(
            0,
            TimeUnit.SECONDS
        ).writeTimeout(0, TimeUnit.SECONDS).addNetworkInterceptor(StethoInterceptor())

        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)!!
}