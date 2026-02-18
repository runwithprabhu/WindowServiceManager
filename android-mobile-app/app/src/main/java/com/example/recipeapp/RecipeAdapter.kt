package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.models.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    
    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.recipeName)
        val descriptionText: TextView = view.findViewById(R.id.recipeDescription)
        val categoryText: TextView = view.findViewById(R.id.recipeCategory)
        val timeText: TextView = view.findViewById(R.id.recipeTime)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        
        holder.nameText.text = recipe.name
        holder.descriptionText.text = recipe.description
        holder.categoryText.text = "📍 ${recipe.category}"
        holder.timeText.text = "${recipe.prepTime} + ${recipe.cookTime}"
        
        // Set category color bar based on category
        val colorBar = holder.itemView.findViewById<View>(R.id.categoryColorBar)
        val categoryColor = when (recipe.category.lowercase()) {
            "italian" -> android.graphics.Color.parseColor("#E91E63")
            "indian" -> android.graphics.Color.parseColor("#FF9800")
            "mexican" -> android.graphics.Color.parseColor("#F44336")
            "salad" -> android.graphics.Color.parseColor("#4CAF50")
            "dessert" -> android.graphics.Color.parseColor("#9C27B0")
            else -> android.graphics.Color.parseColor("#4CAF50")
        }
        colorBar.setBackgroundColor(categoryColor)
        
        holder.itemView.setOnClickListener {
            onItemClick(recipe)
        }
    }
    
    override fun getItemCount() = recipes.size
}
