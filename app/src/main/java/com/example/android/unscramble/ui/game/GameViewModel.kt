package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel: ViewModel() {

    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambledWord = "test"
    private var wordList: MutableList<String> = mutableListOf()
    private var _currentWord =""

    init{
        Log.d("GameFragment", "GameViewModel created")
        getNextWord()
    }

    //in Kotlin di dafault se non è indicato il modificatore di accesso è sotto inteso public
    val score: Int
        get() = _score
    val currentWordCount
        get() = _currentWordCount
    val currentScrambledWord
        get() = _currentScrambledWord
    val currentWord
        get() = _currentWord

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed")

    }

    fun nextWord(): Boolean {
        if(_currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            return true
        }else{
            return false
        }
    }

    private fun getNextWord(){
        _currentWord = getUniqueWord()
        wordList.add(_currentWord)
        _currentWordCount ++
        _currentScrambledWord  = wordScrambler(_currentWord)
    }

    private fun wordScrambler(currentWord: String) : String{
        val scambledWord = currentWord.toMutableList().shuffled().joinToString("")
        return if(scambledWord == currentWord) { wordScrambler(currentWord)} else {scambledWord}
    }

    private fun getUniqueWord(): String{
        val word = allWordsList[Random.nextInt(0, allWordsList.size)]
        return if (wordList.contains(word)) {getUniqueWord()} else word
    }

}