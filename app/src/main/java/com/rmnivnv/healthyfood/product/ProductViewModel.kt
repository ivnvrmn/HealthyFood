package com.rmnivnv.healthyfood.product

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmnivnv.api.data.Event
import com.rmnivnv.api.repository.FoodRepository
import com.rmnivnv.api.data.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductViewModel @ViewModelInject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    val foodLiveData = MutableLiveData<Event<Food>>()

    init {
        loadFood()
    }

    fun update() {
        loadFood()
    }

    private fun loadFood() {
        foodLiveData.postValue(Event.loading())
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            val response = foodRepository.getFood()
            foodLiveData.postValue(response)
        }
    }
}