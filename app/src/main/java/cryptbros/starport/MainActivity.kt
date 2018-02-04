package cryptbros.starport

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

import android.util.Log
import cryptbros.starport.data.DatabaseManager
import cryptbros.starport.data.Session
import android.os.AsyncTask

class MainActivity : AppCompatActivity() {

    companion object {
        val SCAN_BARCODE_REQUEST = 1
    }

    private lateinit var _addressTextView: TextView

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

        val button = findViewById<Button>(R.id.test_button) as Button
        button.setOnClickListener {
            val intent = Intent(this, BarcodeReaderActivity::class.java)
            startActivityForResult(intent, SCAN_BARCODE_REQUEST)
        }

        _addressTextView = findViewById<TextView>(R.id.address_textView) as TextView
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SCAN_BARCODE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    _addressTextView.text = data.getStringExtra("address")
                }
            }
        }
    }


}
