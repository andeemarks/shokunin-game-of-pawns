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
        var initialPopulation: MutableList<MutableList<Char>> = "$WHITE_PIECES$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(RANK_WIDTH).map { row -> row.toMutableList()}.toMutableList()

        initialPopulation = removeAnyPawnsFromPromotionRanks(initialPopulation)

        return initialPopulation
    }

    private fun removeAnyPawnsFromPromotionRanks(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        var pawnsToMove = whitePawnsInPromotionRank(squares)
        var availableSpots = availableSquaresForWhitePawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: Pair<Int, Int> -> squares[availableSpots[i].first][availableSpots[i].second] = WHITE_PAWN; squares[pawn.first][pawn.second] = EMPTY_SQUARE }

        pawnsToMove = blackPawnsInPromotionRank(squares)
        availableSpots = availableSquaresForBlackPawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: Pair<Int, Int> -> squares[availableSpots[i].first][availableSpots[i].second] = BLACK_PAWN; squares[pawn.first][pawn.second] = EMPTY_SQUARE }

        return squares
    }

    private fun availableSquaresForWhitePawns(board: List<List<Char>>): List<Pair<Int, Int>> = emptySquares(board).filter { square -> square.first != WHITE_PROMOTION_RANK }
    private fun whitePawnsInPromotionRank(board: List<List<Char>>) = whitePromotionRank(board).mapIndexed { index, square -> if (square == WHITE_PAWN) Pair(WHITE_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()
    private fun blackPawnsInPromotionRank(board: List<List<Char>>) = blackPromotionRank(board).mapIndexed { index, square -> if (square == BLACK_PAWN) Pair(BLACK_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()
    private fun availableSquaresForBlackPawns(board: List<List<Char>>) = emptySquares(board).filter { square -> square.first != BLACK_PROMOTION_RANK }

    private fun emptySquares(board: List<List<Char>>) = board.flatten().mapIndexed { index, square -> if (square == EMPTY_SQUARE) Pair(index.div(RANK_WIDTH), index.rem(RANK_WIDTH)) else null }.filterNotNull()

    fun whitePromotionRank(board: List<List<Char>>) = board[WHITE_PROMOTION_RANK]
    fun blackPromotionRank(board: List<List<Char>>)= board[BLACK_PROMOTION_RANK]
    fun squares() = board.flatten()

    fun whiteKingPosition(board: List<List<Char>>): Pair<Int, Int> = Pair(0, 0)
    fun blackKingPosition(board: List<List<Char>>): Pair<Int, Int> = Pair(0, 0)
    fun areNeighbours(whiteKing: Pair<Int, Int>, blackKing: Pair<Int, Int>): Boolean = true
}

