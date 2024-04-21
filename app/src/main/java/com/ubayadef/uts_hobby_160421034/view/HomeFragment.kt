package com.ubayadef.uts_hobby_160421034.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubayadef.uts_hobby_160421034.R
import com.ubayadef.uts_hobby_160421034.databinding.FragmentHomeBinding
import com.ubayadef.uts_hobby_160421034.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var carViewModel : HomeViewModel
    private val carListAdapter = CarListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        carViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        carViewModel.refresh()



        binding.recView.visibility      = View.VISIBLE
        binding.recView.layoutManager   = LinearLayoutManager(context)
        binding.recView.adapter         = carListAdapter

        observeViewModel()




    }
    fun observeViewModel() {
        carViewModel.carLD.observe(viewLifecycleOwner, Observer {
            carListAdapter.updateCarList(it)
            Log.d("car list adapter", carListAdapter.toString())
        })
    }
}