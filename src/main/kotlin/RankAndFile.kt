import kotlin.math.absoluteValue

class RankAndFile(val rank: Int, val file: Int) {
    private fun rowDistanceTo(other: RankAndFile) = (this.rank - other.rank).absoluteValue
    private fun columnDistanceTo(other: RankAndFile) = (this.file - other.file).absoluteValue

    fun isNeighbourOf(other: RankAndFile): Boolean = (this.columnDistanceTo(other) <= 1 && this.rowDistanceTo(other) <= 1)
}
