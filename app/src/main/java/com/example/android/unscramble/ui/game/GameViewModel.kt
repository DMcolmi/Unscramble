package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel: ViewModel() {

    private val _score = MutableLiveData<Int>(0)
    private val _currentWordCount = MutableLiveData<Int>(0)
    private val _currentScrambledWord = MutableLiveData<String>()
    private var wordList: MutableList<String> = mutableListOf()
    private var _currentWord =""

    init{
        Log.d("GameFragment", "GameViewModel created")
        getNextWord()
    }

    //in Kotlin di dafault se non è indicato il modificatore di accesso è sotto inteso public
    val score: LiveData<Int>
        get() = _score
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount
    val currentScrambledWord : LiveData<String>
        get() = _currentScrambledWord
    val currentWord
        get() = _currentWord

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed")

    }

    fun nextWord(): Boolean {
        if(_currentWordCount.value!! < MAX_NO_OF_WORDS){
            getNextWord()
            return true
        }else{
            return false
        }
    }

    private fun getNextWord(){
        _currentWord = getUniqueWord()
        wordList.add(_currentWord)
        _currentWordCount.value = (_currentWordCount.value)?.inc()
        _currentScrambledWord.value  = wordScrambler(_currentWord)
    }

    private fun wordScrambler(currentWord: String) : String{
        val scambledWord = currentWord.toMutableList().shuffled().joinToString("")
        return if(scambledWord == currentWord) { wordScrambler(currentWord)} else {scambledWord}
    }

    private fun getUniqueWord(): String{
        val word = allWordsList[Random.nextInt(0, allWordsList.size)]
        return if (wordList.contains(word)) {getUniqueWord()} else word
    }

    private fun increaseScore(){
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(word: String) :Boolean{
        if(word.equals(currentWord, true))
            increaseScore()

        return word.equals(currentWord, true)
    }

    fun newGameInit(){
        _score.value = 0
        _currentWordCount.value = 0
        wordList.clear()
        getNextWord()
    }

}