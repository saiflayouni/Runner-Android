package com.example.runner.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.runner.DuelsFragment
import com.example.runner.R
import com.example.runner.TimelineFragment
import com.example.runner.statsFragment

class Fragment_Profile : AppCompatActivity(){
        private lateinit var fragmentContainerView2: FrameLayout
        private lateinit var timeline: Button
        private lateinit var stat: Button
        private lateinit var duels: Button


        private lateinit var fg1: TimelineFragment
        private lateinit var fg2: statsFragment
        private lateinit var fg3: DuelsFragment

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_profile)



            fragmentContainerView2 = findViewById(R.id.fragmentContainerView2)
            timeline = findViewById(R.id.time_line)
            stat = findViewById(R.id.stat )
            duels = findViewById(R.id.duels)





            fg1 = TimelineFragment()
            fg2 = statsFragment()
            fg3 = DuelsFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView2, fg1)
                .commit()

            timeline.setOnClickListener {
                navigate(fg1)
            }

            stat.setOnClickListener {
                navigate(fg2)
            }
            duels.setOnClickListener{
                navigate(fg3)
            }
        }

        private fun navigate(fragment: Fragment) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView2, fragment)
                .commit()
        }

}