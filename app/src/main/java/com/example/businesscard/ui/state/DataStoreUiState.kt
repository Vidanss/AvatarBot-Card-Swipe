package com.example.businesscard.ui.state

data class DataStoreUiState(
    val showSettings: Boolean = false,
    val name: String = "Azeneth <3",
    val isNameUpdated: Boolean = false,
    val role: String = "Android Developer Extraordinarie",
    val isRoleUpdated: Boolean = false,
    val year: Int = 14,
    val isYearUpdated: Boolean = false,
    val phone: String = "+507 (345) 737 021 1840",
    val isPhoneUpdated: Boolean = false,
    val handle: String = "@AndroidDev",
    val isHandleUpdated: Boolean = false,
    val email: String = "aze.azeneth@android.com",
    val isEmailUpdated: Boolean = false,
    val showContactInfo: Boolean = true
)


