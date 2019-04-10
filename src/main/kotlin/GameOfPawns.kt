private const val PIECES = "RNBQKBNR"
private const val ROW_SIZE = 8

private const val WHITE_PAWN = 'P'
val WHITE_PIECES = WHITE_PAWN.toString().repeat(ROW_SIZE) + PIECES

private const val BLACK_PAWN = 'p'
val BLACK_PIECES = BLACK_PAWN.toString().repeat(ROW_SIZE) + PIECES.toLowerCase()
private const val EMPTY_SQUARE = '.'
private val EMPTY_SQUARES = EMPTY_SQUARE.toString().repeat(32)

private const val WHITE_PROMOTION_ROW = 0
private const val BLACK_PROMOTION_ROW = 7

class ChessBoard {

    var board: List<List<Char>> = populate()

    private fun populate(): List<List<Char>> {
        var initialPopulation: MutableList<MutableList<Char>> = "$WHITE_PIECES$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(ROW_SIZE).map { row -> row.toMutableList()}.toMutableList()

        initialPopulation = removeAnyPawnsFromPromotionSquares(initialPopulation)

        return initialPopulation
    }

    private fun removeAnyPawnsFromPromotionSquares(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        var pawnsToMove = whitePawnsInPromotionSquares(squares)
        var availableSpots = availableSquaresForWhitePawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: Pair<Int, Int> -> squares[availableSpots[i].first][availableSpots[i].second] = WHITE_PAWN; squares[pawn.first][pawn.second] = EMPTY_SQUARE }

        pawnsToMove = blackPawnsInPromotionSquares(squares)
        availableSpots = availableSquaresForBlackPawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: Pair<Int, Int> -> squares[availableSpots[i].first][availableSpots[i].second] = BLACK_PAWN; squares[pawn.first][pawn.second] = EMPTY_SQUARE }

        return squares
    }

    private fun availableSquaresForWhitePawns(board: List<List<Char>>) : List<Pair<Int, Int>> {
        return emptySquares(board).filter { square -> square.first != WHITE_PROMOTION_ROW }
    }

    private fun whitePawnsInPromotionSquares(board: List<List<Char>>) : List<Pair<Int, Int>> {
        return whitePromotionSquares(board).mapIndexed { index, square -> if (square == WHITE_PAWN) Pair(WHITE_PROMOTION_ROW, index.rem(ROW_SIZE)) else null }.filterNotNull()
    }

    private fun blackPawnsInPromotionSquares(board: List<List<Char>>) : List<Pair<Int, Int>> {
        return blackPromotionSquares(board).mapIndexed { index, square -> if (square == BLACK_PAWN) Pair(BLACK_PROMOTION_ROW, index.rem(ROW_SIZE)) else null }.filterNotNull()
    }

    private fun availableSquaresForBlackPawns(board: List<List<Char>>) : List<Pair<Int, Int>> {
        return emptySquares(board).filter { square -> square.first != BLACK_PROMOTION_ROW }
    }

    private fun emptySquares(board: List<List<Char>>): List<Pair<Int, Int>> {
        return board.flatten().mapIndexed { index, square -> if (square == EMPTY_SQUARE) Pair(index.div(ROW_SIZE), index.rem(ROW_SIZE)) else null }.filterNotNull()
    }

    fun squares(): List<Char> {
        return board.flatten()
    }

    fun asFEN(board: List<List<Char>>): String {
        return "a b c d e f"
    }

    fun whitePromotionSquares(board: List<List<Char>>): List<Char> {
        return board[WHITE_PROMOTION_ROW]
    }

    fun blackPromotionSquares(board: List<List<Char>>): List<Char> {
        return board[BLACK_PROMOTION_ROW]
    }
}