fun main() {
    val m: GeoMap = GeoMap()

    val lb: LineBuilder = LineBuilder(OffRoad(Point(0.0, 0.0), Point(0.0, -5.0)))
        .add(Point(20.0, -5.0), OffRoad::class)
        .add(Point(-180.0, -3.0), Paved::class)
        .add(Point(-205.0, 17.0), Paved::class)

    for (l in lb.lines)
        m.addLine(l)

    m.calibrate()


    println("Done")
}