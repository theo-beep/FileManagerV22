package android.template.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theolin.filemanagerapplication.Data.Database.FileManagerDb
import com.theolin.filemanagerapplication.Data.Database.FileStore

@Database(entities = [FileStore::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fileDao(): FileManagerDb
}

