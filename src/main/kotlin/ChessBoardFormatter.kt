class ChessBoardFormatter {

    private val staticFields = "w - - 0 1"

    fun asFEN(board: List<List<Char>>): String {
        val piecePlacement = board.joinToString("/") { rank -> rankToFen(rank) }

        return "$piecePlacement $staticFields"
    }

    private fun rankToFen(rank: List<Char>): String {
        return "1"
    }

}