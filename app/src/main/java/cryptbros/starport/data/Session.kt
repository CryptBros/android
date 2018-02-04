package cryptbros.starport.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Bruno Capezzali on 2/3/18.
 */
@Entity(tableName = "sessions")
data class Session (
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "public_key") var publicKey: String
)
