package com.example.recipeapp.models

import java.io.Serializable

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTime: String,
    val cookTime: String,
    val servings: Int,
    val category: String,
    val imageUrl: String = "",
    var isFavorite: Boolean = false
) : Serializable
