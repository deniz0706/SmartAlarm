package com.deniz0706.smartalarm

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Switch
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import com.deniz0706.smartalarm.model.Alarm
import java.time.LocalTime

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

    val simdikiZaman = LocalTime.now()
    val context = LocalContext.current
    val alarmlar = remember { mutableStateListOf<Alarm>() }

    var alarmEklemeAcik by remember { mutableStateOf(false) }
    var saat by remember { mutableIntStateOf(simdikiZaman.hour) }
    var dakika by remember { mutableIntStateOf(simdikiZaman.minute) }

    if (alarmEklemeAcik) {

        Column {
            Text("Alarm Oluştur")

            Button(
                onClick = {
                    alarmlar.add(Alarm(saat, dakika, true))
                    alarmEklemeAcik = false
                }
            ) {
                Text("Alarmı Kaydet")
            }

            Button(
                onClick = {
                    alarmEklemeAcik = false
                }
            ) {
                Text("Geri")
            }

            Text(
                text = "${"%02d".format(saat)}:${"%02d".format(dakika)}",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.clickable {
                    TimePickerDialog(
                        context,
                        { _, yeniSaat, yeniDakika ->
                            saat = yeniSaat
                            dakika = yeniDakika
                        },
                        saat,
                        dakika,
                        true
                    ).show()
                }
            )
        }

    } else {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "${alarmlar.size} alarm kurulu",
                style = MaterialTheme.typography.bodyLarge
            )

            alarmlar.forEach { alarm ->

              Row (
                modifier = Modifier.fillMaxWidth()
              ) {
                Text(
                    text = "${"%02d".format(alarm.saat)}:${"%02d".format(alarm.dakika)}"
                )

                Switch(
                  checked = alarm.acikMi,
                  onCheckedChange = { yeniDeger ->
                    val yeniAlarm = alarm.copy(acikMi = yeniDeger)
                    val index = alarmlar.indexOf(alarmlar)
                    alarmlar[index] = yeniAlarm
                  }
                )

                Button(
                    onClick = {
                        alarmlar.remove(alarm)
                    }
                ) {
                    Text("Sil")
                }
            }
        }
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