package com.ringga.myetowa.data.utils

/*=================== T H A N K   Y O U ===================*/
/*============= TELAH MENGUNAKAN CODE SAYA ================*/
            /* https://github.com/ringga-dev */
/*=========================================================*/
/*     R I N G G A   S E P T I A  P R I B A D I            */
/*=========================================================*/
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import android.view.Window
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern


fun toast(context:Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun snackbar(text:String, view: View){
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .setAction("Action", null).show()
}

fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}

fun chage_color_stts_bar(window: Window, string: String){
    window.statusBarColor = Color.parseColor(string)
}

fun cek_email(myEmail: String): Boolean {
    val emailRegex = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return emailRegex.matcher(myEmail).matches()
}

//https://github.com/ydhnwb/golang_heroku




