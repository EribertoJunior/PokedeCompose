package com.example.pokedexcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexcompose.R

@Composable
fun LoadingItem(rotationLoading: Float = 0f) {
    Image(
        painter = painterResource(id = R.drawable.pokebola),
        contentDescription = stringResource(R.string.loading_items_content_description),
        modifier = Modifier
            .rotate(rotationLoading)
            .size(64.dp)
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingItemPreview() {
    LoadingItem()
}