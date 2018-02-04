package cryptbros.starport

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic
import com.google.android.gms.vision.barcode.Barcode
import cryptbros.starport.utils.Utils
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever


/**
 * Created by steve on 2/3/2018.
 */

class BarcodeReaderActivity : AppCompatActivity(), BarcodeRetriever {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.barcode_reader_activity)

        val barcodeCapture = supportFragmentManager.findFragmentById(R.id.barcode_reader_fragment) as BarcodeCapture
        barcodeCapture.setRetrieval(this)


    }

    override fun onRetrieved(barcode: Barcode?) {
        val barcodeValue = barcode?.displayValue

        if (Utils.isValidStellarAddressString(barcodeValue)) {
            val intent = Intent()
            intent.putExtra("address", barcodeValue)
            setResult(Activity.RESULT_OK, intent)
            finish()
            return
        }

        vibrateAndShowToast("Not a valid Stellar Address :'(")
    }

    override fun onRetrievedMultiple(closetToClick: Barcode?, barcode: MutableList<BarcodeGraphic>?) {
        // TODO Send to next activity
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRetrievedFailed(reason: String?) {
        runOnUiThread {
            Toast.makeText(this, reason, Toast.LENGTH_LONG).show()
        }
    }

    override fun onPermissionRequestDenied() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun vibrateAndShowToast(message: String) {
        runOnUiThread {
            val vibrator: Vibrator = this.getSystemService(android.content.Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()

        super.onBackPressed()

    }

}