package com.mtthw.persontest.data

sealed class ClientIntent {
    object LoadClient : ClientIntent()
}