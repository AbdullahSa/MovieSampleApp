package com.abdullah.moviereviewapp.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.data.model.DialogBoxModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    private lateinit var viewModel: VM
    private lateinit var dataBinding: DB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = provideViewModel()
        bindViewModel(dataBinding)
        observeLiveData()
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })
    }

    open fun observeLiveData() {
        viewModel.dialogBoxLiveData.let { dialogBoxLiveData ->
            dialogBoxLiveData.hasObservers().takeIf { it }.let {
                dialogBoxLiveData.observe(viewLifecycleOwner, this::displayDialog)
            }
        }

        viewModel.loadingLiveData.let { loadingLiveData ->
            loadingLiveData.hasObservers().takeIf { it }.let {
                loadingLiveData.observe(viewLifecycleOwner) {
                    (activity as BaseActivity<*, *>).determineLoadingVisibility(it)
                }
            }
        }

        observeNavigationEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, provideLayoutResId(), container, false)
        return dataBinding.root
    }

    fun getViewModel() = viewModel

    fun getBinding() = dataBinding

    abstract fun provideLayoutResId(): Int

    abstract fun provideViewModel(): VM

    abstract fun bindViewModel(dataBinding: DB)

    private fun displayDialog(dialogBox: DialogBoxModel) {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle(dialogBox.title)
            .setMessage(dialogBox.message)
            .setPositiveButton(dialogBox.dialogButton?.text) { dialogInterface, _ -> dialogInterface.dismiss() }
            .setNegativeButton(dialogBox.secondDialogButton?.text) { dialogInterface, _ -> dialogInterface.dismiss() }
            .show()
    }

    private fun observeNavigationEvent() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner, Observer { navEvent ->
            val consume = navEvent?.getContentIfNotHandled()
            consume?.invoke(findNavController())
        })
    }

    protected fun onBackPressed() {
        viewModel.onBackPressed()
        onReturnToPreviousScreen()
    }

    protected open fun onReturnToPreviousScreen() {
        findNavController().navigateUp()
    }

}