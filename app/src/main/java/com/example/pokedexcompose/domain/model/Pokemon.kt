package com.example.pokedexcompose.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<String>,
    val height: Int,
    val weight: Int,
    val imageUrl: String
)
