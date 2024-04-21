package com.ubayadef.uts_hobby_160421034.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubayadef.uts_hobby_160421034.databinding.CarListBinding
import com.ubayadef.uts_hobby_160421034.model.Car

class CarListAdapter(val carList:ArrayList<Car>) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>(){
    companion object {
        var title: String = ""
        var creator: String = ""
        var image: String = ""
        var news: ArrayList<String> = ArrayList()
    }
    class CarViewHolder(var binding: CarListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        var binding = CarListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception -> exception.printStackTrace() }
        Picasso.get().load(carList[position].images).into(holder.binding.imgPoster)

        holder.binding.txtTittle.text        = carList[position].title
        holder.binding.txtCreate.text      = "@" + carList[position].creator
        holder.binding.txtDescription.text  = carList[position].descriptions[0]

        holder.binding.btnReadMore.setOnClickListener { view ->
            title       = carList[position].title
            creator     = carList[position].creator
            news        = carList[position].descriptions
            image       = carList[position].images

            val action = HomeFragmentDirections.actionHomeNews(carList[position].id)
            Navigation.findNavController(view).navigate(action)
        }
    }

    fun updateCarList(newCarList: ArrayList<Car>){
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
        Log.d("CarListAdapter", "Car list updated: $carList")
    }
}