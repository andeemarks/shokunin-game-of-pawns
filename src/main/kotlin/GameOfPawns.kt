fun main(args: Array<String>) {
    val board = BoardGenerator().board
    System.out.println(BoardFormatter().asGrid(board))
    System.out.println(BoardFormatter().asFEN(board))
}