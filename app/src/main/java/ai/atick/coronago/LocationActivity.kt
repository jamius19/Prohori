package ai.atick.coronago

import android.content.Context
import android.widget.Toast
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LocationActivity(private val context: Context) {

    val database: AppDatabase = AppDatabase(context)

    val latitudeList: ArrayList<String> = ArrayList()
    val longitudeList: ArrayList<String> = ArrayList()
    val timestampList: ArrayList<String> = ArrayList()

    fun updateLocation() {
        LocationServices
            .getFusedLocationProviderClient(context)
            .lastLocation
            .addOnSuccessListener { location ->
                latitudeList.add(location.latitude.toString())
                longitudeList.add(location.longitude.toString())
                timestampList.add(getTimeStamp())

                database.putListString("latitudeList", latitudeList)
                database.putListString("longitudeList", longitudeList)
                database.putListString("timestampList", timestampList)
            }
    }

    private fun getTimeStamp(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.US)
        val date = Calendar.getInstance().time
        return dateFormat.format(date)
    }
}