package cryptbros.starport.data

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.content.Context
import cryptbros.starport.utils.SingletonHolder

/**
 * Created by Bruno Capezzali on 2/3/18.
 */
@Database(entities = arrayOf(Session::class), version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {
    abstract fun sessionDao(): SessionDao

    companion object : SingletonHolder<DatabaseManager, Context>({
        Room.databaseBuilder(it.applicationContext,
                DatabaseManager::class.java, "main.db")
                .build()
    })
}
