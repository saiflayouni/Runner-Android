package com.example.runner.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil.setContentView

import com.example.runner.R

class EditprofileActivity : AppCompatActivity() {
 private lateinit var button: Button
 private lateinit var imageView: ImageView
 companion object {
    val IMAGE_REQUEST_CODE = 100;
 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
            button = findViewById(R.id.btn_pick_img)
            imageView = findViewById(R.id.img_view)
        button.setOnClickListener {
                pickImageGallery()
            }


    }
    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageView.setImageURI(data?.data)
        }
    }

}