package com.example.runner.model.modelRequest

import android.provider.ContactsContract.CommonDataKinds.Email

data class LoginRequest(
    val Email : String? =null,
    val Password : String

)
