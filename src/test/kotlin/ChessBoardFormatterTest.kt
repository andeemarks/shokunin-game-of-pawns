import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChessBoardFormatterTest {
    private val board = ChessBoard().board

    private fun emptyRank() = EMPTY_SQUARE.toString().repeat(8).toCharArray().toList()

    @Test
    fun populatedBoardCanBeGridFormattedWithCorrectNumberOfRanks() {
        val grid = ChessBoardFormatter().asGrid(board)

        assertEquals(8, grid.size)
    }

    @Test
    fun populatedBoardCanBeGridFormattedShowingAllSquares() {
        val grid = ChessBoardFormatter().asGrid(board)

        repeat(8) { rank -> assertEquals(board[rank].joinToString(""), grid[rank]) }
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithNumberOfRanks() {
        val fen = ChessBoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals(8, ranks.split("/").size)
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithEmptyRank() {
        val board = board.toMutableList()
        board[0] = emptyRank()
        board[3] = emptyRank()
        val fen = ChessBoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("8", ranks.split("/")[0])
        assertEquals("8", ranks.split("/")[3])
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithConsecutiveEmptySquares() {
        val board = board.toMutableList()
        board[0] = ".Pp..Kq.".toCharArray().toList()
        val fen = ChessBoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("1Pp2Kq1", ranks.split("/")[0])
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithRankOfPawns() {
        val board = board.toMutableList()
        board[0] = WHITE_PAWN.toString().repeat(8).toCharArray().toList()
        val fen = ChessBoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("PPPPPPPP", ranks.split("/")[0])
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithRankOfMixedPieces() {
        val board = board.toMutableList()
        board[0] = ".PpR.Kq.".toCharArray().toList()
        val fen = ChessBoardFormatter().asFEN(board)
        val ranks = fen.split(" ")[0]

        assertEquals("1PpR1Kq1", ranks.split("/")[0])
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithActiveColor() {
        val fen = ChessBoardFormatter().asFEN(board)
        val activeColor = fen.split(" ")[1]

        assertTrue(activeColor.contains(Regex("[wb]")), "$activeColor is not 'w' or 'b'")
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithNoCastlingRights() {
        val fen = ChessBoardFormatter().asFEN(board)
        val castlingAvailability = fen.split(" ")[2]

        assertEquals("-", castlingAvailability)
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithNoEnPassant() {
        val fen = ChessBoardFormatter().asFEN(board)
        val enPassant = fen.split(" ")[3]

        assertEquals("-", enPassant)
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithNoHalfmoveClock() {
        val fen = ChessBoardFormatter().asFEN(board)
        val halfMoveClock = fen.split(" ")[4]

        assertEquals("0", halfMoveClock)
    }

    @Test
    fun populatedBoardCanBeFENFormattedWithFullmoveNumberReset() {
        val fen = ChessBoardFormatter().asFEN(board)
        val fullmoveNumber = fen.split(" ")[5]

        assertEquals("1", fullmoveNumber)
    }
}
