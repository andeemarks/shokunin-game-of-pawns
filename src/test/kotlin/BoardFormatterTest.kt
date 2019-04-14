import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoardFormatterTest {
    private val board = BoardGenerator().board

    private fun emptyRank() = EMPTY_SQUARE.toString().repeat(8).toCharArray().toList()

    @Test
    fun boardCanBeGridFormattedWithCorrectNumberOfRanks() {
        val grid = BoardFormatter().asGrid(board)

        assertEquals(8, grid.split("\n").size)
    }

    @Test
    fun boardCanBeGridFormattedShowingAllSquares() {
        val grid = BoardFormatter().asGrid(board)
        var ranks = grid.split("\n")

        repeat(8) { rank -> assertEquals(board[rank].joinToString(""), ranks[rank]) }
    }

    @Test
    fun boardCanBeFENFormattedWithNumberOfRanks() {
        val fen = BoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals(8, ranks.split("/").size)
    }

    @Test
    fun boardCanBeFENFormattedWithEmptyRank() {
        val board = board.toMutableList()
        board[0] = emptyRank()
        board[3] = emptyRank()
        val fen = BoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("8", ranks.split("/")[0])
        assertEquals("8", ranks.split("/")[3])
    }

    @Test
    fun boardCanBeFENFormattedWithConsecutiveEmptySquares() {
        val board = board.toMutableList()
        board[0] = ".Pp..Kq.".toCharArray().toList()
        val fen = BoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("1Pp2Kq1", ranks.split("/")[0])
    }

    @Test
    fun boardCanBeFENFormattedWithRankOfPawns() {
        val board = board.toMutableList()
        board[0] = WHITE_PAWN.toString().repeat(8).toCharArray().toList()
        val fen = BoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("PPPPPPPP", ranks.split("/")[0])
    }

    @Test
    fun boardCanBeFENFormattedWithRankOfMixedPieces() {
        val board = board.toMutableList()
        board[0] = ".PpR.Kq.".toCharArray().toList()
        val fen = BoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("1PpR1Kq1", ranks.split("/")[0])
    }

    @Test
    fun boardCanBeFENFormattedWithActiveColor() {
        val fen = BoardFormatter().asFEN(board)
        val activeColor = fen.split(" ")[1]

        assertTrue(activeColor.contains(Regex("[wb]")), "$activeColor is not 'w' or 'b'")
    }

    @Test
    fun boardCanBeFENFormattedWithNoCastlingRights() {
        val fen = BoardFormatter().asFEN(board)
        val castlingAvailability = fen.split(" ")[2]

        assertEquals("-", castlingAvailability)
    }

    @Test
    fun boardCanBeFENFormattedWithNoEnPassant() {
        val fen = BoardFormatter().asFEN(board)
        val enPassant = fen.split(" ")[3]

        assertEquals("-", enPassant)
    }

    @Test
    fun boardCanBeFENFormattedWithNoHalfmoveClock() {
        val fen = BoardFormatter().asFEN(board)
        val halfMoveClock = fen.split(" ")[4]

        assertEquals("0", halfMoveClock)
    }

    @Test
    fun boardCanBeFENFormattedWithFullmoveNumberReset() {
        val fen = BoardFormatter().asFEN(board)
        val fullmoveNumber = fen.split(" ")[5]

        assertEquals("1", fullmoveNumber)
    }
}
