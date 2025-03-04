package com.example.pokedexcompose.data.model.remote

import com.google.gson.annotations.SerializedName

data class SpeciesRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("evolution_chain") val evolutionChainAddressRemote: EvolutionChainAddressRemote?,
    @SerializedName("flavor_text_entries") val flavorTextEntreyRemotes: List<FlavorTextEntreiesRemote>
)

data class EvolutionChainAddressRemote(
    @SerializedName("url") val url: String
)

data class FlavorTextEntreiesRemote(
    @SerializedName("flavor_text") val flavorText: String,
    @SerializedName("version") val versionRemote: VersionRemote,
    @SerializedName("language") val languageRemote: LanguageRemote
)

data class LanguageRemote(
    @SerializedName("name") val name: String
)

data class VersionRemote(
    @SerializedName("name") val name: String
)