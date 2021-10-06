fun main() {
    val bounding = Bounding(57.011143066509376, 57.01667380944605, 9.98273325128271, 9.994967525503926)

    val api = OSMAPI()
    for (osmNode in api.getNodes(bounding)) {
        println(osmNode.id + ":" + osmNode.lat + ":" + osmNode.lon)
    }
}