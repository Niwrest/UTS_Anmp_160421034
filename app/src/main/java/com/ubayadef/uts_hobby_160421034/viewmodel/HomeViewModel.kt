package com.ubayadef.uts_hobby_160421034.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadef.uts_hobby_160421034.model.Car

class HomeViewModel(application: Application): AndroidViewModel(application)  {
    val TAG = "volleyCarTag"
    val carLD = MutableLiveData<ArrayList<Car>>()
    val loadingLD = MutableLiveData<Boolean>()
    val carLoadErrorLD = MutableLiveData<Boolean>()

    private var queue: RequestQueue?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun refresh(){
        loadingLD.value  = true
        carLoadErrorLD.value = false

        val url = "http://10.0.2.2/erwin/cars.json"
        queue   = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                Log.d("show_volley", it)

                val sType = object: TypeToken<ArrayList<Car>>() {}.type
                val result = Gson().fromJson<ArrayList<Car>>(it, sType)

                carLD.value = result as ArrayList<Car>
                Log.d("cardLD", carLD.value.toString())

            },
            {
                Log.d("show_volley", it.toString())
            }
        )
        stringRequest.tag =TAG
        queue?.add(stringRequest)
    }

}