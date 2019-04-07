import org.junit.Test
import kotlin.test.*

class GameOfPawnsTest {

    @Test
    fun populatedBoardHasCorrectNumberOfRows() {
        assertEquals(8, populate().size)
    }

    @Test
    fun populatedBoardOnlyHas32Pieces() {
        val board = populate()
        assertEquals(32, board.flatten().count { square -> square != '.' })
    }

    @Test
    fun populatedBoardIncludesAllWhitePieces() {
        val board = populate()
        val squares = board.flatten().sorted().joinToString("")

        assertTrue(squares.contains("BBKNNPPPPPPPPQRR"))
    }

    @Test
    fun populatedBoardIncludesAllBlackPieces() {
        val board = populate()
        val squares = board.flatten().sorted().joinToString("")

        assertTrue(squares.contains("bbknnppppppppqrr"))
    }

    @Test
    fun boardPopulationChangesEachTime() {
        assertNotSame(populate(), populate())
    }

    @Test
    fun populatedBoardHasNoPawnsInPromotionSquare() {
        val whitePromotionSquare = populate()[0]
        assertFalse(whitePromotionSquare.contains('P'), "Found white pawn in promotion square: $whitePromotionSquare")
        val blackPromotionSquare = populate()[7]
        assertFalse(blackPromotionSquare.contains('p'), "Found black pawn in promotion square: $blackPromotionSquare")

    }
}
