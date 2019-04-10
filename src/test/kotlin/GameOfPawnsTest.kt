import org.junit.Test
import kotlin.test.*

class GameOfPawnsTest {
    private val board = ChessBoard().board
    private val fen = ChessBoardFormatter().asFEN(board)

    @Test
    fun populatedBoardCanBeFormattedWithValidFENPiecePlacement() {
        val pieces = fen.split(" ")[0]
        assertEquals(8, pieces.split("/").size)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENActiveColor() {
        val activeColor = fen.split(" ")[1]
        assertTrue(activeColor.contains(Regex("[wb]")), "$activeColor is not 'w' or 'b'")
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoCastlingRights() {
        val castlingAvailability = fen.split(" ")[2]
        assertEquals("-", castlingAvailability)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoEnPassant() {
        val enPassant = fen.split(" ")[3]
        assertEquals("-", enPassant)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoHalfmoveClock() {
        val halfMoveClock = fen.split(" ")[4]
        assertEquals("0", halfMoveClock)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndFullmoveNumberReset() {
        val fullmoveNumber = fen.split(" ")[5]
        assertEquals("1", fullmoveNumber)
    }
}
