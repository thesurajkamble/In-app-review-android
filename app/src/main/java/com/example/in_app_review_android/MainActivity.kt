package com.example.in_app_review_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.play.core.review.ReviewManagerFactory
import java.time.Duration

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = ReviewManagerFactory.create(this)

        val request = manager.requestReviewFlow()
        var btn: Button = findViewById(R.id.btn_rate)

        btn.setOnClickListener {
            request.addOnCompleteListener { request ->
                if (request.isSuccessful) {
                    val reviewInfo = request.result
                    val flow = manager.launchReviewFlow(this, reviewInfo!!)
                    flow.addOnCompleteListener { _ ->
                       Toast.makeText(applicationContext,"thanks for rating!!!!!!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext,"ohhh something fucked up",Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
}