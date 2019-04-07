import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

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
        val squares1 = populate().flatten().joinToString("")
        val squares2 = populate().flatten().joinToString("")

        assertNotEquals(squares1, squares2)
    }
}
