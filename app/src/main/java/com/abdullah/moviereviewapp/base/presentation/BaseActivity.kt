package com.abdullah.moviereviewapp.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    private lateinit var dataBinding: DB
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()

        dataBinding = DataBindingUtil.setContentView(this, provideLayoutResId())
        bindViewModel(dataBinding)

        observeLiveData()
    }

    open fun observeLiveData() {
        viewModel.dialogBoxLiveData.let { dialogBoxLiveData ->
            dialogBoxLiveData.hasObservers().takeIf { it }.let {
                dialogBoxLiveData.observe(this, this::displayDialog)
            }
        }

        viewModel.loadingLiveData.let { loadingLiveData ->
            loadingLiveData.hasObservers().takeIf { it }.let {
                loadingLiveData.observe(this, this::determineLoadingVisibility)
            }
        }
    }

    fun getViewModel() = viewModel

    fun getBinding() = dataBinding

    abstract fun provideLayoutResId(): Int

    abstract fun provideViewModel(): VM

    abstract fun bindViewModel(dataBinding: DB)

    abstract fun hideLoading()

    abstract fun showLoading()

    fun determineLoadingVisibility(visibility: Boolean) =
        if (visibility) showLoading() else hideLoading()

    private fun displayDialog(dialogBox: DialogBoxModel) {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
            .setTitle(dialogBox.title)
            .setMessage(dialogBox.message)
            .setPositiveButton(dialogBox.dialogButton?.text) { dialogInterface, i -> dialogInterface.dismiss() }
            .setNegativeButton(dialogBox.secondDialogButton?.text) { dialogInterface, i -> dialogInterface.dismiss() }
            .show()
    }
}