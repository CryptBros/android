package cryptbros.starport.data

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Bruno Capezzali on 2/3/18.
 */
@Dao
interface SessionDao {
    @get:Query("SELECT * FROM session LIMIT 1")
    val active: Session

    @Insert
    fun insert(session: Session)

    @Delete
    fun delete(session: Session)
}
