import kotlin.math.pow
import kotlin.math.sqrt

class GeoMap(val points: MutableList<Point> = mutableListOf(), val lines: MutableList<Line> = mutableListOf()) {
    var mapStart: Point = Point(0.0,0.0)
    var mapEnd: Point = Point(0.0,0.0)

    init {
        calcMapPoints()
    }

    fun addLine(l: Line) {
        lines.add(l)
    }

    fun calibrate() {
        calcMapPoints()
    }

    private fun calcMapPoints() {
        mapStart = points.minOrNull() ?: Point(0.0, 0.0)
        val linesMinX = lines.map { if (it.start.x < it.end.x) it.start.x else it.end.x }.minOrNull() ?: 0.0
        val linesMinY = lines.map { if (it.start.y < it.end.y) it.start.y else it.end.y }.minOrNull() ?: 0.0
        val mbySmaller = Point(linesMinX, linesMinY)

        if (mbySmaller < mapStart)
            mapStart = mbySmaller

        mapEnd = points.maxOrNull() ?: Point(0.0, 0.0)
        val linesMaxX = lines.map { if (it.start.x > it.end.x) it.start.x else it.end.x }.maxOrNull() ?: 0.0
        val linesMaxY = lines.map { if (it.start.y > it.end.y) it.start.y else it.end.y }.maxOrNull() ?: 0.0
        val mbyBigger = Point(linesMaxX, linesMaxY)

        if (mbyBigger > mapEnd)
            mapEnd = mbyBigger
    }

    fun addPoint(p: Point) {
        points.add(p)

        if (p > mapEnd)
            mapEnd = p
    }

    fun nextLine(p: Point): Line? = lines.find { it.start == p || it.end == p}

    fun nextCloseLine(p: Point): Line? {
        var closest: Pair<Double, Line?> = Pair(Double.MAX_VALUE, null)

        for (l in lines) {
            var d = dist(p, l.start)
            if (d < closest.first)
                closest = Pair(d, l)
            d = dist(p, l.end)
            if (d < closest.first)
                closest = Pair(d, l)
        }

        return closest.second
    }
}

open class Point(val x: Double, val y: Double) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
        if (x > other.x)
            return -1
        if (x < other.x)
            return 1
        return y.compareTo(other.y)
    }
}

open class Line(val start: Point, val end: Point) {

}

open class Road(start: Point, end: Point) : Line(start, end) {

}

class Paved(start: Point, end: Point) : Road(start, end) {

}

class Dirt(start: Point, end: Point) : Road(start, end) {

}

class OffRoad(start: Point, end: Point) : Road(start, end) {

}

fun dist(p1: Point, p2: Point) = sqrt((p1.x - p2.x).pow(2.0) + (p1.y - p2.y).pow(2.0))