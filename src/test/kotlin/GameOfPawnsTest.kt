import org.junit.Test
import org.junit.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameOfPawnsTest {

    @Test
    fun generatedGridHasCorrectNumberOfRows() {
        assertEquals(8, GameOfPawns.generateGrid().size)
    }

}
