package com.developersbreach.composeactors.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.developersbreach.composeactors.R
import com.developersbreach.composeactors.ui.screens.actorDetails.ActorDetailScreen

/**
 * Reusable composable used in all screens to load image.
 * Couldn't figure out shaping the size of placeholder and error images still.
 *
 * @param imageUrl url for loading image with coil.
 * @param shape to clip shape of the image
 * @param showAnimProgress remove the background loading progress bar of image if not necessary,
 * by default is visible for all images which uses this composable. False in actors [ActorDetailScreen].
 */
@Composable
fun LoadNetworkImage(
    imageUrl: String?,
    contentDescription: String,
    modifier: Modifier,
    shape: Shape,
    showAnimProgress: Boolean = true
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                if (showAnimProgress) {
                    placeholder(R.drawable.animated_progress)
                    error(R.drawable.ic_image_not_available)
                }
            }),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(shape)
            .background(color = MaterialTheme.colors.surface)
    )
}