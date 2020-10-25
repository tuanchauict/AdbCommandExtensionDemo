package com.example.adbplugindemo

object EventBusMock {
    const val KEY_RECENT_COMMAND = "recent-command"
    const val KEY_BACKGROUND_COLOR = "background-color"

    private val LISTENERS: MutableList<Consumer> = mutableListOf()
    fun registerConsumer(consumer: Consumer) {
        LISTENERS.add(consumer)
    }

    fun post(key: String, value: Any) = LISTENERS.forEach { it.run(key, value) }

    interface Consumer {
        fun run(key: String, value: Any)
    }
}
