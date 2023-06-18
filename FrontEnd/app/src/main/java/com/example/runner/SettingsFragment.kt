package com.example.runner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.runner.view.AboutusActivity2
import com.example.runner.view.CloserfriendsActivity2
import com.example.runner.view.EditprofileActivity
import com.example.runner.view.PrivacyActivity2
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {
    private lateinit var close: TextView
    private lateinit var about: TextView
    private lateinit var privacy: TextView





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
     override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         close= view.findViewById(R.id.close_friends)!!
         close.setOnClickListener {
             val activity: FragmentActivity? = activity

             startActivity(Intent(activity,CloserfriendsActivity2::class.java))

         }
         about= view.findViewById(R.id.btn_about)!!
         about.setOnClickListener {
             val activity: FragmentActivity? = activity

             startActivity(Intent(activity, AboutusActivity2::class.java))

         }
         privacy= view.findViewById(R.id.btn_privacy)!!
            privacy.setOnClickListener {
                val activity: FragmentActivity? = activity

                startActivity(Intent(activity,PrivacyActivity2::class.java))

            }

     }


}