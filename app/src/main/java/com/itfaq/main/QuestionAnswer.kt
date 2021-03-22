package com.itfaq.main

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

class QuestionAnswer {
    val TAG = QuestionAnswer::class.java.simpleName

    init {
        answer1()
        answer2()
        answer3()
    }

    var number = 1
    var count = 0
    var ans = 0

    // Q.3 Write a function in Kotlin to generate the nth Fibonacci number
    private fun answer3() {
        //  iterative approach
        val num = 9;
        var no1 = 0
        var no2 = 1
        for (i in 1..num) {
            Log.e(TAG, "iterative Fibonacci = $no1 ")
            val sum = no1 + no2
            no1 = no2
            no2 = sum
        }
        Log.e(TAG, "recursiiterativeve printFibonacci:: ------- end ")
        number = 1
        printFibonacci(num)
    }


    //  recursive approach printFibonacci
    private fun printFibonacci(num: Int) {
        Log.e(TAG, "recursive Fibonacci: $ans")
        val sum = ans + number
        ans = number
        number = sum
        count++
        if (count <= num) {
            printFibonacci(num)
        } else {
            Log.e(TAG, "recursive Fibonacci:: ------------ end ")
        }
    }


    @SuppressLint("NewApi")
    //Q.2 find two strings are anagrams or not
    private fun answer2() {
        val strVal1 = "mother in law"
        val strVal2 = "women hitler"
        val strVal3 = "debit card"
        val strVal4 = "bad credit"

        Log.e(TAG, "queatin2: strVal1 = $strVal1")
        Log.e(TAG, "queatin2: strVal2 = $strVal2")
        checkAnagramsString(strVal1, strVal2)
        Log.e(TAG, "queatin2: strVal3 = $strVal3")
        Log.e(TAG, "queatin2: strVal4 = $strVal4")
        checkAnagramsString(strVal3, strVal4)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkAnagramsString(strVal1: String, strVal2: String) {
        val isAnagram =
            Arrays.equals(strVal1.chars().sorted().toArray(), strVal2.chars().sorted().toArray());
        if (isAnagram)
            Log.e(TAG, "Ans2: Strings are an anagrams")
        else
            Log.e(TAG, "Ans2: Strings are not anagrams")
    }


    /** Q.1. Add arithmetic operators (add, subtract, multiply, divide) to make the following expressions
    true. You can use any parentheses you’d like. You don’t need to write any code for this
    question. 3 1 3 9 = 12
     **/
    private fun answer1() {
        val ans =( (3 + 1) / 3f) * 9
        Log.e(TAG, "Ans1 : = 3+1/3*9 = ${ans.toInt()}")
    }

}