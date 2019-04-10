import org.junit.Test
import kotlin.test.*

class GameOfPawnsTest {
    private val board = ChessBoard().board
    private val fen = ChessBoardFormatter().asFEN(board)

    @Test
    fun populatedBoardCanBeFormattedWithValidFENPiecePlacement() {
        val (pieces, _) = fen.split(" ")
        assertEquals(8, pieces.split("/").size)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENActiveColor() {
        val (_, activeColor, _) = fen.split(" ")
        assertTrue(activeColor.contains(Regex("[wb]")), "$activeColor is not 'w' or 'b'")
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoCastlingRights() {
        val (_, _, castlingAvailability, _) = fen.split(" ")
        assertEquals("-", castlingAvailability)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoEnPassant() {
        val (_, _, _, enPassant, _) = fen.split(" ")
        assertEquals("-", enPassant)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndNoHalfmoveClock() {
        val (_, _, _, _, halfMoveClock) = fen.split(" ")
        assertEquals("0", halfMoveClock)
    }

    @Test
    fun populatedBoardCanBeFormattedWithValidFENAndFullmoveNumberReset() {
        val fullmoveNumber = fen.split(" ")[5]
        assertEquals("1", fullmoveNumber)
    }
}
