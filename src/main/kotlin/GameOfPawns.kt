class GameOfPawns {

    companion object {
        fun generateBoard(): Array<Array<Char>> {
            return Array(8) { Array(8) {'.'}}
        }
    }


}

fun <T> Array<T>.populate():  Array<Array<Char>>  {
    return Array(8) { Array(8) {i: Int -> if (i % 2 == 0) '.' else 'x'}}
}
