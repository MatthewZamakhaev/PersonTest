package com.mtthw.persontest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtthw.persontest.data.ClientData
import com.mtthw.persontest.data.ClientIntent
import com.mtthw.persontest.data.ClientViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientViewModel : ViewModel() {

    private val _state = MutableStateFlow(ClientViewState(isLoading = true))
    val state: StateFlow<ClientViewState> = _state

    fun handleIntent(intent: ClientIntent) {
        viewModelScope.launch {
            when (intent) {
                is ClientIntent.LoadClient -> loadClientData()
            }
        }
    }

    private fun loadClientData() {
        try {
            val client = ClientData.getClientByIndex(0)

            _state.value = ClientViewState(
                clientName = client.name,
                textColor = client.color,
                isLoading = false
            )
        } catch (e: Exception) {
            _state.value = ClientViewState(
                isLoading = false,
                error = e.message
            )
        }
    }
}