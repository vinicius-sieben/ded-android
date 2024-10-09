package com.example.projeto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class CreateCharacterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_char_activity)

        // Navega para a próxima página (distribuição de atributos)
        val distributeAttribute = findViewById<Button>(R.id.distributeAttribute)
        distributeAttribute.setOnClickListener {
            val intent = Intent(this, AttributeDistributionActivity::class.java)
            startActivity(intent)
        }
    }
}
