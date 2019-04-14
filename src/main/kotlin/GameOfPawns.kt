fun main(args: Array<String>) {
    val board = BoardGenerator().board
    if (args.isEmpty() || args[0].toLowerCase() == "--fen") {
        System.out.println(BoardFormatter().asFEN(board))
    } else if (args[0].toLowerCase() == "--grid") {
        System.out.println(BoardFormatter().asGrid(board))
    }
}