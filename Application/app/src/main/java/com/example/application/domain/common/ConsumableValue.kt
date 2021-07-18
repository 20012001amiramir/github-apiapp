package com.example.application.domain.common

class ConsumableValue<T>(private val data: T) {

    private var consumed = false

    fun consume(block: ConsumableValue<T>.(T) -> Unit) {
        if (!consumed) {
            consumed = true
            block(data)
        }
    }
}
