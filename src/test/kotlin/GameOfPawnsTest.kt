import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class GameOfPawnsTest {

    @Test
    fun generatedBoardHasCorrectNumberOfRows() {
        assertEquals(8, GameOfPawns.generateBoard().size)
    }

    @Test
    fun generatedBoardSquaresAreCorrectlyInitialised() {
        val board = GameOfPawns.generateBoard()
        board.forEach { row ->
            assertEquals(8, row.size)
            row.forEach { square -> assertEquals('.', square) }
        }
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
