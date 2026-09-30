package com.deniz0706.smartalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                SmartAlarmScreen()
            }
        }
    }
}

@Composable
private fun SmartAlarmScreen() {
    var alarmSayisi by remember { mutableIntStateOf(0) }
    var alarmEklemeAcik by remember { mutableStateOf(false) }

    if (alarmEklemeAcik) {
        Column {
            Text("Alarm Oluştur")

            Button(
                onClick = {
                    alarmEklemeAcik = false
                }
            ) {
                Text("Geri")
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
            )

            Text(
                text = "$alarmSayisi alarm kurulu",
                style = MaterialTheme.typography.bodyLarge,
            )

            Button(
                onClick = {
                    alarmEklemeAcik = true
                }
            ) {
                Text("Alarm Ekle")
            }
        }
    }
}