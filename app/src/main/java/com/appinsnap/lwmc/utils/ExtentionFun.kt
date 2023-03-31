package com.appinsnap.lwmc.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


fun Fragment.navigateFragment(id: Int) {
    try {
        Navigation.findNavController(this.requireView()).navigate(id)
    } catch (e: Exception) {

    }
}

fun Fragment.navigateFragment(id: Int, bundle: Bundle) {
    try {
        Navigation.findNavController(this.requireView()).navigate(id, bundle)
    } catch (e: Exception) {

    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE

}
fun Context.checkPermissions (permissions: Array<String>): Boolean = permissions.all {
    ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.isNetworkAvailable(): Boolean {
    var result = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
    }

    return result
}
 fun Context.requestPermissions(permissions: Collection<String>,listener:PermissionListener) {
    Dexter.withContext(this)
        .withPermissions(
          permissions
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report?.let {
                    if (report.areAllPermissionsGranted()) {
                        listener.areAllPermissionsGranted()
                    }
                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        listener.isAnyPermissionPermanentlyDenied()
                      //  this.showPermissionDeniedDialog(Const.PERMISSION_MSG ,true)
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?,
            ) {
                token?.continuePermissionRequest()
            }
        })
        .check()
}


interface PermissionListener{
    fun areAllPermissionsGranted()
    fun isAnyPermissionPermanentlyDenied()
}
