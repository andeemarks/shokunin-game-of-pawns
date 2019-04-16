import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

private const val TEST_EXECUTION_COUNT = 50

class BoardGeneratorTest {

    @Test
    fun boardHasCorrectNumberOfRanks() {
        assertEquals(8, BoardGenerator().board.size)
    }

    @Test
    fun boardIncludesAllExpectedPieces() {
        repeat(TEST_EXECUTION_COUNT) {
            val squares = BoardGenerator().squares()
            assertEquals(32, squares.count { square -> square != '.' }, "$squares not not contain 32 empty spaces")
            assertTrue(squares.containsAll(Pieces.WHITE.toList()), "$squares not not contain ${Pieces.WHITE}")
            assertTrue(squares.containsAll(Pieces.BLACK.toList()), "$squares not not contain ${Pieces.BLACK}")
        }
    }

    @Test
    fun boardPopulationChangesEachTime() {
        repeat(TEST_EXECUTION_COUNT) {
            assertNotSame(BoardGenerator(), BoardGenerator())
        }
    }

    @Test
    fun boardHasNoWhitePawnsInPromotionRank() {
        repeat(TEST_EXECUTION_COUNT) {
            val whitePromotionRank = BoardGenerator().whitePromotionRank(BoardGenerator().board)

            assertFalse(whitePromotionRank.contains('P'), "Found white pawn in promotion square: $whitePromotionRank")
        }
    }

    @Test
    fun boardHasNoBlackPawnsInPromotionRank() {
        repeat(TEST_EXECUTION_COUNT) {
            val blackPromotionRank = BoardGenerator().blackPromotionRank(BoardGenerator().board)

            assertFalse(blackPromotionRank.contains('p'), "Found black pawn in promotion square: $blackPromotionRank")
        }
    }

    @Test
    fun boardDoesNotPlaceKingsInAdjacentSquares() {
        repeat(TEST_EXECUTION_COUNT) {
            val board = BoardGenerator().board

            val whiteKing = BoardGenerator().whiteKingPosition(board)
            val blackKing = BoardGenerator().blackKingPosition(board)

            assertFalse(whiteKing.isNeighbourOf(blackKing), "Kings are adjacent at $whiteKing and $blackKing")
        }
    }
}
