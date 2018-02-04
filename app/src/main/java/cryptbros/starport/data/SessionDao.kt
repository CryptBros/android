package cryptbros.starport.data

import android.arch.persistence.room.*

/**
 * Created by Bruno Capezzali on 2/3/18.
 */
@Dao
interface SessionDao {
    @get:Query("SELECT * FROM sessions LIMIT 1")
    val active: Session

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(session: Session)

    @Query("DELETE FROM sessions")
    fun delete()
}
