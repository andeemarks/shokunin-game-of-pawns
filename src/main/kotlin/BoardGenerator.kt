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

private const val BLACK_KING = 'k'
private const val WHITE_KING = 'K'

class RankAndFile(val rank: Int, val file: Int)

class BoardGenerator {
    var board: List<List<Char>> = populate()

    fun squares() = board.flatten()
    fun whitePromotionRank(board: List<List<Char>>) = board[WHITE_PROMOTION_RANK]

    fun blackPromotionRank(board: List<List<Char>>) = board[BLACK_PROMOTION_RANK]
    fun blackKingPosition(board: List<List<Char>>): RankAndFile = findPositionOfPiece(board, BLACK_KING)

    fun whiteKingPosition(board: List<List<Char>>): RankAndFile = findPositionOfPiece(board, WHITE_KING)
    fun areNeighbours(whiteKing: RankAndFile, blackKing: RankAndFile): Boolean = (columnDistanceBetween(whiteKing, blackKing) <= 1 && rowDistanceBetween(whiteKing, blackKing) <= 1)

    private fun populate(): List<List<Char>> {
        var populatedBoard: MutableList<MutableList<Char>> = "$WHITE_PIECES$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(RANK_WIDTH).map { row -> row.toMutableList() }.toMutableList()

        populatedBoard = preventPromoPawns(populatedBoard)
        populatedBoard = preventNeighbouringKings(populatedBoard)

        return populatedBoard
    }

    private fun preventNeighbouringKings(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        val whiteKingPosition= whiteKingPosition(squares)
        val blackKingPosition= blackKingPosition(squares)

        if (areNeighbours(whiteKingPosition, blackKingPosition)) {
            val newWhiteKingPosition = emptySquares(squares).filter { square -> !areNeighbours(blackKingPosition, square) }.shuffled().first()
            switchWithEmptySquare(squares, newWhiteKingPosition, whiteKingPosition, WHITE_KING)
        }

        return squares
    }

    private fun switchWithEmptySquare(squares: MutableList<MutableList<Char>>, position1: RankAndFile, position2: RankAndFile, position2Occupant: Char) {
        squares[position1.rank][position1.file] = position2Occupant
        squares[position2.rank][position2.file] = EMPTY_SQUARE
    }

    private fun preventPromoPawns(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        removeWhitePawnsFromPromotionRank(squares)
        removeBlackPawnsFromPromotionRank(squares)

        return squares
    }

    private fun removeBlackPawnsFromPromotionRank(squares: MutableList<MutableList<Char>>) {
        val pawnsToMove = blackPawnsInPromotionRank(squares)
        val availableSpots = availableSquaresForBlackPawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: RankAndFile -> switchWithEmptySquare(squares, availableSpots[i], pawn, BLACK_PAWN) }
    }

    private fun removeWhitePawnsFromPromotionRank(squares: MutableList<MutableList<Char>>) {
        val pawnsToMove = whitePawnsInPromotionRank(squares)
        val availableSpots = availableSquaresForWhitePawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: RankAndFile -> switchWithEmptySquare(squares, availableSpots[i], pawn, WHITE_PAWN) }
    }
    private fun availableSquaresForWhitePawns(board: List<List<Char>>): List<RankAndFile> = emptySquares(board).filter { square -> square.rank != WHITE_PROMOTION_RANK }
    private fun whitePawnsInPromotionRank(board: List<List<Char>>) = whitePromotionRank(board).mapIndexed { index, square -> if (square == WHITE_PAWN) RankAndFile(WHITE_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()
    private fun blackPawnsInPromotionRank(board: List<List<Char>>) = blackPromotionRank(board).mapIndexed { index, square -> if (square == BLACK_PAWN) RankAndFile(BLACK_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()

    private fun availableSquaresForBlackPawns(board: List<List<Char>>) = emptySquares(board).filter { square -> square.rank != BLACK_PROMOTION_RANK }
    private fun findPositionOfPiece(board: List<List<Char>>, piece: Char) = board.flatten().mapIndexed { i, square -> if (square == piece) RankAndFile(i.div(RANK_WIDTH), i.rem(RANK_WIDTH)) else null }.filterNotNull().first()

    private fun emptySquares(board: List<List<Char>>) = board.flatten().mapIndexed { index, square -> if (square == EMPTY_SQUARE) RankAndFile(index.div(RANK_WIDTH), index.rem(RANK_WIDTH)) else null }.filterNotNull()
    private fun rowDistanceBetween(whiteKing: RankAndFile, blackKing: RankAndFile) = (whiteKing.rank - blackKing.rank).absoluteValue

    private fun columnDistanceBetween(whiteKing: RankAndFile, blackKing: RankAndFile) = (whiteKing.file - blackKing.file).absoluteValue
}

