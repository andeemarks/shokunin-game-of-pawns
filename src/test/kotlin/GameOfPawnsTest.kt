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
}
