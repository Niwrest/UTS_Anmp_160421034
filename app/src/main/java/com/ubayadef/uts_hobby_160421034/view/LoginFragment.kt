package com.ubayadef.uts_hobby_160421034.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubayadef.uts_hobby_160421034.R
import com.ubayadef.uts_hobby_160421034.databinding.FragmentLoginBinding
import com.ubayadef.uts_hobby_160421034.viewmodel.LogInViewModel


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginSignup()
            Navigation.findNavController(binding.root).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)

        binding.btnSignIn.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPaassword.text.toString()
            viewModel.login(username, password)
            observeViewModel()
        }
    }

    fun observeViewModel(){
        viewModel.userIDLD.observe(viewLifecycleOwner, Observer {
                val sharedPrefs = requireContext().getSharedPreferences(
                    "com.ubayadef.uts_hobby_160421034",
                    Context.MODE_PRIVATE
                )
                var editor: SharedPreferences.Editor = sharedPrefs.edit()

                editor.putString("id", viewModel.userIDLD.value.toString())
                editor.apply()

                val action = LoginFragmentDirections.actionLoginHome()
                Navigation.findNavController(binding.root).navigate(action)

        })
    }
}