package com.rmnivnv.healthyfood.product

import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rmnivnv.api.data.Event
import com.rmnivnv.api.data.Food
import com.rmnivnv.design.utils.hide
import com.rmnivnv.design.utils.show
import com.rmnivnv.healthyfood.R
import com.squareup.seismic.ShakeDetector
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product.*

@AndroidEntryPoint
class ProductFragment : Fragment(), ShakeDetector.Listener {

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var shakeDetector: ShakeDetector
    private lateinit var vibrator: Vibrator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupShakeDetector()
        setupViews()

        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Event.Status.SUCCESS -> it.data?.let { data -> showSuccess(data) } ?: viewModel.update()
                Event.Status.ERROR -> it.message?.let { message -> showError(message) }
                Event.Status.LOADING -> {
                    circle.startSpin()
                }
            }
        })
    }

    override fun hearShake() {
        name.hide()
        calories.hide()
        description.hide()

        protein.description = null
        carbs.description = null
        fat.description = null

        viewModel.update()
    }

    private fun setupShakeDetector() {
        shakeDetector = ShakeDetector(this)
        shakeDetector.start(requireNotNull(activity).getSystemService(SENSOR_SERVICE) as SensorManager)
    }

    private fun setupViews() {
        protein.title = getString(R.string.info_title_protein)
        carbs.title = getString(R.string.info_title_cards)
        fat.title = getString(R.string.info_title_fat)
        button.title = getString(R.string.product_action_button_text)
    }

    private fun showSuccess(data: Food) {
        circle.stopSpin()
        circle.reDraw()

        name.text = data.title
        name.show()
        calories.text = data.calories?.toInt()?.toString()
        calories.show()
        description.show()

        data.protein?.toInt()?.toString()?.let { value ->
            protein.description = "$value%"
            protein.show()
        } ?: protein.hide()

        data.carbs?.toInt()?.toString()?.let { value ->
            carbs.description = "$value%"
            carbs.show()
        } ?: carbs.hide()

        data.fat?.toInt()?.toString()?.let { value ->
            fat.description = "$value%"
            fat.show()
        } ?: fat.hide()
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}