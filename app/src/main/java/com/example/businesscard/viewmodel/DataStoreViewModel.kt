package com.example.businesscard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.businesscard.data.DataStoreInstance
import com.example.businesscard.ui.state.DataStoreUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DataStoreViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(DataStoreUiState())
    val uiState: StateFlow<DataStoreUiState> = _uiState

    init {
        getDataStoreValues()
    }

    fun getDataStoreValues() {
        viewModelScope.launch {
            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.NAME_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(name = valueString)
                    }
                }
            }

            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.ROLE_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(role = valueString)
                    }
                }
            }

            DataStoreInstance.getIntPreferences(
                getApplication(),
                DataStoreInstance.YEAR_KEY
            ).collect { valueInt ->
                if (valueInt != null) {
                    _uiState.update {
                        it.copy(year = valueInt)
                    }
                }
            }

            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.PHONE_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(phone = valueString)
                    }
                }
            }

            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.HANDLE_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(handle = valueString)
                    }
                }
            }

            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.EMAIL_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(email = valueString)
                    }
                }
            }

            DataStoreInstance.getBooleanPreferences(
                getApplication(),
                DataStoreInstance.BOOLEAN_KEY
            ).collect { valueBool ->
                if (valueBool != null) {
                    _uiState.update {
                        it.copy(showContactInfo = valueBool)
                    }
                }
            }
        }
    }

    fun toggleSettings() {
        _uiState.update {
            it.copy(
                showSettings = !_uiState.value.showSettings
            )
        }
    }

    fun toggleContactInfo() {
        _uiState.update {
            it.copy(
                showContactInfo = !_uiState.value.showContactInfo
            )
        }
    }

    fun saveValuesInDataStore() {
        viewModelScope.launch {
            val context = getApplication<Application>()
            val state = _uiState.value

            if (state.isNameUpdated) {
                DataStoreInstance.saveStringPreferences(
                    context,
                    DataStoreInstance.NAME_KEY,
                    state.name
                )
            }

            if (state.isRoleUpdated) {
                DataStoreInstance.saveStringPreferences(
                    context,
                    DataStoreInstance.ROLE_KEY,
                    state.role
                )
            }

            if (state.isYearUpdated) {
                DataStoreInstance.saveIntPreferences(
                    context,
                    DataStoreInstance.YEAR_KEY,
                    state.year
                )
            }

            if (state.isPhoneUpdated) {
                DataStoreInstance.saveStringPreferences(
                    context,
                    DataStoreInstance.PHONE_KEY,
                    state.phone
                )
            }

            if (state.isHandleUpdated) {
                DataStoreInstance.saveStringPreferences(
                    context,
                    DataStoreInstance.HANDLE_KEY,
                    state.handle
                )
            }

            if (state.isEmailUpdated) {
                DataStoreInstance.saveStringPreferences(
                    context,
                    DataStoreInstance.EMAIL_KEY,
                    state.email
                )
            }

            DataStoreInstance.saveBooleanPreferences(
                context,
                DataStoreInstance.BOOLEAN_KEY,
                state.showContactInfo
            )
        }
    }

    fun updateName(value: String) {
        _uiState.update {
            it.copy(name = value, isNameUpdated = true)
        }
    }

    fun updateRole(value: String) {
        _uiState.update {
            it.copy(role = value, isRoleUpdated = true)
        }
    }

    fun updateYear(value: Int) {
        _uiState.update {
            it.copy(year = value, isYearUpdated = true)
        }
    }

    fun updatePhone(value: String) {
        _uiState.update {
            it.copy(phone = value, isPhoneUpdated = true)
        }
    }

    fun updateHandle(value: String) {
        _uiState.update {
            it.copy(handle = value, isHandleUpdated = true)
        }
    }

    fun updateEmail(value: String) {
        _uiState.update {
            it.copy(email = value, isEmailUpdated = true)
        }
    }
}
