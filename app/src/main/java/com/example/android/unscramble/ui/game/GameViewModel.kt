package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambledWord = "test"

    //in Kotlin di dafault se non è indicato il modificatore di accesso è sotto inteso public
    val score: Int
        get() = _score
    val currentWordCount
        get() = _currentWordCount
    val currentScrambledWord
        get() = _currentScrambledWord

}