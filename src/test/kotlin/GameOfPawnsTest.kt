import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameOfPawnsTest {
    private val board = ChessBoard().board
    private val fen = ChessBoardFormatter().asFEN(board)
    private val fenElements = fen.split(" ")

    @Test
    fun populatedBoardCanBeFormattedWithValidFENPiecePlacement() {
        val pieces = fenElements[0]
        assertEquals(8, pieces.split("/").size)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENActiveColor() {
        val activeColor = fenElements[1]
        assertTrue(activeColor.contains(Regex("[wb]")), "$activeColor is not 'w' or 'b'")
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoCastlingRights() {
        val castlingAvailability = fenElements[2]
        assertEquals("-", castlingAvailability)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoEnPassant() {
        val enPassant = fenElements[3]
        assertEquals("-", enPassant)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoHalfmoveClock() {
        val halfMoveClock = fenElements[4]
        assertEquals("0", halfMoveClock)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndFullmoveNumberReset() {
        val fullmoveNumber = fenElements[5]
        assertEquals("1", fullmoveNumber)
    }
}
