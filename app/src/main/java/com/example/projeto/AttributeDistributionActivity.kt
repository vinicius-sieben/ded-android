package com.example.projeto

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import com.example.projeto.singleton.CharacterManager
import com.viniciussieben.ded.model.Attributes
import com.viniciussieben.ded.model.Attribute
import com.viniciussieben.ded.model.Character
import com.viniciussieben.ded.model.Race
import com.viniciussieben.ded.model.CharClass
import com.viniciussieben.ded.strategy.CustomAttributeDistribution

class AttributeDistributionActivity : ComponentActivity() {
    private var availablePoints = 27
    private lateinit var baseAttributes: Attributes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attribute_distribution)

        // Recuperar o personagem existente do Singleton ou criar um novo personagem padrão
        var character = CharacterManager.character
        if (character != null) {
            baseAttributes = character.attributes
        } else {
            // Cria um novo conjunto de atributos e personagem padrão, se necessário
            baseAttributes = Attributes(
                strength = Attribute(8),
                dexterity = Attribute(8),
                constitution = Attribute(8),
                intelligence = Attribute(8),
                wisdom = Attribute(8),
                charisma = Attribute(8)
            )
            character = Character(
                name = "Personagem Padrão",
                race = Race.HUMAN,
                charClass = CharClass.WARRIOR,
                distributionStrategy = CustomAttributeDistribution()
            )
            character.attributes = baseAttributes
            CharacterManager.character = character
        }

        // Referências aos elementos do layout
        val availablePointsTextView = findViewById<TextView>(R.id.availablePoints)
        availablePointsTextView.text = "Pontos disponíveis: $availablePoints"

        val finishCreationButton = findViewById<Button>(R.id.finishCreationButton)
        finishCreationButton.setOnClickListener {
            // Recuperar o personagem existente do Singleton e atualizar os atributos
            CharacterManager.character?.let { character ->
                character.attributes.strength.score = baseAttributes.strength.score
                character.attributes.dexterity.score = baseAttributes.dexterity.score
                character.attributes.constitution.score = baseAttributes.constitution.score
                character.attributes.intelligence.score = baseAttributes.intelligence.score
                character.attributes.wisdom.score = baseAttributes.wisdom.score
                character.attributes.charisma.score = baseAttributes.charisma.score
            }

            // Navegar para a próxima atividade
            val intent = Intent(this, CharacterInfoActivity::class.java)
            startActivity(intent)
        }

        // Configurando os botões para cada atributo
        setupAttributeButtons(
            R.id.minusStrength,
            R.id.strengthValue,
            R.id.plusStrength,
            baseAttributes.strength
        )
        setupAttributeButtons(
            R.id.minusDexterity,
            R.id.dexterityValue,
            R.id.plusDexterity,
            baseAttributes.dexterity
        )
        setupAttributeButtons(
            R.id.minusConstitution,
            R.id.constitutionValue,
            R.id.plusConstitution,
            baseAttributes.constitution
        )
        setupAttributeButtons(
            R.id.minusIntelligence,
            R.id.intelligenceValue,
            R.id.plusIntelligence,
            baseAttributes.intelligence
        )
        setupAttributeButtons(
            R.id.minusWisdom,
            R.id.wisdomValue,
            R.id.plusWisdom,
            baseAttributes.wisdom
        )
        setupAttributeButtons(
            R.id.minusCharisma,
            R.id.charismaValue,
            R.id.plusCharisma,
            baseAttributes.charisma
        )
    }

    // Função para configurar os botões de atributo
    private fun setupAttributeButtons(
        minusButtonId: Int,
        valueTextViewId: Int,
        plusButtonId: Int,
        attribute: Attribute
    ) {
        val minusButton = findViewById<Button>(minusButtonId)
        val valueTextView = findViewById<TextView>(valueTextViewId)
        val plusButton = findViewById<Button>(plusButtonId)

        valueTextView.text = attribute.score.toString()

        minusButton.setOnClickListener {
            if (attribute.score > 8) {
                attribute.score--
                availablePoints++
                valueTextView.text = attribute.score.toString()
                updateAvailablePoints()
            }
        }

        plusButton.setOnClickListener {
            if (availablePoints > 0 && attribute.score < 20) {
                attribute.score++
                availablePoints--
                valueTextView.text = attribute.score.toString()
                updateAvailablePoints()
            }
        }
    }

    // Função para atualizar os pontos disponíveis no TextView
    private fun updateAvailablePoints() {
        val availablePointsTextView = findViewById<TextView>(R.id.availablePoints)
        availablePointsTextView.text = "Pontos disponíveis: $availablePoints"
    }
}