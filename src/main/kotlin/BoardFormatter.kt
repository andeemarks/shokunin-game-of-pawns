class BoardFormatter {

    private val staticFields = "w - - 0 1"

    fun asFEN(board: List<List<Char>>): String {
        val piecePlacement = board.joinToString("/") { rank -> rankToFen(rank) }

        return "$piecePlacement $staticFields"
    }

    private fun rankToFen(rank: List<Char>): String {
        var fenRank = rank.joinToString("")

        fenRank = fenRank.replace("........", "8")
        fenRank = fenRank.replace(".......", "7")
        fenRank = fenRank.replace("......", "6")
        fenRank = fenRank.replace(".....", "5")
        fenRank = fenRank.replace("....", "4")
        fenRank = fenRank.replace("...", "3")
        fenRank = fenRank.replace("..", "2")
        fenRank = fenRank.replace(".", "1")

        return fenRank
    }

    fun asGrid(board: List<List<Char>>): String {
        return board.joinToString("\n") { rank -> rank.joinToString("") }
    }

}