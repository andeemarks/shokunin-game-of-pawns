import org.junit.Test
import kotlin.test.*

class GameOfPawnsTest {

    @Test
    fun populatedBoardHasCorrectNumberOfRows() {
        assertEquals(8, ChessBoard().board.size)
    }

    @Test
    fun populatedBoardOnlyHas32Pieces() {
        val board = ChessBoard()
        assertEquals(32, board.squares().count { square -> square != '.' })
    }

    @Test
    fun populatedBoardIncludesAllWhitePieces() {
        val board = ChessBoard()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains(WHITE_PIECES.toCharArray().sorted().joinToString("")))
    }

    @Test
    fun populatedBoardIncludesAllBlackPieces() {
        val board = ChessBoard()
        val squares = board.squares().sorted().joinToString("")

        assertTrue(squares.contains(BLACK_PIECES.toCharArray().sorted().joinToString("")))
    }

    @Test
    fun boardPopulationChangesEachTime() {
        assertNotSame(ChessBoard(), ChessBoard())
    }

    @Test
    fun populatedBoardHasNoWhitePawnsInPromotionSquare() {
        val whitePromotionRow = ChessBoard().whitePromotionSquares(ChessBoard().board)
        assertFalse(whitePromotionRow.contains('P'), "Found white pawn in promotion square: $whitePromotionRow")

    }

    @Test
    fun populatedBoardHasNoBlackPawnsInPromotionSquare() {
        val blackPromotionRow = ChessBoard().blackPromotionSquares(ChessBoard().board)
        assertFalse(blackPromotionRow.contains('p'), "Found black pawn in promotion square: $blackPromotionRow")

    }

    @Test
    fun populatedBoardCanBeFormattedAsValidFEN() {
        val board = ChessBoard().board
        val fen = ChessBoard().asFEN(board)
        assertNotNull(fen)
        var elements = fen.split(" ")
        assertEquals(6, elements.size)

    }
}
