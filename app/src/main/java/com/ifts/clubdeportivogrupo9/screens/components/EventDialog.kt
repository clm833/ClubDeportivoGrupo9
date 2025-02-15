package com.ifts.clubdeportivogrupo9.screens.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.Style

@Composable
fun EventDialog(
    modifier: Modifier = Modifier,
    @StringRes errorMessage: Int,
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog(
        modifier = modifier
            .background(Color.White)
            .padding(16.dp),
        onDismissRequest = { onDismiss?.invoke() },
        title ={
               Text(
                   text = "Error",
                   style = TextStyle(
                       color = MaterialTheme.colorScheme.onSurface,
                       fontSize = 20.sp,
                       fontWeight = FontWeight.Bold
                   )
               )
        },
        text = {
               Text(
                   text = LocalContext.current.getString(errorMessage),
                   style = TextStyle(
                       color = MaterialTheme.colorScheme.onSurface,
                       fontSize = 16.sp
                   )
               )
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { onDismiss?.invoke()}) {
                    Text(
                        text = "Aceptar",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        })
}