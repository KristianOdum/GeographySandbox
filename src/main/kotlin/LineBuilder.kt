import kotlin.reflect.KClass

class LineBuilder(l: Line) {
    val lines: MutableList<Line> = mutableListOf(l)
    var lastPoint: Point = Point(0.0, 0.0)

    init {
        lastPoint = l.end
    }

    fun add(next: Point, clazz: KClass<out Road>): LineBuilder {
        val line = when (clazz) {
            Paved::class -> Paved(lastPoint, next)
            Dirt::class -> Dirt(lastPoint, next)
            OffRoad::class -> OffRoad(lastPoint, next)
            else -> Road(lastPoint, next)
        }

        lines.add(line)
        lastPoint = line.end

        return this
    }
}