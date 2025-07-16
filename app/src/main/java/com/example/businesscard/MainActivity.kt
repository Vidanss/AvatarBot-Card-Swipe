package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.model.ContactInfo
import com.example.businesscard.model.ProfileInfo
import com.example.businesscard.ui.theme.BusinessCardTheme
import com.example.businesscard.viewmodel.DataStoreViewModel
import com.example.businesscard.ui.screen.DataStoreScreen

class MainActivity : ComponentActivity() {
    private val myViewModel by viewModels<DataStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onDoubleTap = {
                                    myViewModel.toggleSettings()
                                }
                            )
                        },
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState by myViewModel.uiState.collectAsState()

                    if (uiState.showSettings) {
                        DataStoreScreen(viewModel = myViewModel)
                    } else {
                        val profileInfo = ProfileInfo(
                            name = uiState.name,
                            role = uiState.role,
                            year = uiState.year
                        )
                        val contactInfo = ContactInfo(
                            phone = uiState.phone,
                            handle = uiState.handle,
                            email = uiState.email,
                            showContactInfo = uiState.showContactInfo
                        )
                        BusinessCard(
                            profileInfo = profileInfo,
                            contactInfo = contactInfo,
                            showContactInfo = uiState.showContactInfo
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    profileInfo: ProfileInfo,
    contactInfo: ContactInfo,
    showContactInfo: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        ProfileInfo(
            profileInfo = profileInfo,
            modifier = Modifier.align(Alignment.Center)
        )
        ContactInfo(
            contactInfo = contactInfo,
            showContactInfo = showContactInfo,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ProfileInfo(profileInfo: ProfileInfo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.vicbot),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .background(Color(0xFF073042))
                .size(100.dp)
        )

        Text(
            text = profileInfo.name,
            fontSize = 40.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
        )

        Text(
            text = profileInfo.role,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF006D3A),
        )

        Text(
            text = "${profileInfo.year} years of Experience",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun ContactInfo(contactInfo: ContactInfo, showContactInfo: Boolean, modifier: Modifier = Modifier) {
    if (showContactInfo) {
        Column(modifier = modifier.padding(bottom = 20.dp)) {
            ContactRow(text = contactInfo.phone, icon = Icons.Filled.Call)
            ContactRow(text = contactInfo.handle, icon = Icons.Filled.Share)
            ContactRow(text = contactInfo.email, icon = Icons.Filled.Email)
        }
    }
}

@Composable
fun ContactRow(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
        Text(text = text)
    }
}
