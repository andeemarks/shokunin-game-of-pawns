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
    fun populatedBoardCanBeFormattedWithValidFENPiecePlacement() {
        val board = ChessBoard().board
        val fen = ChessBoardFormatter().asFEN(board)
        val (pieces, _) = fen.split(" ")
        assertEquals(8, pieces.split("/").size)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENActiveColor() {
        val board = ChessBoard().board
        val fen = ChessBoardFormatter().asFEN(board)
        val (_, activeColor, _) = fen.split(" ")
        assertTrue(activeColor.contains(Regex("[wb]")), "$activeColor is not 'w' or 'b'")
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoCastlingRights() {
        val board = ChessBoard().board
        val fen = ChessBoardFormatter().asFEN(board)
        val (_, _, castlingAvailability, _) = fen.split(" ")
        assertEquals("-", castlingAvailability)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoEnPassant() {
        val board = ChessBoard().board
        val fen = ChessBoardFormatter().asFEN(board)
        val (_, _, _, enPassant, _) = fen.split(" ")
        assertEquals("-", enPassant)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoHalfmoveClock() {
        val board = ChessBoard().board
        val fen = ChessBoardFormatter().asFEN(board)
        val (_, _, _, _, halfMoveClock) = fen.split(" ")
        assertEquals("0", halfMoveClock)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndFullmoveNumberReset() {
        val board = ChessBoard().board
        val fen = ChessBoardFormatter().asFEN(board)
        val fullmoveNumber = fen.split(" ")[5]
        assertEquals("1", fullmoveNumber)
    }
}
