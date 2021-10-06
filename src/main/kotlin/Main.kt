import de.westnordost.osmapi.OsmConnection
import de.westnordost.osmapi.map.MapDataApi
import de.westnordost.osmapi.map.data.BoundingBox
import de.westnordost.osmapi.map.data.Node
import de.westnordost.osmapi.map.data.Relation
import de.westnordost.osmapi.map.data.Way
import de.westnordost.osmapi.map.handler.MapDataHandler


fun main() {
    val osm: OsmConnection = OsmConnection("https://api.openstreetmap.org/api/0.6/",
        "my user agent", null)

    val mapApi = MapDataApi(osm)

    val myMapDataHandler: MyMapDataHandler = MyMapDataHandler()
    mapApi.getMap(boundingBox, myMapDataHandler);

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

class MyMapDataHandler() : MapDataHandler {
    override fun handle(p0: BoundingBox?) {
        TODO("Not yet implemented")
    }

    override fun handle(p0: Node?) {
        TODO("Not yet implemented")
    }

    override fun handle(p0: Way?) {
        TODO("Not yet implemented")
    }

    override fun handle(p0: Relation?) {
        TODO("Not yet implemented")
    }

}