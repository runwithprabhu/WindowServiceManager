package com.example.recipeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.models.Recipe

class RecipeDetailActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        val recipe = intent.getSerializableExtra("recipe") as? Recipe
        recipe?.let { displayRecipe(it) }
    }
    
    private fun displayRecipe(recipe: Recipe) {
        supportActionBar?.title = recipe.name
        
        findViewById<TextView>(R.id.detailDescription).text = recipe.description
        findViewById<TextView>(R.id.detailCategory).text = recipe.category
        findViewById<TextView>(R.id.detailServings).text = "${recipe.servings} servings"
        findViewById<TextView>(R.id.detailPrepTime).text = "Prep\n${recipe.prepTime}"
        findViewById<TextView>(R.id.detailCookTime).text = "Cook\n${recipe.cookTime}"
        
        val ingredientsText = recipe.ingredients.joinToString("\n") { "• $it" }
        findViewById<TextView>(R.id.detailIngredients).text = ingredientsText
        
        val instructionsText = recipe.instructions.mapIndexed { index, instruction ->
            "${index + 1}. $instruction"
        }.joinToString("\n\n")
        findViewById<TextView>(R.id.detailInstructions).text = instructionsText
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
