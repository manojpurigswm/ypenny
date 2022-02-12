package com.fexle.upennys

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.fexle.upennys.data.AppData
import com.fexle.upennys.ui.splash.SplashActivity
import com.fexle.upennys.utils.setupActionBar
import com.fexle.upennys.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment <T : ViewDataBinding, V : BaseViewModel> : Fragment(){
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

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.setVariable(BR.viewModel, mViewModel)

        mViewModel.appData = AppData.getInstance(requireContext())

        mProgressDialog = Dialog(requireContext())
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mProgressDialog.setContentView(R.layout.layout_progress_dialog)
        mProgressDialog.setCancelable(false)
        mProgressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mViewModel.showToast.observe(requireActivity(), {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.showSnackbar.observe(requireActivity(), {
            it?.let {
                showSnackBarMsg(mBinding.root, it)
            }
        })

        mViewModel.showProgress.observe(requireActivity(), {
            it?.let {
                showProgress(it)
            }
        })


        mViewModel.sessionExpired.observe(this, {
            it?.let {
                showSessionExpired()
            }
        })

        mViewModel.create()

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.setVariable(layoutId, mViewModel)
        mBinding.executePendingBindings()
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


    fun showSessionExpired() {
        mViewModel.appData.logoutUser()
        val intent = Intent(requireContext(), SplashActivity::class.java)
        startActivity(intent)
        requireActivity().finishAffinity()
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
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val permissionReadStorage = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        val permissionLocation = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val permissionCoarseLocation = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val permissionCamera = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        )

        val permissionReadContacts = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        )

        val permissionPhoneCall = ContextCompat.checkSelfPermission(
            requireContext(),
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
                requireActivity(),
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
        val cursor = requireContext().contentResolver?.query(uri, projection, null, null, null)
        if (cursor != null) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        }
        return uri.path
    }
}