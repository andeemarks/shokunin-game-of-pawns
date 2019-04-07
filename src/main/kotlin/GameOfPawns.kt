private const val PIECES = "RNBQKBNR"
private const val ROW_SIZE = 8

private const val WHITE_PIECES = PIECES
private val BLACK_PIECES = PIECES.toLowerCase()

private val WHITE_PAWNS = "P".repeat(ROW_SIZE)
private val BLACK_PAWNS = WHITE_PAWNS.toLowerCase()
private val EMPTY_SQUARES = ".".repeat(32)

class ChessBoard {

    var board: List<List<Char>> = "$WHITE_PAWNS$WHITE_PIECES$BLACK_PAWNS$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(ROW_SIZE)

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