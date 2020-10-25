package com.example.adbplugindemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.adbplugindemo.command.ChangeBackgroundCommand

class AdbCommandReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!BuildConfig.DEBUG) {
            // Only available for debug mode
            return
        }

        val commandKey = intent.getStringExtra(KEY_COMMAND)
        EventBusMock.post(EventBusMock.KEY_RECENT_COMMAND, commandKey ?: "Invalid")

        val commandType = CommandType.from(commandKey)
        if (commandType == null) {
            val availableCommands = CommandType.values().joinToString("\n") { it.commandKey }
            Log.i(TAG, "Available commands:\n$availableCommands")
            return
        }

        commandType.command.handle(context, intent.extras)
    }

    companion object {
        private const val TAG = "ADB_COMMAND"

        private const val KEY_COMMAND = "cmd"
    }
}

enum class CommandType(val commandKey: String, val command: Command) {
    CHANGE_BACKGROUND("changeBg", ChangeBackgroundCommand())
    // TODO: Add more commands
    ;

    companion object {
        private val KEY_TO_TYPE_MAP = values().associateBy { it.commandKey }

        fun from(commandKey: String?): CommandType? = KEY_TO_TYPE_MAP[commandKey]
    }
}

interface Command {
    fun handle(context: Context, arguments: Bundle?)
}
