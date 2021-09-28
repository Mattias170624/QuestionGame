package com.example.questiongame

object DataManager { // Saved user data
    var subject: Any = 0
    var difficulty: Int = 0
    var number1: Int = 0
    var number2: Int = 0
    var sum: Int = 0
    var points: Int = 0

    fun difficultyProperties() {
        when (difficulty) {
            1 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (50..100).random()
                    number2 = (50..100).random()
                } else if (subject == "*") {
                    number1 = (3..9).random()
                    number2 = (3..9).random()
                } else {
                    number1 = ((4..(40) / 2).random() * 2)
                    primeNumberMaker()
                }
            }

            2 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (100..200).random()
                    number2 = (100..200).random()
                } else if (subject == "*") {
                    number1 = (5..12).random()
                    number2 = (5..12).random()
                } else {
                    number1 = ((4..(60) / 2).random() * 2)
                    primeNumberMaker()
                }
            }

            3 -> {
                if (subject == "+" || subject == "-") {
                    number1 = (300..500).random()
                    number2 = (300..500).random()
                } else if (subject == "*") {
                    number1 = (9..19).random()
                    number2 = (9..19).random()
                } else {
                    number1 = ((4..(90) / 2).random() * 2)
                    primeNumberMaker()
                }
            }
        }
    }

    fun subjectProperties() {
        when (subject) {
            1 -> subject = "+"
            2 -> subject = "-"
            3 -> subject = "*"
            4 -> subject = "/"
        }
    }

    private fun primeNumberMaker() {

        val listNum = mutableListOf<Int>()
        for (num in 2..number1 / 2) {
            if (number1 % num == 0) {
                listNum.add(num)
                number2 = listNum.random()
            }
        }
    }
}










