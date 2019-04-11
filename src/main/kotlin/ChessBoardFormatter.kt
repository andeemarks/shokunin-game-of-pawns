class ChessBoardFormatter {

    private val staticFields = "w - - 0 1"

    fun asFEN(board: List<List<Char>>): String {
        val piecePlacement = board.joinToString("/") { rank -> rankToFen(rank) }

        return "$piecePlacement $staticFields"
    }

    private fun rankToFen(rank: List<Char>): String {
        var fenRank = rank.map { square -> if (square == EMPTY_SQUARE) '1' else square }.joinToString("")

        val consecutiveEmptySpacesRE = "(.*)(\\d)(\\d)(.*)"
        var consecutiveEmptySquares = Regex(consecutiveEmptySpacesRE).find(fenRank)
        while (consecutiveEmptySquares?.value != null) {
            val rankComponents = consecutiveEmptySquares.groupValues
            fenRank = rankComponents[1] + (rankComponents[2].toInt() + rankComponents[3].toInt()) + rankComponents[4]
            consecutiveEmptySquares = Regex(consecutiveEmptySpacesRE).find(fenRank)
        }
        return fenRank
    }

}