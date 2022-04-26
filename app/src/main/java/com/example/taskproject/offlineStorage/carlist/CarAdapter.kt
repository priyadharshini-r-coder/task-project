package com.example.taskproject.offlineStorage.carlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.databinding.CarlistItemBinding
import com.example.taskproject.offlineStorage.data.CarList

class CarAdapter:ListAdapter<CarList,CarAdapter.CarViewHolder>(CarListComparator()){

    class CarViewHolder(private val binding:CarlistItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(carList: CarList)
        {
            binding.apply {
                carName.text=carList.make_and_model
                carTransmission.text=carList.transmission
                carColor.text=carList.color
                carDriveType.text=carList.drive_type
                carFuelType.text=carList.fuel_type
                carCarType.text=carList.car_type

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding=CarlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val currentItem=getItem(position)
        if(currentItem !=null)
        {
            holder.bind(currentItem)
        }
    }
    class CarListComparator: DiffUtil.ItemCallback<CarList>()
    {
        override fun areItemsTheSame(oldItem: CarList, newItem: CarList)
        =
            oldItem.make_and_model == newItem.make_and_model


        override fun areContentsTheSame(oldItem: CarList, newItem: CarList) =
           oldItem == newItem


    }
}