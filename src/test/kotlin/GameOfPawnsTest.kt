import org.junit.Test
import kotlin.test.assertEquals

class GameOfPawnsTest {

    @Test
    fun generatedBoardHasCorrectNumberOfRows() {
        assertEquals(8, GameOfPawns.generateBoard().size)
    }

    @Test
    fun generatedBoardSquaresAreCorrectlyInitialised() {
        val grid = GameOfPawns.generateBoard()
        grid.forEach { row ->
            assertEquals(8, row.size)
            row.forEach { square -> assertEquals('.', square) }
        }
    }

}
