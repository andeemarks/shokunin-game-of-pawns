private const val PIECES = "RNBQKBNR"
private const val ROW_SIZE = 8

private const val WHITE_PAWN = 'P'
private val WHITE_PAWNS = WHITE_PAWN.toString().repeat(ROW_SIZE)
val WHITE_PIECES = WHITE_PAWNS + PIECES

private val BLACK_PAWN = WHITE_PAWN.toLowerCase()
private val BLACK_PAWNS = BLACK_PAWN.toString().repeat(ROW_SIZE)
val BLACK_PIECES = BLACK_PAWNS + PIECES.toLowerCase()
private const val EMPTY_SQUARE = '.'
private val EMPTY_SQUARES = EMPTY_SQUARE.toString().repeat(32)

class ChessBoard {

    var board: List<List<Char>> = populate()

    private fun populate(): List<List<Char>> {
        var initialPopulation = "$WHITE_PIECES$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(ROW_SIZE).toMutableList()

//        initialPopulation = removeAnyPawnsFromPromotionSquares(initialPopulation)

        return initialPopulation
    }

    private fun removeAnyPawnsFromPromotionSquares(squares: MutableList<List<Char>>): MutableList<List<Char>> {
        squares[0] = squares[0].map { square: Char -> if (square == WHITE_PAWN) EMPTY_SQUARE else square }
        squares[7] = squares[7].map { square: Char -> if (square == BLACK_PAWN) EMPTY_SQUARE else square }

        return squares
    }

    private fun availableSquaresForWhitePawns(board: List<List<Char>>) : List<Pair<Int, Int>> {
        return emptySquares(board).filter { square -> square.first != 0 }
    }

    private fun availableSquaresForBlackPawns(board: List<List<Char>>) : List<Pair<Int, Int>> {
        return emptySquares(board).filter { square -> square.first != 7 }
    }

    private fun emptySquares(board: List<List<Char>>): List<Pair<Int, Int>> {
        return board.flatten().mapIndexed { index, square -> if (square == EMPTY_SQUARE) Pair(index.div(ROW_SIZE), index.rem(ROW_SIZE)) else null }.filterNotNull()
    }

    fun squares(): List<Char> {
        return board.flatten()
    }

    fun whitePromotionSquares(): List<Char> {
        return board[0]
    }

    fun blackPromotionSquares(): List<Char> {
        return board[7]
    }
}