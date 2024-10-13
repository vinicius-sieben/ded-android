package com.example.projeto

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import com.example.projeto.singleton.CharacterManager
import com.viniciussieben.ded.model.Character
import com.viniciussieben.ded.model.Race
import com.viniciussieben.ded.model.CharClass
import com.viniciussieben.ded.strategy.CustomAttributeDistribution

class CreateCharacterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_char_activity)

        // Referências aos elementos do layout
        val nameEditText = findViewById<EditText>(R.id.charName)
        val raceGroup = findViewById<RadioGroup>(R.id.raceGroup)
        val classGroup = findViewById<RadioGroup>(R.id.classGroup)
        val distributeAttributeButton = findViewById<Button>(R.id.createCharacterButton)

        // Configurando o clique do botão "Distribuição de Atributos"
        distributeAttributeButton.setOnClickListener {
            val name = nameEditText.text.toString()

            // Obter a raça selecionada
            val selectedRaceId = raceGroup.checkedRadioButtonId
            val race = when (selectedRaceId) {
                R.id.radioDwarf -> Race.DWARF
                R.id.radioElf -> Race.ELF
                R.id.radioHuman -> Race.HUMAN
                R.id.radioHalfling -> Race.HALFLING
                else -> Race.HUMAN // Raça padrão caso nenhuma seja selecionada
            }

            // Obter a classe selecionada
            val selectedClassId = classGroup.checkedRadioButtonId
            val charClass = when (selectedClassId) {
                R.id.radioWarrior -> CharClass.WARRIOR
                R.id.radioMage -> CharClass.MAGE
                R.id.radioRogue -> CharClass.ROGUE
                R.id.radioCleric -> CharClass.CLERIC
                else -> CharClass.WARRIOR // Classe padrão caso nenhuma seja selecionada
            }

            // Instanciar o personagem com a estratégia de distribuição de atributos
            val distributionStrategy = CustomAttributeDistribution()
            val character = Character(
                name = name,
                race = race,
                charClass = charClass,
                distributionStrategy = distributionStrategy
            )

            // Armazena o personagem no Singleton
            CharacterManager.character = character

            // Navega para a próxima página (distribuição de atributos)
            val intent = Intent(this, AttributeDistributionActivity::class.java)
            startActivity(intent)
        }
    }
}
