package com.example.projeto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class AttributeDistributionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_attribute_distribution)

        val finishCreationButton = findViewById<Button>(R.id.finishCreationButton)
        finishCreationButton.setOnClickListener {
            val intent = Intent(this, CharacterInfoActivity::class.java)
            startActivity(intent)
        }

    }
}
