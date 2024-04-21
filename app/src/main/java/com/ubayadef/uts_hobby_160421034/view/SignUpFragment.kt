package com.ubayadef.uts_hobby_160421034.view

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
import com.ubayadef.uts_hobby_160421034.databinding.FragmentSignUpBinding
import com.ubayadef.uts_hobby_160421034.viewmodel.SignUpViewModel


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUps.setOnClickListener{
            val username = binding.txtUsernames.text.toString()
            val firstName = binding.txtFirstNames.text.toString()
            val lastName = binding.txtLastName.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            val repeatPassword = binding.txtConf.text.toString()


            if(password != repeatPassword){
                Toast.makeText(requireContext(), "password and repeat password are not same", Toast.LENGTH_SHORT).show()

            }

            else{
                viewModel.signUp(username, firstName,lastName,email,password)
                observeViewModel()

            }
        }

    }

    fun observeViewModel(){
        viewModel.success.observe(viewLifecycleOwner, Observer {
            val action = SignUpFragmentDirections.actionSignupLogin()
            Navigation.findNavController(binding.root).navigate(action)


        })
    }
}