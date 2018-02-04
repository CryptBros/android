package cryptbros.starport

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cryptbros.starport.data.DatabaseManager
import cryptbros.starport.data.Session
import android.os.AsyncTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        object : AsyncTask<Void, Void, Int?>() {
            override fun doInBackground(vararg params: Void): Int? {

                DatabaseManager.getInstance(this@MainActivity).sessionDao().delete()
                DatabaseManager.getInstance(this@MainActivity).sessionDao().active

                DatabaseManager.getInstance(this@MainActivity).sessionDao().insert(Session(publicKey = "0x123"))
                val activeSession = DatabaseManager.getInstance(this@MainActivity).sessionDao().active
                Log.d("", activeSession.publicKey)

                return null
            }

        }.execute()
    }
}
