package com.example.adbplugindemo.command

import android.content.Context
import android.os.Bundle
import com.example.adbplugindemo.Command
import com.example.adbplugindemo.EventBusMock

/**
 * A [Command] to change the background color of the main screen to red, green, blue, or white by
 * default.
 */
class ChangeBackgroundCommand: Command {
    override fun handle(context: Context, arguments: Bundle?) {
        val backgroundColorString = arguments?.getString(KEY_BACKGROUND_COLOR) ?: return

        val backgroundColor = NAME_TO_COLOR_MAP[backgroundColorString] ?: 0xFFFFFFFF
        EventBusMock.post(EventBusMock.KEY_BACKGROUND_COLOR, backgroundColor.toInt())
    }

    companion object {
        private const val KEY_BACKGROUND_COLOR = "bgcolor"

        private val NAME_TO_COLOR_MAP = mapOf(
            "red" to 0xFFFF0000,
            "green" to 0xFF00FF00,
            "blue" to 0xFF0000FF
        )
    }
}
