import kotlin.math.absoluteValue

private const val RANK_WIDTH = 8
private const val WHITE_PROMOTION_RANK = 0
private const val BLACK_PROMOTION_RANK = 7

object Pieces {
    private const val WHITE_NON_PAWNS = "RNBQKBNR"
    private const val BLACK_NON_PAWNS = "rnbqkbnr"

    const val EMPTY_SQUARE = '.'
    const val WHITE_PAWN = 'P'
    const val BLACK_PAWN = 'p'
    const val BLACK_KING = 'k'
    const val WHITE_KING = 'K'

    val EMPTY_SQUARES = EMPTY_SQUARE.toString().repeat(32)
    val WHITE = WHITE_PAWN.toString().repeat(RANK_WIDTH) + WHITE_NON_PAWNS
    val BLACK = BLACK_PAWN.toString().repeat(RANK_WIDTH) + BLACK_NON_PAWNS
}

class RankAndFile(val rank: Int, val file: Int) {
    fun rowDistanceTo(other: RankAndFile) = (this.rank - other.rank).absoluteValue
    fun columnDistanceTo(other: RankAndFile) = (this.file - other.file).absoluteValue
}

class BoardGenerator {
    var board: List<List<Char>> = populate()

    fun squares() = board.flatten()
    fun whitePromotionRank(board: List<List<Char>>) = board[WHITE_PROMOTION_RANK]

    fun blackPromotionRank(board: List<List<Char>>) = board[BLACK_PROMOTION_RANK]
    fun blackKingPosition(board: List<List<Char>>): RankAndFile = findPositionOfPiece(board, Pieces.BLACK_KING)

    fun whiteKingPosition(board: List<List<Char>>): RankAndFile = findPositionOfPiece(board, Pieces.WHITE_KING)
    fun areNeighbours(whiteKing: RankAndFile, blackKing: RankAndFile): Boolean = (whiteKing.columnDistanceTo(blackKing) <= 1 && whiteKing.rowDistanceTo(blackKing) <= 1)

    private fun populate(): List<List<Char>> {
        var populatedBoard: MutableList<MutableList<Char>> = "${Pieces.WHITE}${Pieces.BLACK}${Pieces.EMPTY_SQUARES}".toMutableList().shuffled().chunked(RANK_WIDTH).map { row -> row.toMutableList() }.toMutableList()

        populatedBoard = preventPromoPawns(populatedBoard)
        populatedBoard = preventNeighbouringKings(populatedBoard)

        return populatedBoard
    }

    private fun preventNeighbouringKings(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        val whiteKingPosition = whiteKingPosition(squares)
        val blackKingPosition = blackKingPosition(squares)

        if (areNeighbours(whiteKingPosition, blackKingPosition)) {
            val newWhiteKingPosition = emptySquares(squares).filter { square -> !areNeighbours(blackKingPosition, square) }.shuffled().first()
            switchWithEmptySquare(squares, newWhiteKingPosition, whiteKingPosition, Pieces.WHITE_KING)
        }

        return squares
    }

    private fun switchWithEmptySquare(squares: MutableList<MutableList<Char>>, position1: RankAndFile, position2: RankAndFile, position2Occupant: Char) {
        squares[position1.rank][position1.file] = position2Occupant
        squares[position2.rank][position2.file] = Pieces.EMPTY_SQUARE
    }

    private fun preventPromoPawns(squares: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
        removeWhitePawnsFromPromotionRank(squares)
        removeBlackPawnsFromPromotionRank(squares)

        return squares
    }

    private fun removeBlackPawnsFromPromotionRank(squares: MutableList<MutableList<Char>>) {
        val pawnsToMove = blackPawnsInPromotionRank(squares)
        val availableSpots = availableSquaresForBlackPawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: RankAndFile -> switchWithEmptySquare(squares, availableSpots[i], pawn, Pieces.BLACK_PAWN) }
    }

    private fun removeWhitePawnsFromPromotionRank(squares: MutableList<MutableList<Char>>) {
        val pawnsToMove = whitePawnsInPromotionRank(squares)
        val availableSpots = availableSquaresForWhitePawns(squares).shuffled()

        pawnsToMove.forEachIndexed { i: Int, pawn: RankAndFile -> switchWithEmptySquare(squares, availableSpots[i], pawn, Pieces.WHITE_PAWN) }
    }

    private fun availableSquaresForWhitePawns(board: List<List<Char>>): List<RankAndFile> = emptySquares(board).filter { square -> square.rank != WHITE_PROMOTION_RANK }
    private fun whitePawnsInPromotionRank(board: List<List<Char>>) = whitePromotionRank(board).mapIndexed { index, square -> if (square.hasWhitePawn()) RankAndFile(WHITE_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()
    private fun blackPawnsInPromotionRank(board: List<List<Char>>) = blackPromotionRank(board).mapIndexed { index, square -> if (square.hasBlackPawn()) RankAndFile(BLACK_PROMOTION_RANK, index.rem(RANK_WIDTH)) else null }.filterNotNull()

    private fun availableSquaresForBlackPawns(board: List<List<Char>>) = emptySquares(board).filter { square -> square.rank != BLACK_PROMOTION_RANK }
    private fun findPositionOfPiece(board: List<List<Char>>, piece: Char) = board.flatten().mapIndexed { i, square -> if (square.contains(piece)) RankAndFile(i.div(RANK_WIDTH), i.rem(RANK_WIDTH)) else null }.filterNotNull().first()

    private fun emptySquares(board: List<List<Char>>) = board.flatten().mapIndexed { index, square -> if (square.isUnoccupied()) RankAndFile(index.div(RANK_WIDTH), index.rem(RANK_WIDTH)) else null }.filterNotNull()
}

private fun Char.hasBlackPawn(): Boolean = this == Pieces.BLACK_PAWN
private fun Char.hasWhitePawn(): Boolean = this == Pieces.WHITE_PAWN
private fun Char.contains(piece: Char): Boolean = this == piece
private fun Char.isUnoccupied(): Boolean = this == Pieces.EMPTY_SQUARE

