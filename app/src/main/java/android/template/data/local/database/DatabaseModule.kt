package android.template.data.local.database

import android.content.Context
import androidx.room.Room
import com.theolin.filemanagerapplication.Data.Database.FileManagerDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideHomeDao(appDatabase: AppDatabase): FileManagerDb {
        return appDatabase.fileDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "FileManager"
        ).build()
    }
}
