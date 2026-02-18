package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.models.Recipe

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private var recipes = mutableListOf<Recipe>()
    private var filteredRecipes = mutableListOf<Recipe>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupRecyclerView()
        loadSampleRecipes()
    }
    
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        adapter = RecipeAdapter(filteredRecipes) { recipe ->
            openRecipeDetail(recipe)
        }
        recyclerView.adapter = adapter
    }
    
    private fun loadSampleRecipes() {
        recipes = mutableListOf(
            Recipe(
                1, "Spaghetti Carbonara",
                "Classic Italian pasta dish",
                listOf("400g spaghetti", "200g bacon", "4 eggs", "100g parmesan", "Black pepper"),
                listOf("Boil pasta", "Fry bacon", "Mix eggs and cheese", "Combine all", "Serve hot"),
                "10 min", "20 min", 4, "Italian"
            ),
            Recipe(
                2, "Chicken Curry",
                "Spicy and flavorful curry",
                listOf("500g chicken", "2 onions", "3 tomatoes", "Curry spices", "Coconut milk"),
                listOf("Sauté onions", "Add chicken", "Add spices", "Simmer with coconut milk"),
                "15 min", "30 min", 4, "Indian"
            ),
            Recipe(
                3, "Caesar Salad",
                "Fresh and crispy salad",
                listOf("Romaine lettuce", "Croutons", "Parmesan", "Caesar dressing", "Chicken breast"),
                listOf("Wash lettuce", "Grill chicken", "Toss with dressing", "Add toppings"),
                "10 min", "10 min", 2, "Salad"
            ),
            Recipe(
                4, "Beef Tacos",
                "Mexican street food favorite",
                listOf("500g ground beef", "Taco shells", "Lettuce", "Cheese", "Salsa", "Sour cream"),
                listOf("Cook beef with spices", "Warm taco shells", "Assemble with toppings"),
                "5 min", "15 min", 4, "Mexican"
            ),
            Recipe(
                5, "Chocolate Cake",
                "Rich and moist dessert",
                listOf("Flour", "Sugar", "Cocoa powder", "Eggs", "Butter", "Milk"),
                listOf("Mix dry ingredients", "Add wet ingredients", "Bake at 180°C", "Cool and frost"),
                "20 min", "40 min", 8, "Dessert"
            )
        )
        
        filteredRecipes.clear()
        filteredRecipes.addAll(recipes)
        adapter.notifyDataSetChanged()
    }
    
    private fun openRecipeDetail(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra("recipe", recipe)
        startActivity(intent)
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            
            override fun onQueryTextChange(newText: String?): Boolean {
                filterRecipes(newText ?: "")
                return true
            }
        })
        
        return true
    }
    
    private fun filterRecipes(query: String) {
        filteredRecipes.clear()
        
        if (query.isEmpty()) {
            filteredRecipes.addAll(recipes)
        } else {
            filteredRecipes.addAll(
                recipes.filter {
                    it.name.contains(query, ignoreCase = true) ||
                    it.category.contains(query, ignoreCase = true)
                }
            )
        }
        
        adapter.notifyDataSetChanged()
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                showFavorites()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun showFavorites() {
        filteredRecipes.clear()
        filteredRecipes.addAll(recipes.filter { it.isFavorite })
        adapter.notifyDataSetChanged()
    }
}
