import java.util.*

class GameOfPawns {

    companion object {
        fun generateBoard(): Array<Array<Char>> {
            return Array(ROW_SIZE) { Array(ROW_SIZE) {'.'}}
        }
    }


}

private const val PIECES = "RNBQKBNR"
private const val ROW_SIZE = 8

private const val WHITE_PIECES = PIECES
private val BLACK_PIECES = PIECES.toLowerCase()

private val WHITE_PAWNS = "P".repeat(ROW_SIZE)
private val BLACK_PAWNS = WHITE_PAWNS.toLowerCase()
private val EMPTY_SQUARES = ".".repeat(32)

fun populate():  Array<Array<Char>>  {
    val board = Array(ROW_SIZE) { Array(ROW_SIZE) { '.'} }

    val squares = "$WHITE_PAWNS$WHITE_PIECES$BLACK_PAWNS$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(ROW_SIZE).toTypedArray()

    board[0] = squares[0].toTypedArray()
    board[1] = squares[1].toTypedArray()
    board[2] = squares[2].toTypedArray()
    board[3] = squares[3].toTypedArray()
    board[4] = squares[4].toTypedArray()
    board[5] = squares[5].toTypedArray()
    board[6] = squares[6].toTypedArray()
    board[7] = squares[7].toTypedArray()

    return board
}
