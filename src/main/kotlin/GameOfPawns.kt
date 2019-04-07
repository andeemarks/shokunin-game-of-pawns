private const val PIECES = "RNBQKBNR"
private const val ROW_SIZE = 8

private const val WHITE_PIECES = PIECES
private val BLACK_PIECES = PIECES.toLowerCase()

private val WHITE_PAWNS = "P".repeat(ROW_SIZE)
private val BLACK_PAWNS = WHITE_PAWNS.toLowerCase()
private val EMPTY_SQUARES = ".".repeat(32)

fun populate(): List<List<Char>> {

    return "$WHITE_PAWNS$WHITE_PIECES$BLACK_PAWNS$BLACK_PIECES$EMPTY_SQUARES".toMutableList().shuffled().chunked(ROW_SIZE)
}

fun List<List<Char>>.whitePromotionRow(): List<Char> {
    return this[0]
}

fun List<List<Char>>.blackPromotionRow(): List<Char> {
    return this[7]
}
