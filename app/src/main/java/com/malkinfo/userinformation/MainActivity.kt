package com.malkinfo.userinformation

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.malkinfo.userinformation.adapter.UserListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val Base_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: UserListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    var REQUEST_CODE = 111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()


        /*userRecyclerView.setHasFixedSize(true)
        LinearLayoutManager = LinearLayoutManager(this)
        userRecyclerView.layoutManager = LinearLayoutManager
        getMyData()*/
    }

    //Retrofit work

    /*private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_URL)
            .build()
            .create(ApiInterface::class.java)

        val dataRetrofit = retrofitBuilder.getData()

        dataRetrofit.enqueue(object : Callback<List<RetrofitDataItem>?> {
            override fun onResponse(
                call: Call<List<RetrofitDataItem>?>,
                response: Response<List<RetrofitDataItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter = UserListAdapter(baseContext, responseBody)
                myAdapter.notifySetDataChanged()
                userRecyclerView.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<RetrofitDataItem>?>, t: Throwable) {
                d("MainActivity", "onFailure: " + t.message)
            }
        })
    }*/

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED

        ){
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),REQUEST_CODE
            )

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (
            requestCode == REQUEST_CODE
            && grantResults[0]== PackageManager.PERMISSION_GRANTED
            && grantResults[1]== PackageManager.PERMISSION_GRANTED
            && grantResults[2]== PackageManager.PERMISSION_GRANTED
        ){
            Toast.makeText(this,"All Permission Granted",Toast.LENGTH_SHORT).show()
        }
    }


}