package com.appinsnap.lwmc.utils

import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.io.ByteArrayOutputStream
import java.io.IOException

object UtilFun {
    fun isNetworkAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

    fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // CREATE A MATRIX FOR THE MANIPULATION
        val matrix = Matrix()
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight)

        // "RECREATE" THE NEW BITMAP
        val resizedBitmap = Bitmap.createBitmap(
            bm, 0, 0, width, height, matrix, false
        )
        bm.recycle()
        return resizedBitmap
    }

    fun convertImgToBas64(str: Bitmap): String {
        var imgString = ""
        try {
            val bao = ByteArrayOutputStream()
            str.compress(Bitmap.CompressFormat.JPEG, 80, bao)
            val byte = bao.toByteArray()
            // Log.e("ByteArray", ":" + ba);

            imgString = Base64.encodeToString(byte, Base64.DEFAULT)
            //  Log.i("base", img_str_nak2);

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return imgString
    }

    fun uriToBitmap(context: Context, imageUri: Uri): Bitmap {
        var image: Bitmap? = null
        try {
            val parcelFileDescriptor =
                context.contentResolver.openFileDescriptor(imageUri, "r")
            val fileDescriptor = parcelFileDescriptor!!.fileDescriptor
            image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image!!
    }

    fun convertStringToBitmap(base64Str: String): Bitmap {
        val decodedBytes = Base64.decode(
            base64Str.substring(base64Str.indexOf(",") + 1),
            Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

}