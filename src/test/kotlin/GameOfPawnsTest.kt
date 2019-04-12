import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

class GameOfPawnsTest {

    @Test
    fun populatedBoardHasCorrectNumberOfRanks() {
        assertEquals(8, ChessBoard().board.size)
    }

    @Test
    fun populatedBoardOnlyHas32Pieces() {
        assertEquals(32, ChessBoard().squares().count { square -> square != '.' })
    }

    @Test
    fun populatedBoardIncludesAllWhitePieces() {
        val board = ChessBoard()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains(WHITE_PIECES.toCharArray().sorted().joinToString("")))
    }

    @Test
    fun populatedBoardIncludesAllBlackPieces() {
        val board = ChessBoard()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains(BLACK_PIECES.toCharArray().sorted().joinToString("")))
    }

    @Test
    fun boardPopulationChangesEachTime() {
        assertNotSame(ChessBoard(), ChessBoard())
        assertNotSame(ChessBoard(), ChessBoard())
        assertNotSame(ChessBoard(), ChessBoard())
        assertNotSame(ChessBoard(), ChessBoard())
        assertNotSame(ChessBoard(), ChessBoard())
    }

    @Test
    fun populatedBoardHasNoWhitePawnsInPromotionRank() {
        val whitePromotionRank = ChessBoard().whitePromotionRank(ChessBoard().board)
        assertFalse(whitePromotionRank.contains('P'), "Found white pawn in promotion square: $whitePromotionRank")
    }

    @Test
    fun populatedBoardHasNoBlackPawnsInPromotionRank() {
        val blackPromotionRank = ChessBoard().blackPromotionRank(ChessBoard().board)
        assertFalse(blackPromotionRank.contains('p'), "Found black pawn in promotion square: $blackPromotionRank")
    }

    @Test
    fun populatedBoardDoesNotPlaceKingsInAdjacentSquares() {
        repeat(20) {
            val board = ChessBoard().board
            val whiteKing: Pair<Int, Int> = ChessBoard().whiteKingPosition(board)
            val blackKing: Pair<Int, Int> = ChessBoard().blackKingPosition(board)

            assertFalse(ChessBoard().areNeighbours(whiteKing, blackKing))
        }
    }

    @Test
    fun boardDoesNotDetectDistantKingsOnSameRankAsNeighbours() {
        val board = ChessBoard().board.toMutableList()
        board[0] = "K.k.....".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = ChessBoard().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = ChessBoard().blackKingPosition(board)

        assertFalse(ChessBoard().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardDoesNotDetectDistantKingsAsNeighbours() {
        val board = ChessBoard().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[2] = "k.......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = ChessBoard().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = ChessBoard().blackKingPosition(board)

        assertFalse(ChessBoard().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsOnTheSameRankAsNeighbours() {
        val board = ChessBoard().board.toMutableList()
        board[0] = "Kk......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = ChessBoard().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = ChessBoard().blackKingPosition(board)

        assertTrue(ChessBoard().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsOnTheSameColumnAsNeighbours() {
        val board = ChessBoard().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[1] = "k.......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = ChessBoard().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = ChessBoard().blackKingPosition(board)

        assertTrue(ChessBoard().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsDiagonallyAsNeighbours() {
        val board = ChessBoard().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[1] = ".k......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = ChessBoard().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = ChessBoard().blackKingPosition(board)

        assertTrue(ChessBoard().areNeighbours(whiteKing, blackKing))
    }
}
