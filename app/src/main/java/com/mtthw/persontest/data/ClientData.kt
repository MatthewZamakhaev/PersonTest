package com.mtthw.persontest.data

object ClientData {
    val clients = listOf(
        Client(name = "OperatorA", color = 0xFFFF5733.toInt()), // #FF5733
        Client(name = "OperatorB", color = 0xFF33FF57.toInt()), // #33FF57
        Client(name = "OperatorC", color = 0xFF3357FF.toInt()), // #3357FF
        Client(name = "OperatorD", color = 0xFFFF33A1.toInt()), // #FF33A1
        Client(name = "OperatorE", color = 0xFFA133FF.toInt()), // #A133FF
        Client(name = "OperatorF", color = 0xFFFF8C33.toInt()), // #FF8C33
        Client(name = "OperatorG", color = 0xFF33FF8C.toInt()), // #33FF8C
        Client(name = "OperatorH", color = 0xFF8C33FF.toInt()), // #8C33FF
        Client(name = "OperatorI", color = 0xFFFF3333.toInt()), // #FF3333
        Client(name = "OperatorJ", color = 0xFFFF33A1.toInt()), // #33FFA1
    )

    fun getClientByIndex(index: Int): Client {
        return clients[index]
    }
}