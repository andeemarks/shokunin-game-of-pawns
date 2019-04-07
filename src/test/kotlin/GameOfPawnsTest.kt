import org.junit.Test
import kotlin.test.*

class GameOfPawnsTest {

    @Test
    fun populatedBoardHasCorrectNumberOfRows() {
        assertEquals(8, ChessBoard().board.size)
    }

    @Test
    fun populatedBoardOnlyHas32Pieces() {
        val board = ChessBoard()
        assertEquals(32, board.squares().count { square -> square != '.' })
    }

    @Test
    fun populatedBoardIncludesAllWhitePieces() {
        val board = ChessBoard()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains("BBKNNPPPPPPPPQRR"))
    }

    @Test
    fun populatedBoardIncludesAllBlackPieces() {
        val board = ChessBoard()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains("bbknnppppppppqrr"))
    }

    @Test
    fun boardPopulationChangesEachTime() {
        assertNotSame(ChessBoard(), ChessBoard())
    }

    @Test
    fun populatedBoardHasNoPawnsInPromotionSquare() {
        val whitePromotionRow = ChessBoard().whitePromotionRow()
        assertFalse(whitePromotionRow.contains('P'), "Found white pawn in promotion square: $whitePromotionRow")
        val blackPromotionRow = ChessBoard().blackPromotionRow()
        assertFalse(blackPromotionRow.contains('p'), "Found black pawn in promotion square: $blackPromotionRow")

    }
}
