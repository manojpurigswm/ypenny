package com.fexle.upennys

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.fexle.upennys.data.AppData
import com.fexle.upennys.ui.splash.SplashActivity
import com.fexle.upennys.utils.setupActionBar
import com.fexle.upennys.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.util.*

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(){
    private lateinit var mProgressDialog: Dialog
    val isInternetConnected: Boolean get() = mViewModel.isInternetConnected()

    lateinit var mBinding: T
    lateinit var mViewModel: V

    @get:LayoutRes
    abstract val layoutId: Int
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.setVariable(BR.viewModel, mViewModel)

        mViewModel.appData = AppData.getInstance(this)

        mProgressDialog = Dialog(this)
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mProgressDialog.setContentView(R.layout.layout_progress_dialog)
        mProgressDialog.setCancelable(false)
        mProgressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mViewModel.showToast.observe(this, {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.showSnackbar.observe(this, {
            it?.let {
                showSnackBarMsg(mBinding.root, it)
            }
        })

        mViewModel.showProgress.observe(this, {
            it?.let {
                showProgress(it)
            }
        })


        mViewModel.sessionExpired.observe(this, {
            it?.let {
                showSessionExpired()
            }
        })

        resetTitle()
        mViewModel.create()
    }

    override fun onStart() {
        mViewModel.start()
        super.onStart()
    }

    override fun onStop() {
        mViewModel.stop()
        super.onStop()
    }

    override fun onDestroy() {
        mViewModel.destroy()
        super.onDestroy()
    }


    open fun setupToolbar(title: String) {
        setupActionBar(R.id.toolbar){
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
            setDisplayHomeAsUpEnabled(true)
            setTitle(title)
        }
    }

    fun resetTitle() {
        try {
            var label = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA).labelRes;
            if (label != 0) {
                setTitle(label);
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun showSessionExpired() {
        mViewModel.appData.logoutUser()
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun showSnackBarMsg(view: View, msg: String) {
        view.showSnackbar(msg, Snackbar.LENGTH_SHORT)
    }

    fun showProgress(state: Boolean) {
        if (state) {
            if(!mProgressDialog.isShowing)
                mProgressDialog.show()
        } else {
            if(mProgressDialog.isShowing)
                mProgressDialog.dismiss()
        }
    }

    open fun checkAndRequestPermissions(): Boolean {
        val permissionWriteStorage = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val permissionReadStorage = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )

        val permissionLocation = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val permissionCoarseLocation = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val permissionCamera = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )

        val permissionReadContacts = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        )

        val permissionPhoneCall = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
        )

        val listPermissionsNeeded = arrayListOf<String>()

        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (permissionReadStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (permissionCoarseLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }

        if (permissionReadContacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS)
        }

        if (permissionPhoneCall != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE)
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                1
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                }
                else{
                    //baseViewModel.snackBarMessage.value = "App Permissions Are Not Granted"
                }
            }

            else -> {

            }
        }
    }

    fun getPath(uri: Uri?): String? {
        if (uri == null) {
            return null
        }
        // this will only work for images selected from gallery
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver?.query(uri, projection, null, null, null)
        if (cursor != null) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        }
        return uri.path
    }
}