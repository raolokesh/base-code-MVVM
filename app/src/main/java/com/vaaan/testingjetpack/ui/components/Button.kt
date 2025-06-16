package com.vaaan.testingjetpack.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vaaan.testingjetpack.ui.theme.AppTypography


@Composable
fun PrimaryButton(
    buttonName: String = "Button",
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,

    ),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .height(72.dp)
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = buttonName,
            textAlign = TextAlign.Center,
            style = AppTypography.titleLarge,
        )
    }
}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(
        "Login", colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
//        Toast.makeText(getAppContext(), "Button Clicked", Toast.LENGTH_SHORT).show()
    }

}