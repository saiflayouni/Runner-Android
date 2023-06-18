package com.example.runner.view


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.runner.BASE_URL
import com.example.runner.R
import com.example.runner.api.ApiInterface
import com.example.runner.model.modelRequest.LoginRequest
import com.example.runner.model.modelResponse.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

class LoginActivity : AppCompatActivity() {
    private var retrofit: Retrofit? = null
    private var retrofitInterface: ApiInterface? = null

        lateinit var etEmail: EditText
        lateinit var  etPassword: EditText
        val MIN_PASSWORD_LENGTH = 2
        lateinit var loginbtn  : Button



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.loginactivity)
            etEmail = findViewById(R.id.etusername)
            etPassword = findViewById(R.id.etPassword)
            loginbtn = findViewById(R.id.btn)

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitInterface = retrofit!!.create(ApiInterface::class.java)

            val ss = SpannableString("Don't have an account? Sign up ")
            val clickableSpan: ClickableSpan = object : ClickableSpan() {

                override fun onClick(textView: View) {

                    startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
                }


            }
            ss.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val boldSpan = StyleSpan(Typeface.BOLD)
            ss.setSpan(boldSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


            val textView = findViewById<TextView>(R.id.signup)
            textView.text = ss
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.highlightColor = Color.TRANSPARENT

            // To show back button in actionbar

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            loginbtn.setOnClickListener {

                if(etEmail.text.isEmpty()){
                    etEmail.error = "Email required"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }
                if(etPassword.text.isEmpty()){
                    etPassword.error = "Password required"
                    etPassword.requestFocus()
                    return@setOnClickListener
                }
                val map = HashMap<String?, String?>()
                map["username"] = etEmail.text.toString()
                map["password"] = etPassword.text.toString()
                val loginRequest = LoginRequest(Email = etEmail.text.toString(), Password = etPassword.text.toString())


                val call = retrofitInterface!!.executeLogin(map)
                call?.enqueue(object  : Callback<LoginResponse?> {
                    override fun onResponse(
                        call: Call<LoginResponse?>,
                        response: Response<LoginResponse?>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                this@LoginActivity,
                                "login successfully", Toast.LENGTH_LONG
                            ).show()
                            val i = Intent(this@LoginActivity,MainActivity::class.java)
                            startActivity(i)
                        //if (response.code() == 200) {
                           // val result = response.body()
                            //Toast.makeText(this@LoginActivity,"connected",Toast.LENGTH_SHORT)
                            //val builder1 = AlertDialog.Builder(this@LoginActivity)
                           // builder1.setTitle(result!!.messgae)
                          //  builder1.setMessage(result.messgae)
                           // builder1.show()

                        } else if (response.code() == 404) {
                            Toast.makeText(
                                this@LoginActivity, "Wrong Credentials",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                        Toast.makeText(
                            this@LoginActivity, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })

            /*
            loginbtn.setOnClickListener {
                apiInterface.seConnecter(userinfo = loginRequest).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val response = response.body()!!
                        Log.d("TaG",response.token)
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }


                })
            }

             */
        }



        // Checking if the input in form is valid
        fun validateInput(): Boolean {
            if (etEmail.text.toString() == "") {
                etEmail.error = "Please Enter Email"
                return false
            }
            if (etPassword.text.toString() == "") {
                etPassword.error = "Please Enter Password"
                return false
            }

            // checking the proper email format
            //if (!isEmailValid(etEmail.text.toString())) {
                //etEmail.error = "Please Enter Valid Email"
               // return false
           // }

            // checking minimum password Length
            if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
                etPassword.error = "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
                return false
            }
            return true
        }

        fun isEmailValid(email: String?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        // Hook Click Event
        fun performSignUp(v: View) {
            if (validateInput()) {

                // Input is valid, here send data to your server
                val email = etEmail!!.text.toString()
                val password = etPassword!!.text.toString()
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                // Here you can call you API
                // Check this tutorial to call server api through Google Volley Library https://handyopinion.com
            }
        }

        fun goToSignup(v: View) {
            // Open your SignUp Activity if the user wants to signup
            // Visit this article to get SignupActivity code https://handyopinion.com/signup-activity-in-android-studio-kotlin-java/
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }



}

private fun <T> Call<T>?.enqueue(callback: Callback<LoginResponse?>) {}

}
