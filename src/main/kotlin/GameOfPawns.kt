private const val PIECES = "RNBQKBNR"
private const val ROW_SIZE = 8

private val WHITE_PAWNS = "P".repeat(ROW_SIZE)
val WHITE_PIECES = WHITE_PAWNS + PIECES

private val BLACK_PAWNS = WHITE_PAWNS.toLowerCase()
val BLACK_PIECES = BLACK_PAWNS + PIECES.toLowerCase()
private val EMPTY_SQUARES = ".".repeat(32)

class ChessBoard {

    var board: List<List<Char>> = "$WHITE_PIECES$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(ROW_SIZE)

    fun squares(): List<Char> {
        return board.flatten()
    }

    fun whitePromotionRow(): List<Char> {
        return board[0]
    }

    fun blackPromotionRow(): List<Char> {
        return board[7]
    }
}