import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RankAndFileTest {

    @Test
    fun doesNotDetectDistantPiecesAsNeighbours() {
        assertFalse(RankAndFile(0, 0).isNeighbourOf(RankAndFile(0, 2)))
        assertFalse(RankAndFile(0, 0).isNeighbourOf(RankAndFile(2, 0)))
    }

    @Test
    fun detectAdjacentPiecesAsNeighbours() {
        assertTrue(RankAndFile(0,0).isNeighbourOf(RankAndFile(0,1)))
        assertTrue(RankAndFile(0,0).isNeighbourOf(RankAndFile(1,0)))
        assertTrue(RankAndFile(0,0).isNeighbourOf(RankAndFile(1,1)))
    }
}