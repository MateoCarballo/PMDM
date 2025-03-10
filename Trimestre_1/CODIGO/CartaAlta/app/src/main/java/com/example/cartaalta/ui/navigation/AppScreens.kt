package com.example.cartaalta.ui.navigation

enum class AppScreens(){
    HOME,
    GAME,
    GAMEOVER;

    fun withArgs(vararg args: String): String {
        return buildString {
            append(name)
            args.forEach { append("/$it") }
        }
    }
}