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
    fun boardOnlyHas32Pieces() {
        repeat(TEST_EXECUTION_COUNT) {
            assertEquals(32, BoardGenerator().squares().count { square -> square != '.' })
        }
    }

    @Test
    fun boardIncludesAllWhitePieces() {
        repeat(TEST_EXECUTION_COUNT) {
            val board = BoardGenerator()
            val squares = board.squares().sorted().joinToString("")

            assertTrue(squares.contains(Pieces.WHITE.toCharArray().sorted().joinToString("")))
        }
    }

    @Test
    fun boardIncludesAllBlackPieces() {
        repeat(TEST_EXECUTION_COUNT) {
            val board = BoardGenerator()
            val squares = board.squares().sorted().joinToString("")

            assertTrue(squares.contains(Pieces.BLACK.toCharArray().sorted().joinToString("")))
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
            assertFalse(BoardGenerator().areNeighbours(whiteKing, blackKing), "Kings are adjacent at $whiteKing and $blackKing")
        }
    }

    @Test
    fun boardDoesNotDetectDistantKingsOnSameRankAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "K.k.....".toCharArray().toList()
        val whiteKing = BoardGenerator().whiteKingPosition(board)
        val blackKing = BoardGenerator().blackKingPosition(board)

        assertFalse(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardDoesNotDetectDistantKingsAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[2] = "k.......".toCharArray().toList()
        val whiteKing  = BoardGenerator().whiteKingPosition(board)
        val blackKing  = BoardGenerator().blackKingPosition(board)

        assertFalse(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsOnTheSameRankAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "Kk......".toCharArray().toList()
        val whiteKing = BoardGenerator().whiteKingPosition(board)
        val blackKing = BoardGenerator().blackKingPosition(board)

        assertTrue(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsOnTheSameColumnAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[1] = "k.......".toCharArray().toList()
        val whiteKing = BoardGenerator().whiteKingPosition(board)
        val blackKing = BoardGenerator().blackKingPosition(board)

        assertTrue(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsDiagonallyAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[1] = ".k......".toCharArray().toList()
        val whiteKing = BoardGenerator().whiteKingPosition(board)
        val blackKing = BoardGenerator().blackKingPosition(board)

        assertTrue(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }
}
