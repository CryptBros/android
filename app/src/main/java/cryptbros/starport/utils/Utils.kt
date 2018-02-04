package cryptbros.starport.utils

/**
 * Created by steve on 2/4/2018.
 */

object Utils {

    fun isValidStellarAddressString(addressString: String?): Boolean {
        if (addressString == null) {
            return false
        }
        return addressString.length == 64
    }
}