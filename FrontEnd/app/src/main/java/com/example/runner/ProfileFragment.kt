package com.example.runner

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import com.example.runner.view.EditprofileActivity
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match

class ProfileFragment : Fragment(R.layout.fragment_profile), SensorEventListener {
    var running = false
    var sensorManager: SensorManager? = null

private lateinit var updateProfileButton: ImageButton
    private lateinit var fragmentContainerView2: FrameLayout
    private lateinit var timeline: Button
    private lateinit var stat: Button
    private lateinit var duels: Button


    private lateinit var fg1: TimelineFragment
    private lateinit var fg2: statsFragment
    private lateinit var fg3: DuelsFragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProfileButton = view?.findViewById(R.id.btnsettings)!!
        updateProfileButton.setOnClickListener {
            val activity: FragmentActivity? = activity


            startActivity(Intent(activity, EditprofileActivity::class.java))


        }
        fragmentContainerView2 = view?.findViewById(R.id.fragmentContainerView2)!!
        timeline = view?. findViewById(R.id.time_line)!!
        stat = view?.findViewById(R.id.stat )!!
        duels = view?.findViewById(R.id.duels)!!





        fg1 = TimelineFragment()
        fg2 = statsFragment()
        fg3 = DuelsFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView2, fg1)
        transaction.commit()
        timeline.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView2, fg1)
            transaction.commit()
        }
        stat.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView2, fg2)
            transaction.commit()
        }
        duels.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView2, fg3)
            transaction.commit()
        }



}

    override fun onResume() {
        super.onResume()
        running=true
        val stepSensor: Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepSensor == null) {
            Toast.makeText(activity, "active Step Counter Sensor!", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }
    override fun onPause() {
        super.onPause()
        running=false
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            stepsvalue.setText("" + event?.values!![0] )
        }
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}
