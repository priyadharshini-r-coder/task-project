package com.example.taskproject.offlineStorage.carlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskproject.R
import com.example.taskproject.databinding.ActivityCarBinding
import com.example.taskproject.offlineStorage.util.Resource

class CarActivity : AppCompatActivity() {
    private val viewModel: CarListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val carAdapter=CarAdapter()
        binding.apply {
            recyclerViewer.apply {
                adapter=carAdapter
                layoutManager=LinearLayoutManager(this@CarActivity)
            }
            viewModel.cars.observe(this@CarActivity)
            {
                result->
                carAdapter.submitList(result.data)
                progressBar.isVisible=result is Resource.Loading<*> && result.data.isNullOrEmpty()
                textViewError.isVisible=result is Resource.Error<*>  && result.data.isNullOrEmpty()
                textViewError.text=result.error?.localizedMessage

            }
        }
    }
}