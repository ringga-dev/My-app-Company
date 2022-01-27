package com.ringga.myetowa.data.utils

import android.content.Context
import android.content.res.Resources
import android.view.View
import com.ringga.myetowa.R
import com.ringga.myetowa.model.BaseRespon


fun View.ValidationUsername(email: String): BaseRespon {

    if (email.isEmpty()) {
        return BaseRespon(stts = false, resources.getString(R.string.username_validation_false))
    }

    return BaseRespon(true, "")
}

fun View.ValidationUsernameEmail(email: String): BaseRespon {

    if (email.isEmpty()) {
        return BaseRespon(stts = false, resources.getString(R.string.username_email_validation_false))
    }

    return BaseRespon(true, "")
}


fun View.ValidationEmail(email: String): BaseRespon {

    if (email.isEmpty()) {
        return BaseRespon(stts = false, resources.getString(R.string.email_validation_false))
    }
    if (!isVAlidEmail(email)) {
        return BaseRespon(false, resources.getString(R.string.email_validation_false2))
    }

    return BaseRespon(true, "")
}

fun View.ValidationPassword(password: String): BaseRespon {
    if (password.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.password_validation_false))
    }
    if (password.length < 6) {
        return BaseRespon(false, resources.getString(R.string.password_validation_false2))
    }
    return BaseRespon(true, "")
}

fun isVAlidEmail(email: String) =
    android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun View.ValidationBadge(badge:String):BaseRespon{
    if (badge.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.badge_validation_false))
    }
    return BaseRespon(true, "")
}

fun View.ValidationFullname(fullname:String):BaseRespon{
    if (fullname.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.fullname_validation_false))
    }
    return BaseRespon(true, "")
}

fun View.ValidationDevisi(devisi:String):BaseRespon{
    if (devisi.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.devisi_validation_false))
    }
    return BaseRespon(true, "")
}

fun View.ValidationPangkat(pangkat:String):BaseRespon{
    if (pangkat.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.pangkat_validation_false))
    }
    return BaseRespon(true, "")
}

fun View.ValidationAlamat(alamat:String):BaseRespon{
    if (alamat.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.alamat_validation_false))
    }
    return BaseRespon(true, "")
}

fun View.ValidationStts(stts:String):BaseRespon{
    if (stts.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.stts_validation_false))
    }
    return BaseRespon(true, "")
}

fun View.ValidationPhone(phone:String):BaseRespon{
    if (phone.isEmpty()) {
        return BaseRespon(false, resources.getString(R.string.phone_validation_false))
    }
    return BaseRespon(true, "")
}

fun View.ValidationText(text:String):BaseRespon{
    if(text.isEmpty()){
        return BaseRespon(false, resources.getString(R.string.text_validation_false))
    }
    return BaseRespon(true, "")
}




