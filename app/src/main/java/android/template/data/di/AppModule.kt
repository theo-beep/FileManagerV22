package android.template.data.di

import android.template.common.Constants
import android.template.data.Repository.FileManagerRepository
import android.template.data.Repository.FileManagerRepositoryImpl
import android.template.data.network.FileManagerApi
import com.theolin.filemanagerapplication.Data.Database.FileManagerDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFileManagerApi(): FileManagerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FileManagerApi::class.java)
    }

    @Provides
    fun provideFileManagerRepositoryRepository(api : FileManagerApi ,db : FileManagerDb) : FileManagerRepository {
        return FileManagerRepositoryImpl(api = api , db = db)
    }

}

