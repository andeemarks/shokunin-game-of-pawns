import org.junit.Test
import kotlin.test.assertEquals

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
}
