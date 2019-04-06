import org.junit.Test
import kotlin.test.assertEquals
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
    fun populatedBoardHas32Pieces() {
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
}
