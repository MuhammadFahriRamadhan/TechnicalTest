package com.example.technicaltest.core.base

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.example.technicaltest.R
import com.example.technicaltest.core.exception.Failure
import com.example.technicaltest.core.helper.util.CustomSnackbarView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var mToolbar: Toolbar? = null
    protected var disposable = CompositeDisposable()

    var viewBinding: VB? = null

    private lateinit var progress: Dialog
    private lateinit var progressReconnecting : Dialog
    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getUiBinding()
        setContentView(viewBinding?.root)
        rootView = window.decorView.findViewById(android.R.id.content)
        setupToolbar()
        onFirstLaunch(savedInstanceState)
        initProgressDialog()
        initUiListener()
    }

    abstract fun enableBackButton(): Boolean
    abstract fun bindToolbar(): Toolbar?
    abstract fun getUiBinding(): VB
    abstract fun onFirstLaunch(savedInstanceState: Bundle?)
    abstract fun initUiListener()

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
        viewBinding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    private fun setupToolbar() {
        bindToolbar()?.let {
            mToolbar = it
            setSupportActionBar(mToolbar)
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(enableBackButton())
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun initProgressDialog() {
        if (!::progress.isInitialized) {
            progress = Dialog(this)
            val inflate = LayoutInflater.from(this).inflate(R.layout.progress_default, null)
            progress.setContentView(inflate)
            progress.setCancelable(false)
            progress.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progress.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun showProgress() {
        if (!progress.isShowing) {
            progress.show()
        }
    }


    fun hideProgressReconecting(){
        if (progressReconnecting.isShowing) progressReconnecting.dismiss()
    }

    fun hideProgress() {
        if (progress.isShowing) progress.dismiss()
    }

    fun showInfoSnackbar(text: String, onDismissListener: (() -> Unit) = { }) {
        CustomSnackbarView.make(
            rootView,
            text,
            CustomSnackbarView.SnackbarType.INFO,
            true,
            onDismissListener
        ).show()
    }

    fun showSuccessSnackbar(text: String, onDismissListener: (() -> Unit) = { }) {
        CustomSnackbarView.make(
            rootView,
            text,
            CustomSnackbarView.SnackbarType.SUCCESS,
            true,
            onDismissListener
        ).show()
    }

    fun showErrorSnackbar(text: String, onDismissListener: (() -> Unit) = { }) {
        CustomSnackbarView.make(
            rootView,
            text,
            CustomSnackbarView.SnackbarType.ERROR,
            true,
            onDismissListener
        ).show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showErrorSnackbar(getString(R.string.error_disconnect))
            is Failure.ServerError -> {
                showErrorSnackbar(failure.message)
            }
            else -> {
                showErrorSnackbar(getString(R.string.error_default))

            }
        }

    }
}