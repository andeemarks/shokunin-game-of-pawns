import org.junit.Test
import kotlin.test.*

class ChessBoardFormatterTest {

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
