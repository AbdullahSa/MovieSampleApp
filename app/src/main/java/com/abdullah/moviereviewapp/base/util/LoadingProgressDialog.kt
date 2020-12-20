package com.abdullah.moviereviewapp.base.util

import android.content.Context
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.abdullah.moviereviewapp.R

class LoadingProgressDialog(
    context: Context,
    private val lifecycleOwner: Lifecycle
) : LifecycleObserver {

    private val progressDialogLayoutResId = R.layout.dialog_progress

    private var progressDialog: AppCompatDialog? = null

    private var willShowAfterResume = false

    init {
        progressDialog = AppCompatDialog(context, R.style.ProgressDialogStyle).apply {
            setContentView(progressDialogLayoutResId)
            setCancelable(false)
        }
        lifecycleOwner.addObserver(this)
    }

    fun show() {
        if (lifecycleOwner.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            progressDialog?.takeUnless { it.isShowing }?.show()
        } else {
            willShowAfterResume = true
        }
    }

    fun hide() {
        progressDialog?.hide()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (willShowAfterResume) {
            progressDialog?.show()
        }
        willShowAfterResume = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        willShowAfterResume = progressDialog?.isShowing == true
        if (willShowAfterResume) {
            hide()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dismiss() {
        willShowAfterResume = false
        progressDialog?.dismiss()
    }
}