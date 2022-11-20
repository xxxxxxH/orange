package com.beta.orange.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beta.orange.R
import com.beta.orange.base.BaseActivity
import com.beta.orange.databinding.ActivityMainBinding
import com.beta.orange.event.RequestEvent
import com.beta.orange.utils.print
import com.beta.orange.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initialization() {
//        viewModel.request()
//        viewModel.liveData.observe(this){
//            "result = $it".print()
//        }
        viewModel.request2().observe(this){
            when(it){
               is RequestEvent.StartRequestEvent->{
                    "on start".print()
                }
                is RequestEvent.SuccessRequestEvent->{
                    "result = ${it.data}".print()
                }
                is RequestEvent.FailRequestEvent->{
                    "on fail = ${it.exception?.message}".print()
                }
            }
        }
    }
}