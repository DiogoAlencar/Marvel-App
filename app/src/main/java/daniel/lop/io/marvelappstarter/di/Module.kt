package daniel.lop.io.marvelappstarter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import daniel.lop.io.marvelappstarter.data.remote.ServiceApi
import daniel.lop.io.marvelappstarter.util.Constants.APIKEY
import daniel.lop.io.marvelappstarter.util.Constants.BASE_URL
import daniel.lop.io.marvelappstarter.util.Constants.HASH
import daniel.lop.io.marvelappstarter.util.Constants.PRIVATE_KEY
import daniel.lop.io.marvelappstarter.util.Constants.PUBLIC_KEY
import daniel.lop.io.marvelappstarter.util.Constants.TS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Singleton

/*
 *  Dagger Hilt
 *  10º passo => Fornece uma instância única para a app
 */
@Module
@InstallIn(SingletonComponent::class)
object Module {

    /*
     *  Dagger Hilt
     *  11º passo => Fornece uma instância única, atualiza automaticamente,
     *  prove essa instancia
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggin = HttpLoggingInterceptor()
        loggin.level = HttpLoggingInterceptor.Level.BODY

        // monta url conforme API requer
        return OkHttpClient().newBuilder().addInterceptor { chain ->
            val currentTimeStamp = System.currentTimeMillis()
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter(TS, currentTimeStamp.toString())
                .addQueryParameter(APIKEY, PUBLIC_KEY)
                .addQueryParameter(
                    HASH,
                    provideToMd5Hash(
                        currentTimeStamp.toString() + PRIVATE_KEY + PUBLIC_KEY
                    )
                )
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
            .addInterceptor(loggin)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideToMd5Hash(encrypted: String): String {
        var pass = encrypted
        var encryptedString: String? = null
        val md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
            md5.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, md5.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            encryptedString = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }
        Timber.d("hash -> $encryptedString")
        return encryptedString ?: ""
    }
}