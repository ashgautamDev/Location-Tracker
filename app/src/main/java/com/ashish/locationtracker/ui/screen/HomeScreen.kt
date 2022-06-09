package com.ashish.locationtracker.ui.screen

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashish.locationtracker.LocationUpdateViewModel
import com.ashish.locationtracker.data.manager.UserManager
import com.ashish.locationtracker.hasPermission
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.jar.Manifest

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(onLocateMe : () -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val requireContext : CompositionLocalContext
    val application = context.applicationContext
    val userManager = UserManager(context)

    var name by remember {
        mutableStateOf("Ashish")
    }
    var number by remember {
        mutableStateOf(0)
    }

    coroutineScope.launch{

        userManager.userNumberFlow.collect { t->
            number = t
        }
        userManager.userNameFlow.collect { username ->
            name = username
        }
    }
    val viewModel = LocationUpdateViewModel(application as Application)

  val location = viewModel.locationListLiveData.observeAsState()
   val l =  location.value


    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 36.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = name,
                fontSize = 21.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = number.toString(),
                fontSize = 18.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(100.dp))

            Column(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colors.secondary
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Current Location :-",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 23.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = l.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF707070),

                        )
                    Text(
                        text = "",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF707070),

                        )

                    Text(
                        text = "",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF707070),

                        )

                    Text(
                        text = "",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF707070),

                        )

                }


            }
            val buttonText = "Track Location"
            Box(modifier = Modifier
                .padding(36.dp)
                .clickable {
                    onLocateMe()
                }
                .fillMaxWidth()
                .height(56.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = MaterialTheme.colors.primary)
            ) {
                Text(
                    text = buttonText,
                    fontSize = 21.sp,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colors.onPrimary
                )
            }


        }


    }

}