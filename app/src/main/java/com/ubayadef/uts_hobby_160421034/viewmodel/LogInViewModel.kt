package com.ubayadef.uts_hobby_160421034.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LogInViewModel(application: Application): AndroidViewModel(application)  {
    val userIDLD = MutableLiveData<Int>()

    val TAG = "volleyUserTag"
    private var queue: RequestQueue?=null

    fun login(username: String, password: String){

        if (username.isBlank() || password.isBlank()){
            return
        }


        val url = "http://10.0.2.2/erwin/php/hobby_login.php"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("show_volley", it.toString())

                val obj = JSONObject(it)
                if (obj.getString("result") == "success"){
                    userIDLD.value = obj.getInt("id")
                }
            },
            {
                Log.d("show_volley", it.toString())

            }

        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        stringRequest.tag =TAG
        queue?.add(stringRequest)



    }

}