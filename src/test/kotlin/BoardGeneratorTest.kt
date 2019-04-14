import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

class BoardGeneratorTest {

    @Test
    fun populatedBoardHasCorrectNumberOfRanks() {
        assertEquals(8, BoardGenerator().board.size)
    }

    @Test
    fun populatedBoardOnlyHas32Pieces() {
        assertEquals(32, BoardGenerator().squares().count { square -> square != '.' })
    }

    @Test
    fun populatedBoardIncludesAllWhitePieces() {
        val board = BoardGenerator()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains(WHITE_PIECES.toCharArray().sorted().joinToString("")))
    }

    @Test
    fun populatedBoardIncludesAllBlackPieces() {
        val board = BoardGenerator()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains(BLACK_PIECES.toCharArray().sorted().joinToString("")))
    }

    @Test
    fun boardPopulationChangesEachTime() {
        assertNotSame(BoardGenerator(), BoardGenerator())
        assertNotSame(BoardGenerator(), BoardGenerator())
        assertNotSame(BoardGenerator(), BoardGenerator())
        assertNotSame(BoardGenerator(), BoardGenerator())
        assertNotSame(BoardGenerator(), BoardGenerator())
    }

    @Test
    fun populatedBoardHasNoWhitePawnsInPromotionRank() {
        val whitePromotionRank = BoardGenerator().whitePromotionRank(BoardGenerator().board)
        assertFalse(whitePromotionRank.contains('P'), "Found white pawn in promotion square: $whitePromotionRank")
    }

    @Test
    fun populatedBoardHasNoBlackPawnsInPromotionRank() {
        val blackPromotionRank = BoardGenerator().blackPromotionRank(BoardGenerator().board)
        assertFalse(blackPromotionRank.contains('p'), "Found black pawn in promotion square: $blackPromotionRank")
    }

    @Test
    fun populatedBoardDoesNotPlaceKingsInAdjacentSquares() {
        repeat(50) {
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
        val whiteKing: Pair<Int, Int> = BoardGenerator().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = BoardGenerator().blackKingPosition(board)

        assertFalse(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardDoesNotDetectDistantKingsAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[2] = "k.......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = BoardGenerator().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = BoardGenerator().blackKingPosition(board)

        assertFalse(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsOnTheSameRankAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "Kk......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = BoardGenerator().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = BoardGenerator().blackKingPosition(board)

        assertTrue(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsOnTheSameColumnAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[1] = "k.......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = BoardGenerator().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = BoardGenerator().blackKingPosition(board)

        assertTrue(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }

    @Test
    fun boardCanDetectAdjacentKingsDiagonallyAsNeighbours() {
        val board = BoardGenerator().board.toMutableList()
        board[0] = "K.......".toCharArray().toList()
        board[1] = ".k......".toCharArray().toList()
        val whiteKing: Pair<Int, Int> = BoardGenerator().whiteKingPosition(board)
        val blackKing: Pair<Int, Int> = BoardGenerator().blackKingPosition(board)

        assertTrue(BoardGenerator().areNeighbours(whiteKing, blackKing))
    }
}
