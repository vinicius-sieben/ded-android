package com.example.projeto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.widget.TextView
import com.example.projeto.singleton.CharacterManager

class CharacterInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_info)

        // Recupera o personagem do Singleton
        val character = CharacterManager.character

        // Referências aos elementos de layout
        val characterNameTextView = findViewById<TextView>(R.id.characterNameTextView)
        val strengthTextView = findViewById<TextView>(R.id.strengthTextView)
        val dexterityTextView = findViewById<TextView>(R.id.dexterityTextView)
        val constitutionTextView = findViewById<TextView>(R.id.constitutionTextView)
        val intelligenceTextView = findViewById<TextView>(R.id.intelligenceTextView)
        val wisdomTextView = findViewById<TextView>(R.id.wisdomTextView)
        val charismaTextView = findViewById<TextView>(R.id.charismaTextView)
        val raceTextView = findViewById<TextView>(R.id.raceTextView)
        val classTextView = findViewById<TextView>(R.id.classTextView)
        val newCharacterButton = findViewById<Button>(R.id.createNewCharacterButton)

        // Verifica se o personagem está disponível
        if (character != null) {
            // Define os valores nos TextViews
            characterNameTextView.text = character.name
            raceTextView.text = "Raça: ${character.race.description}"
            classTextView.text = "Classe: ${character.charClass.description}"
            strengthTextView.text = "Força: ${character.attributes.strength.score}"
            dexterityTextView.text = "Destreza: ${character.attributes.dexterity.score}"
            constitutionTextView.text = "Constituição: ${character.attributes.constitution.score}"
            intelligenceTextView.text = "Inteligência: ${character.attributes.intelligence.score}"
            wisdomTextView.text = "Sabedoria: ${character.attributes.wisdom.score}"
            charismaTextView.text = "Carisma: ${character.attributes.charisma.score}"
        }

        // Configuração do botão para criar um novo personagem
        newCharacterButton.setOnClickListener {
            // Limpa o personagem atual
            CharacterManager.character = null

            // Navega para a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}