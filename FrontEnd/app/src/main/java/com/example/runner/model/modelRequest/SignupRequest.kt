package com.example.runner.model.modelRequest

data class SignupRequest(
    var id :String?= null,
    var name: String?= null,
    var email: String?= null,
    var phone: String?= null,
    var password : String?= null,

    var __v:Int =0
)
