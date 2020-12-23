package com.abdullah.moviereviewapp.feature.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.presentation.BaseActivity
import com.abdullah.moviereviewapp.base.util.LoadingProgressDialog
import com.abdullah.moviereviewapp.databinding.ActivityMainBinding
import com.abdullah.moviereviewapp.feature.navigation.RouteNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val progressDialog: LoadingProgressDialog by lazy {
        LoadingProgressDialog(this, lifecycle)
    }

    override fun provideLayoutResId() = R.layout.activity_main

    override fun provideViewModel() =
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


    override fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun bindViewModel(dataBinding: ActivityMainBinding) {
        // NOTHING
    }
}