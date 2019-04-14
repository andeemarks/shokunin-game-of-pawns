fun main(args: Array<String>) {
    System.out.println(BoardFormatter().asGrid(BoardGenerator().board))
    System.out.println(BoardFormatter().asFEN(BoardGenerator().board))
}