import kotlin.math.absoluteValue

private const val PIECES = "RNBQKBNR"
private const val RANK_WIDTH = 8

const val WHITE_PAWN = 'P'
val WHITE_PIECES = WHITE_PAWN.toString().repeat(RANK_WIDTH) + PIECES

private const val BLACK_PAWN = 'p'
val BLACK_PIECES = BLACK_PAWN.toString().repeat(RANK_WIDTH) + PIECES.toLowerCase()
const val EMPTY_SQUARE = '.'
private val EMPTY_SQUARES = EMPTY_SQUARE.toString().repeat(32)

private const val WHITE_PROMOTION_RANK = 0
private const val BLACK_PROMOTION_RANK = 7

class ChessBoard {

    var board: List<List<Char>> = populate()

    private fun populate(): List<List<Char>> {
        var initialPopulation: MutableList<MutableList<Char>> = "$WHITE_PIECES$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(RANK_WIDTH).map { row -> row.toMutableList() }.toMutableList()

        initialPopulation = removeAnyPawnsFromPromotionRanks(initialPopulation)
        initialPopulation = preventKingsFromBeingNeighbours(initialPopulation)

        return initialPopulation
    }

    private fun preventKingsFromBeingNeighbours(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        val whiteKing: Pair<Int, Int> = whiteKingPosition(squares)
        val blackKing: Pair<Int, Int> = blackKingPosition(squares)

        if (areNeighbours(whiteKing, blackKing)) {
            val newWhiteKingPosition = emptySquares(squares).filter { square -> !areNeighbours(blackKing, square)}.shuffled().first()
            switchWithEmptySquare(squares, newWhiteKingPosition, whiteKing, 'K')
        }

        return squares
    }

    private fun switchWithEmptySquare(squares: MutableList<MutableList<Char>>, position1: Pair<Int, Int>, position2: Pair<Int, Int>, position2Occupant: Char) {
        squares[position1.first][position1.second] = position2Occupant
        squares[position2.first][position2.second] = EMPTY_SQUARE
    }

    private fun removeAnyPawnsFromPromotionRanks(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        removeWhitePawnsFromPromotionRank(squares)
        removeBlackPawnsFromPromotionRank(squares)

        return squares
    }

    private fun removeBlackPawnsFromPromotionRank(squares: MutableList<MutableList<Char>>) {
        val pawnsToMove = blackPawnsInPromotionRank(squares)
        val availableSpots = availableSquaresForBlackPawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: Pair<Int, Int> -> switchWithEmptySquare(squares, availableSpots[i], pawn, BLACK_PAWN) }
    }

    private fun removeWhitePawnsFromPromotionRank(squares: MutableList<MutableList<Char>>) {
        val pawnsToMove = whitePawnsInPromotionRank(squares)
        val availableSpots = availableSquaresForWhitePawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: Pair<Int, Int> -> switchWithEmptySquare(squares, availableSpots[i], pawn, WHITE_PAWN) }
    }

    private fun availableSquaresForWhitePawns(board: List<List<Char>>): List<Pair<Int, Int>> = emptySquares(board).filter { square -> square.first != WHITE_PROMOTION_RANK }
    private fun whitePawnsInPromotionRank(board: List<List<Char>>) = whitePromotionRank(board).mapIndexed { index, square -> if (square == WHITE_PAWN) Pair(WHITE_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()
    private fun blackPawnsInPromotionRank(board: List<List<Char>>) = blackPromotionRank(board).mapIndexed { index, square -> if (square == BLACK_PAWN) Pair(BLACK_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()
    private fun availableSquaresForBlackPawns(board: List<List<Char>>) = emptySquares(board).filter { square -> square.first != BLACK_PROMOTION_RANK }

    private fun emptySquares(board: List<List<Char>>) = board.flatten().mapIndexed { index, square -> if (square == EMPTY_SQUARE) Pair(index.div(RANK_WIDTH), index.rem(RANK_WIDTH)) else null }.filterNotNull()

    fun whitePromotionRank(board: List<List<Char>>) = board[WHITE_PROMOTION_RANK]
    fun blackPromotionRank(board: List<List<Char>>) = board[BLACK_PROMOTION_RANK]
    fun squares() = board.flatten()

    fun blackKingPosition(board: List<List<Char>>): Pair<Int, Int> = findPositionOfPiece(board, 'k')
    fun whiteKingPosition(board: List<List<Char>>): Pair<Int, Int> = findPositionOfPiece(board, 'K')

    private fun findPositionOfPiece(board: List<List<Char>>, piece: Char) = board.flatten().mapIndexed { i, square -> if (square == piece) Pair(i.div(RANK_WIDTH), i.rem(RANK_WIDTH)) else null }.filterNotNull().first()

    fun areNeighbours(whiteKing: Pair<Int, Int>, blackKing: Pair<Int, Int>): Boolean = (columnDistanceBetween(whiteKing, blackKing) <= 1 && rowDistanceBetween(whiteKing, blackKing) <= 1)

    private fun rowDistanceBetween(whiteKing: Pair<Int, Int>, blackKing: Pair<Int, Int>) = (whiteKing.second - blackKing.second).absoluteValue
    private fun columnDistanceBetween(whiteKing: Pair<Int, Int>, blackKing: Pair<Int, Int>) = (whiteKing.first - blackKing.first).absoluteValue
}

