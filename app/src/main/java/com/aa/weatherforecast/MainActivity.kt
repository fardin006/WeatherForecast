package com.aa.weatherforecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aa.weatherforecast.api.DailyForecastApi
import com.aa.weatherforecast.databinding.ActivityMainBinding
import com.aa.weatherforecast.network.NetworkService
import com.aa.weatherforecast.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel : MainActivityViewModel

    val networkService = NetworkService()
    val retrofit = networkService.getRetrofitInstance()
    lateinit var dailyForecastApi: DailyForecastApi

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        dailyForecastApi = retrofit.create(DailyForecastApi::class.java)

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mViewModel.showProgres.observe(this,{ showProgress ->
            if(showProgress){

            }else {

            }
        })

        mViewModel.showDailyForecast.observe(this,{ forcast  ->
            // Show data on to the UI
            forcast?.let {
                binding.tempTV.text = forcast.toString()
            }
        })

        mViewModel.getForcastDetailsWithLatAndLng("40","33")

    }

    override fun onResume() {
        super.onResume()


    }
}
