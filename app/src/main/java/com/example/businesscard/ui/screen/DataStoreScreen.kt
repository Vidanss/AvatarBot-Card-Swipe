package com.example.businesscard.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.businesscard.viewmodel.DataStoreViewModel
import com.example.businesscard.R

@Composable
fun DataStoreScreen(viewModel: DataStoreViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.vicbot),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .background(Color(0xFF073042))
                    .size(100.dp)
            )
        }

        item {
            TextField(
                value = uiState.name,
                onValueChange = viewModel::updateName,
                label = { Text("Name") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            TextField(
                value = uiState.role,
                onValueChange = viewModel::updateRole,
                label = { Text("Role") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            Column {
                Text(
                    text = "Experience",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${uiState.year}")
                    Slider(
                        value = uiState.year.toFloat(),
                        onValueChange = { viewModel.updateYear(it.toInt()) },
                        valueRange = 0f..50f,
                    )
                }
            }
        }

        item {
            TextField(
                value = uiState.phone,
                onValueChange = viewModel::updatePhone,
                label = { Text("Phone") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            TextField(
                value = uiState.handle,
                onValueChange = viewModel::updateHandle,
                label = { Text("Handle") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            TextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                label = { Text("Email") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Show Contact Info")
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = uiState.showContactInfo,
                    onCheckedChange = { viewModel.toggleContactInfo() }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(onClick = {
                viewModel.saveValuesInDataStore()
                viewModel.toggleSettings()
            }) {
                Text("Save & Close")
            }
        }
    }
}


