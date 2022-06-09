package com.ashish.locationtracker.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashish.locationtracker.data.manager.UserManager
import com.ashish.locationtracker.navigation.Screens
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun UserInputScreen(navController: NavController) {
    var name by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val userManager = UserManager(context)
    val hasData = name.isNotBlank() && number.isNotBlank()
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                text = "Track \n" +
                        "Your \n" +
                        "Location ", fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground, fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = name,
                textStyle = TextStyle(textAlign = TextAlign.Start),
                onValueChange = { name = it },
                shape = RoundedCornerShape(16.dp),
                label = { Text(text = "Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Default
                ),
                singleLine = true,
                maxLines = 1,

                )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = number,
                textStyle = TextStyle(textAlign = TextAlign.Start),
                onValueChange = { number = it },
                shape = RoundedCornerShape(16.dp),
                label = { Text(text = "Mobile Number") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Default
                ),
                singleLine = true,
                maxLines = 1,

                )
            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    if (hasData) {
//                        delay(1000L)
                        GlobalScope.launch {
                            userManager.storeUser(number = number.toInt(), name = name)
//                            Toast
//                                .makeText(context, "Saving Data", Toast.LENGTH_SHORT)
//                                .show()
//                            delay(1000L)
//                            Toast
//                                .makeText(context, "Saved", Toast.LENGTH_SHORT)
//                                .show()
//                            delay(1000L)
//                            navController.navigate(Screens.HomeScreen.route)
                        }
                        Toast
                            .makeText(context, "Saving Data", Toast.LENGTH_SHORT)
                            .show()
                        Toast
                            .makeText(context, "Saved", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(Screens.HomeScreen.route)



//                    Give 2 Sec Delay and save name and number to Data Store.

                    } else {
                        Toast
                            .makeText(
                                context,
                                "Please enter above details",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
//
                }
                .fillMaxWidth()
                .height(56.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = if (hasData) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Continue",
                    fontSize = 21.sp,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colors.onPrimary
                )
            }

            Text(
                text = "Enter above details to continue",
                fontSize = 15.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                color = MaterialTheme.colors.onSurface
            )


        }
    }


}

