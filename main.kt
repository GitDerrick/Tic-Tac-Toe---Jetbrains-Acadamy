package tictactoe

import java.util.*

fun printGameBoard(plays:List<Char>) {
    println("---------")
    println("| ${plays[0]} ${plays[1]} ${plays[2]} |")
    println("| ${plays[3]} ${plays[4]} ${plays[5]} |")
    println("| ${plays[6]} ${plays[7]} ${plays[8]} |")
    println("---------")
}

fun getCoordinate():Int {
    val scanner = Scanner(System.`in`)
    var newCoordinate = 0
    var firstCoordinate = "a"
    var secondCoordinate = "a"

    var continueCycle = true
    while (continueCycle) { // Cycle to get coordinates and check if they are valid
        print("Enter the coordinates: ")
        firstCoordinate = scanner.next()
        secondCoordinate = scanner.next()
        if (!firstCoordinate[0].isDigit() || !secondCoordinate[0].isDigit()) {
            println("You should enter numbers!")
        } else if (firstCoordinate[0].toString().toInt() !in (0..3) || secondCoordinate[0].toString().toInt() !in (0..3)) {
            println("Coordinates should be from 1 to 3!")
        } else continueCycle = false
    }
    when {
        firstCoordinate == "1" && secondCoordinate =="1" -> newCoordinate = 0
        firstCoordinate == "1" && secondCoordinate =="2" -> newCoordinate = 1
        firstCoordinate == "1" && secondCoordinate =="3" -> newCoordinate = 2
        firstCoordinate == "2" && secondCoordinate =="1" -> newCoordinate = 3
        firstCoordinate == "2" && secondCoordinate =="2" -> newCoordinate = 4
        firstCoordinate == "2" && secondCoordinate =="3" -> newCoordinate = 5
        firstCoordinate == "3" && secondCoordinate =="1" -> newCoordinate = 6
        firstCoordinate == "3" && secondCoordinate =="2" -> newCoordinate = 7
        firstCoordinate == "3" && secondCoordinate =="3" -> newCoordinate = 8
    }

    return newCoordinate
}

fun main() {
    var xNot3InARow = true // establish game end booleans
    var oNot3InARow = true

    val winCon1 = listOf(0, 1, 2) // win conditions indices
    val winCon2 = listOf(0, 3, 6)
    val winCon3 = listOf(0, 4, 8)
    val winCon4 = listOf(1, 4, 7)
    val winCon5 = listOf(2, 4, 6)
    val winCon6 = listOf(2, 5, 8)
    val winCon7 = listOf(3, 4, 5)
    val winCon8 = listOf(6, 7, 8)

    val xArr = mutableListOf<Int>() // create an array of indices of each occurrence
    val oArr = mutableListOf<Int>()

    val plays = mutableListOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    var letterToPlay: Char
    var count = 0

    printGameBoard(plays)

    while (xNot3InARow && oNot3InARow && count < 9) {
        count ++
        letterToPlay = if (count%2 == 1) 'X' else 'O'

        var coordinate = getCoordinate()

        while (plays[coordinate] != ' ') { // checks if already played and gets new input
            println("This cell is occupied! Choose another one!")
            coordinate = getCoordinate()
        }

        plays[coordinate] = letterToPlay
        printGameBoard(plays)

        if (letterToPlay == 'X') xArr += coordinate else oArr += coordinate

        if (xArr.containsAll(winCon1) || xArr.containsAll(winCon2) || xArr.containsAll(winCon3) || xArr.containsAll(winCon4) || xArr.containsAll(winCon5) || xArr.containsAll(winCon6) || xArr.containsAll(winCon7) || xArr.containsAll(winCon8)) {
            xNot3InARow = false
            println("X wins")
        }

        if (oArr.containsAll(winCon1) || oArr.containsAll(winCon2) || oArr.containsAll(winCon3) || oArr.containsAll(winCon4) || oArr.containsAll(winCon5) || oArr.containsAll(winCon6) || oArr.containsAll(winCon7) || oArr.containsAll(winCon8)) {
            oNot3InARow = false
            println("O wins")
        }

        if (count == 9) println("Draw")
    }
}
