package connectfour

import java.lang.NumberFormatException
import kotlin.system.exitProcess

data class Board(val rows: Int, val columns: Int, val cells: Array<Array<String>>)
data class Game(
    var firstPlayer: String,
    var secondPlayer: String,
    var board: Board,
    var scores: MutableMap<String, Int>,
    var firstPlayerSymbol: String,
    var secondPlayerSymbol: String
)


fun main() {
    println("Connect Four")
    val game = setupGame()
    playGameFinal(game)
}

fun setupGame(): Game {
    val firstPlayerName = readPlayerName("First")
    val secondPlayerName = readPlayerName("Second")
    val dimensions = setBoardDimensions()
    val board = createBoard(dimensions.first, dimensions.second)
    val scores = mutableMapOf(firstPlayerName to 0, secondPlayerName to 0)
    val firstPlayerSymbol = "o"
    val secondPlayerSymbol = "*"
    return Game(firstPlayerName, secondPlayerName, board, scores, firstPlayerSymbol, secondPlayerSymbol)
}


fun readPlayerName(playerNumber: String): String {
    println("$playerNumber player's name:")
    return readln()
}

fun createBoard(rows: Int, columns: Int): Board {
    return Board(rows, columns, Array(rows) { Array(columns) { " " } })
}


fun setBoardDimensions(): Pair<Int, Int> {
    var isValid = false
    var numberOfRows = 6
    var numberOfColumns = 7
    while (!isValid) {
        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        val boardDimensionsInput = readln().replace("\\s".toRegex(), "")
        if (boardDimensionsInput.isBlank()) {
            isValid = true
        } else {
            val dimensions = boardDimensionsInput.split("x", "X")
            if (dimensions.size != 2) {
                println("Invalid input")
            } else {
                try {
                    val numRows = dimensions[0].toInt()
                    val numColumns = dimensions[1].toInt()
                    if (numRows in 5..9) {
                        numberOfRows = numRows
                        if (numColumns in 5..9) {
                            numberOfColumns = numColumns
                            isValid = true
                        } else {
                            println("Board columns should be from 5 to 9")
                        }
                    } else {
                        println("Board rows should be from 5 to 9")
                    }
                } catch (e: NumberFormatException) {
                    println("Invalid input")
                }
            }
        }
    }
    return Pair(numberOfRows, numberOfColumns)
}

fun setNumberOfGames(): Int {
    printSetNumberOfGames()
    var isNumOfGamesValid = false
    var numOfGames = 1
    while (!isNumOfGamesValid) {
        try {

            val input = readln()
            if (input.isEmpty() || input.toIntOrNull() == 1) {
                isNumOfGamesValid = true
            } else {
                if (input.toInt() > 1) {
                    numOfGames = input.toInt()
                    isNumOfGamesValid = true
                } else {
                    throw NumberFormatException()
                }
            }

        } catch (e: NumberFormatException) {
            println("Invalid input")
            printSetNumberOfGames()
        }
    }
    return numOfGames

}

fun printSetNumberOfGames() {
    println("Do you want to play single or multiple games?")
    println("For a single game, input 1 or press Enter")
    println("Input a number of games:")
}


fun drawBoard(board: Board) {

    printColumnIndexes(board.columns)
    printBoardTopPart(board.rows, board.columns, board)
    printBoardBottomPart(board.columns)

}

fun printColumnIndexes(columns: Int) {
    val columnIndexes = (1..columns).joinToString(" ") { "$it" }
    println(" $columnIndexes ")
}

fun printBoardTopPart(rows: Int, columns: Int, board: Board) {
    for (i in 0 until rows) {
        print("║")
        for (j in 0 until columns) {
            print("${board.cells[i][j]}║")
        }
        println()
    }
}

fun printBoardBottomPart(columns: Int) {
    print("╚")
    repeat(columns - 1) {
        print("═╩")
    }
    print("═╝")
    println()
}


fun playGame(game: Game) {
    var currentPlayer = game.firstPlayer
    var input: String
    var winner: String? = null
    var draw = false


    while (winner == null && !draw) {
        printPlayerTurn(currentPlayer, game)
        input = readln().trim()
        if (input == "end") {
            println("Game Over!")
            exitProcess(0)
        }
        val column = getColumnNumber(input, game.board.columns) ?: continue

        val placed = placePiece(game.board, currentPlayer, column, game)
        if (!placed) {
            println("Column ${column + 1} is full")
            continue
        }
        drawBoard(game.board)
        if (checkForWin(game.board)) {
            winner = currentPlayer
            incrementScore(winner, game.scores, false)
        } else if (checkForDraw(game.board)) {
            draw = true
            incrementScore(game.firstPlayer, game.scores, true)
            incrementScore(game.secondPlayer, game.scores, true)
        } else {
            currentPlayer = switchPlayer(currentPlayer, game)

        }

    }

    printResult(winner, game.scores)

}

fun printPlayerTurn(currentPlayer: String, game: Game) {
    println(if (currentPlayer == game.firstPlayer) "${game.firstPlayer}'s turn: " else "${game.secondPlayer}'s turn: ")
}

fun getColumnNumber(input: String, columnCount: Int): Int? {
    return try {
        val column = input.toInt()
        if (column < 1 || column > columnCount) {
            throw IllegalArgumentException("The column number is out of range (1 - ${columnCount})")
        }
        column - 1
    } catch (e: NumberFormatException) {
        println("Incorrect column number")
        null
    } catch (e: IllegalArgumentException) {
        println(e.message)
        null
    } catch (e: Exception){
        if (input.toIntOrNull() == null)
            println("Incorrect column number")
        else
            throw e
        null
    }
}

fun placePiece(board: Board, player: String, column: Int, game: Game): Boolean {
    for (row in board.rows - 1 downTo 0) {
        if (board.cells[row][column] == " ") {
            board.cells[row][column] =
                if (player == game.firstPlayer) game.firstPlayerSymbol else game.secondPlayerSymbol
            return true
        }
    }
    return false
}


fun incrementScore(player: String, scores: MutableMap<String, Int>, isDraw: Boolean) {
    scores[player] = scores[player]?.plus(if (isDraw) 1 else 2) ?: if (isDraw) 1 else 2
}


fun switchPlayer(currentPlayer: String, game: Game): String {
    return if (currentPlayer == game.firstPlayer) game.secondPlayer else game.firstPlayer
}

fun printResult(winner: String?, scores: MutableMap<String, Int>) {
    if (winner != null) {
        println("Player $winner won")
    } else {
        println("It is a draw")
    }
    println("Score")
    scores.forEach { (player, score) ->
        print("$player: $score ")
    }
    println()
}

fun checkForWin(board: Board): Boolean {
    return checkForWinHorizontalFirstPlayer(board) ||
            checkForWinVerticalFirstPlayer(board) ||
            checkForWinDiagonalBottomLeftTopRightFirstPlayer(board) ||
            checkForWinDiagonalTopLeftBottomRightFirstPlayer(board) ||
            checkForWinHorizontalSecondPlayer(board) ||
            checkForWinVerticalSecondPlayer(board) ||
            checkForWinDiagonalBottomLeftTopRightSecondPlayer(board) ||
            checkForWinDiagonalTopLeftBottomRightSecondPlayer(board)
}

fun checkForWinHorizontalSecondPlayer(board: Board): Boolean {
    var isValid = false
    for (row in board.cells) {
        for (col in 0..board.cells[0].size - 4) {
            if (row[col] == "*" && row[col + 1] == "*" && row[col + 2] == "*" && row[col + 3] == "*") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForWinVerticalSecondPlayer(board: Board): Boolean {
    var isValid = false
    for (col in 0 until board.cells[0].size) {
        for (row in 0..board.cells.size - 4) {
            if (board.cells[row][col] == "*" && board.cells[row + 1][col] == "*" && board.cells[row + 2][col] == "*" && board.cells[row + 3][col] == "*") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForWinDiagonalBottomLeftTopRightSecondPlayer(board: Board): Boolean {
    var isValid = false
    for (col in 0..board.cells[0].size - 4) {
        for (row in 0..board.cells.size - 4) {
            if (board.cells[row][col] == "*" && board.cells[row + 1][col + 1] == "*" && board.cells[row + 2][col + 2] == "*" && board.cells[row + 3][col + 3] == "*") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForWinDiagonalTopLeftBottomRightSecondPlayer(board: Board): Boolean {
    var isValid = false
    for (col in 0..board.cells[0].size - 4) {
        for (row in 3 until board.cells.size) {
            if (board.cells[row][col] == "*" && board.cells[row - 1][col + 1] == "*" && board.cells[row - 2][col + 2] == "*" && board.cells[row - 3][col + 3] == "*") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForWinHorizontalFirstPlayer(board: Board): Boolean {
    var isValid = false
    for (row in board.cells) {
        for (col in 0..board.cells[0].size - 4) {
            if (row[col] == "o" && row[col + 1] == "o" && row[col + 2] == "o" && row[col + 3] == "o") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForWinVerticalFirstPlayer(board: Board): Boolean {
    var isValid = false
    for (col in 0 until board.cells[0].size) {
        for (row in 0..board.cells.size - 4) {
            if (board.cells[row][col] == "o" && board.cells[row + 1][col] == "o" && board.cells[row + 2][col] == "o" && board.cells[row + 3][col] == "o") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForWinDiagonalBottomLeftTopRightFirstPlayer(board: Board): Boolean {
    var isValid = false
    for (col in 0..board.cells[0].size - 4) {
        for (row in 0..board.cells.size - 4) {
            if (board.cells[row][col] == "o" && board.cells[row + 1][col + 1] == "o" && board.cells[row + 2][col + 2] == "o" && board.cells[row + 3][col + 3] == "o") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForWinDiagonalTopLeftBottomRightFirstPlayer(board: Board): Boolean {
    var isValid = false
    for (col in 0..board.cells[0].size - 4) {
        for (row in 3 until board.cells.size) {
            if (board.cells[row][col] == "o" && board.cells[row - 1][col + 1] == "o" && board.cells[row - 2][col + 2] == "o" && board.cells[row - 3][col + 3] == "o") {
                isValid = true
            }
        }
    }
    return isValid
}

fun checkForDraw(board: Board): Boolean {
    return board.cells[0].none { it == " " }
}

fun playGameFinal(game: Game) {
    val numberOfGames = setNumberOfGames()
    if (numberOfGames == 1) {
        playSingleGame(game)
    } else {
        playMultipleGames(game, numberOfGames)
    }

}

fun announceGame(game: Game) {
    println("${game.firstPlayer} VS ${game.secondPlayer}")
    println("${game.board.rows} X ${game.board.columns} board")
}


fun playSingleGame(game: Game) {
    announceGame(game)
    println("Single game")
    drawBoard(game.board)
    playGame(game)
}

fun playMultipleGames(game: Game, numberOfGames: Int) {
    announceGame(game)

    var isFirstPlayerFirst = true

    println("Total $numberOfGames games")
    repeat(numberOfGames) { i ->
        println("Game #${i + 1}")
        val board = createBoard(game.board.rows, game.board.columns)
        game.board = board
        drawBoard(board)

        if (i > 0) {
            swapPlayers(game)
            isFirstPlayerFirst = !isFirstPlayerFirst
        }
        playGame(game)
    }
    println("Game over!")
}


fun swapPlayers(game: Game) {
    val helper = game.firstPlayer
    game.firstPlayer = game.secondPlayer
    game.secondPlayer = helper

    val helperSymbol = game.firstPlayerSymbol
    game.firstPlayerSymbol = game.secondPlayerSymbol
    game.secondPlayerSymbol = helperSymbol

}

