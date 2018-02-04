package cryptbros.starport.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Bruno Capezzali on 2/3/18.
 */
@Entity
class Session {
    @PrimaryKey
    private val uid: Int = 0

    @ColumnInfo(name = "public_key")
    val publicKey: String? = null

    @ColumnInfo(name = "login_date")
    val loginDate: Date? = null
}
