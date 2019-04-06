class GameOfPawns {

    companion object {
        fun generateBoard(): Array<Array<Char>> {
            return Array(8) { Array(8) {'.'}}
        }
    }


}

fun populate():  Array<Array<Char>>  {
    val board = Array(8) { Array(8) { '.'} }
    board[0] = Array(8) {'P'}
    board[1] = "RNBQKBNR".toCharArray().toTypedArray()
    board[7] = Array(8) {'p'}
    board[6] = "rnbqkbnr".toCharArray().toTypedArray()
    return board
}
