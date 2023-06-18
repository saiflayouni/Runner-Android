package com.example.runner.view


    import android.annotation.SuppressLint
    import android.content.Intent
    import android.os.Bundle
    import android.util.Patterns
    import android.view.View
    import android.widget.Button
    import android.widget.EditText
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.example.runner.R
    import com.example.runner.api.ApiInterface
    import com.example.runner.model.User
    import com.example.runner.model.modelResponse.SignupResponse
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import java.util.HashMap


class SignupActivity : AppCompatActivity() {

    private var retrofit: Retrofit? = null
    private var retrofitInterface: ApiInterface? = null
    private val BASE_URL = "http://10.0.2.2:3000"

        lateinit var etName: EditText
        //lateinit var etLastName:EditText
        lateinit var etEmail: EditText
        lateinit var etPassword:EditText
        lateinit var etPhone:EditText
        lateinit var btn:Button
        val MIN_PASSWORD_LENGTH = 2;

        @SuppressLint("SuspiciousIndentation")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_signup)


            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitInterface = retrofit!!.create(ApiInterface::class.java)

            viewInitializations()
            val btn =findViewById<Button>(R.id.btn)
            btn.setOnClickListener(View.OnClickListener {
              val  intent=Intent(this,MainActivity::class.java )
                startActivity(intent)

                val map = HashMap<String?, String?>()
                map["name"] = etName.text.toString()
                map["email"] = etEmail.text.toString()
                map["phone"] = etPhone.text.toString()
                map["password"] = etPassword.text.toString()


                val User = User("", etName.text.toString().trim(),etEmail.text.toString().trim(),etPhone.text.toString(),"user",etPassword.text.toString().trim())

                val call = retrofitInterface!!.executeSignup(map)
                call?.enqueue(object : Callback<SignupResponse> {
                    override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                this@SignupActivity,
                                "Signed up successfully", Toast.LENGTH_LONG
                            ).show()
                        } else if (response.code() == 400) {
                            Toast.makeText(
                                this@SignupActivity,
                                "Already registered", Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        Toast.makeText(
                            this@SignupActivity, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })




            })
        }

        fun viewInitializations() {
            etName = findViewById(R.id.etName)
            //etLastName = findViewById(R.id.et_last_name)
            etEmail = findViewById(R.id.etemail)
            etPassword = findViewById(R.id.etPassword)
            etPhone = findViewById(R.id.etPhone)

            // To show back button in actionbar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // Checking if the input in form is valid
        fun validateInput(): Boolean {
            if (etName.text.toString().equals("")) {
                etName.setError("Please Enter First Name")
                return false
            }
            //if (etFirstName.text.toString().equals("")) {
               // etFirstName.setError("Please Enter Last Name")
              //  return false
           // }
            if (etEmail.text.toString().equals("")) {
                etEmail.setError("Please Enter Email")
                return false
            }
            if (etPassword.text.toString().equals("")) {
                etPassword.setError("Please Enter Password")
                return false
            }
            if (etPhone.text.toString().equals("")) {
                etPhone.setError("Please Enter phone number")
                return false
            }

            // checking the proper email format
            if (!isEmailValid(etEmail.text.toString())) {
                etEmail.setError("Please Enter Valid Email")
                return false
            }

            // checking minimum password Length
            if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
                etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters")
                return false
            }

            // Checking if repeat password is same
            /*if (!etPassword.text.toString().equals(etRepeatPassword.text.toString())) {
                etRepeatPassword.setError("Password does not match")
                return false
            }*/
            return true
        }




        fun isEmailValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        // Hook Click Event

        fun performSignUp (view: View) {
            if (validateInput()) {

                // Input is valid, here send data to your server

                val Name = etName.text.toString()
               // val lastName = etLastName.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val Phone = etPhone.text.toString()
                //val repeatPassword = etRepeatPassword.text.toString()

                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
                // Here you can call you API

            }
        }
}




