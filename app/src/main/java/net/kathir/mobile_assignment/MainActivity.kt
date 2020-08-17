package net.kathir.mobile_assignment

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.qualifiedName

    private lateinit var appInfoModel: AppInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendRequest.setOnClickListener {
            appInfoModel = getAppInfo()
            callApi(appInfoModel)
        }
    }

    override fun onResume() {
        super.onResume()
        appInfoModel = getAppInfo()
        callApi(appInfoModel)
    }

    fun getAppInfo() : AppInfoModel
    {
        val appName = getResources().getString(R.string.app_name);
        val osVersion = Build.VERSION.SDK_INT.toString()
        val osName = "Android"
        val timeStamp =  System.currentTimeMillis()
        Log.d(TAG,"GET_INFO "+ appName + "OS " + osVersion + "OS NAme " + osName + " TimeStamp " + timeStamp)
        val appInfoModel = AppInfoModel(appName,osVersion,osName,timeStamp)
        return appInfoModel

    }

    fun callApi(appInfoModel: AppInfoModel)
    {
        val request = RetrofitBuilder.apiService
        val call = request.sendInfo(appInfoModel)
        call.enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
            }

        })
    }

}