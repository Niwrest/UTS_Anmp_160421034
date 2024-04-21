package com.ubayadef.uts_hobby_160421034.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class SignUpViewModel (application: Application): AndroidViewModel(application){
    var success = MutableLiveData<Boolean>(false)
    val TAG = "volleyFilmTag"
    private var queue: RequestQueue?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun signUp(username: String, firstName: String, lastName:String,
               email: String, password: String){


        val url = "http://10.0.2.2/erwin/php/hobby_register.php"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("signup", it.toString())

                val obj = JSONObject(it)
                if(obj.getString("result") == "OK"){
                    success.value = true
                }


            },
            {
                Log.d("signup", it.toString())

            }

        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["first_name"] = firstName
                params["last_name"] = lastName
                params["email"] = email
                params["password"] = password
                return params
            }
        }

        stringRequest.tag =TAG
        queue?.add(stringRequest)



    }


}