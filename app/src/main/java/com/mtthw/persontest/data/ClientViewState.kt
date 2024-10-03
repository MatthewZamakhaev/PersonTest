package com.mtthw.persontest.data

data class ClientViewState(
    val clientName: String = "",
    val textColor: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)
