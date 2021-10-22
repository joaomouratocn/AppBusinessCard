package br.com.programadorjm.businesscard.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.programadorjm.businesscard.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ImageCard {
    companion object{
        fun shareCardImage(context: Context, view:View){
            val bitmap = getScreenShotFromView(view)
            bitmap?.let{
                saveMediaToStorage(context, bitmap)
            }
        }

        private fun saveMediaToStorage(context: Context, bitmap: Bitmap) {
            val fileName = "${System.currentTimeMillis()}.jpg"
            var outputStream: OutputStream?

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                context.contentResolver.also {contentResolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }
                    val imageUri:Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                    outputStream = imageUri?.let {
                        shareIntent(context, imageUri)
                        contentResolver.openOutputStream(it)
                    }
                }
            }else{
                val imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imageDir, fileName)
                shareIntent(context, Uri.fromFile(image))
                outputStream = FileOutputStream(image)
            }

            outputStream?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(context, "successfully captured image", Toast.LENGTH_SHORT).show()
            }
        }

        private fun shareIntent(context: Context, image: Uri) {
            val shareIntent:Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, image)
                type = "image/jpeg"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.resources.getText(R.string.str_share)
                )
            )
        }

        private fun getScreenShotFromView(view: View): Bitmap? {
            var screenShot:Bitmap? = null
            try {
                screenShot = Bitmap.createBitmap(
                    view.measuredWidth,
                    view.measuredHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(screenShot)
                view.draw(canvas)
            }catch (e:Exception){
                Log.e("GFG", "Failed to capture screenshot because:" + e.message)
            }
            return screenShot
        }
    }
}