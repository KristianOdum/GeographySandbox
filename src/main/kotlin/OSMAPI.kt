import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

class OSMAPI {
    private val OVERPASS_API = "http://www.overpass-api.de/api/interpreter"
    private val OSM_API = "http://www.openstreetmap.org/"

    fun getNodes(bounds: Bounding): List<node> {
        val res = mutableListOf<node>()

        // Setup connection to openstreetmap.org
//        val apiUrl = (OSM_API + "map?bbox=" + bounds.minLat + "," + bounds.minLat + "," + bounds.maxLon + "," + bounds.maxLat)
//        val textUrl = """https://www.openstreetmap.org/#map=15/57.0113/9.9885""" // TODO: FIX
//        val xmlDocument = File("""C:\Users\kristian\Downloads\map.osm""").readText()
//        val osm = URL(textUrl)
//        val connection: HttpURLConnection = osm.openConnection() as HttpURLConnection
//        val xmlDocument: String = connection.inputStream.readAllBytes().toString()
        val xmlDocument = this::class.java.getResource("map_test.osm")!!.readText() // TODO: Hard coded to not use bandwidth

        // Parse xml document
        val mapper = XmlMapper()
        val osm = mapper.readValue(xmlDocument, osm::class.java)
//        mapper.readTree(xmlDocument)["node"].forEach { res.add(mapper.readValue(it.asText(), node::class.java)) }

        return res
    }

//    fun getNodes(xmlDocument: String): List<OSMNode> {
//        val osmNodes: MutableList<OSMNode> = mutableListOf()
//
//        // Document xml = getXML(8.32, 49.001);
//        val osmRoot: Node = xmlDocument.firstChild
//        val osmXMLNodes: NodeList = osmRoot.childNodes
//        for (i in 1 until osmXMLNodes.length) {
//            val item: Node = osmXMLNodes.item(i)
//            if (item.nodeName.equals("node")) {
//                val attributes: NamedNodeMap = item.attributes
//                val tagXMLNodes: NodeList = item.childNodes
//                val tags: MutableMap<String, String> = HashMap()
//                for (j in 1 until tagXMLNodes.length) {
//                    val tagItem: Node = tagXMLNodes.item(j)
//                    val tagAttributes: NamedNodeMap = tagItem.attributes
//                    if (tagAttributes != null) {
//                        tags[tagAttributes.getNamedItem("k").nodeValue] = tagAttributes.getNamedItem("v")
//                            .nodeValue
//                    }
//                }
//                val namedItemID: Node = attributes.getNamedItem("id")
//                val namedItemLat: Node = attributes.getNamedItem("lat")
//                val namedItemLon: Node = attributes.getNamedItem("lon")
//                val namedItemVersion: Node? = attributes.getNamedItem("version")
//                val id: String = namedItemID.nodeValue
//                val latitude: String = namedItemLat.nodeValue
//                val longitude: String = namedItemLon.nodeValue
//                var version = "0"
//                if (namedItemVersion != null) {
//                    version = namedItemVersion.nodeValue
//                }
//                osmNodes.add(OSMNode(id, latitude, longitude, tags, version))
//            }
//        }
//        return osmNodes
//    }

//    fun getNode(nodeId: String): OSMNode? {
//        val string = "http://www.openstreetmap.org/api/0.6/node/$nodeId"
//        val osm = URL(string)
//        val connection: HttpURLConnection = osm.openConnection() as HttpURLConnection
//        val xml: String = connection.inputStream.toString()
//        val nodes: List<OSMNode> = getNodes(xml)
//        return if (nodes.isNotEmpty()) {
//            nodes.iterator().next()
//        } else null
//    }

//    private fun getXML(bounds: Bounding): String {
//        val apiUrl = (OSM_API + "map?bbox=" + bounds.minLat + "," + bounds.minLat + "," + bounds.maxLon + "," + bounds.maxLat)
//        val textUrl = """https://www.openstreetmap.org/#map=15/57.0113/9.9885""" // TODO: FIX
//        val osm = URL(textUrl)
//        val connection: HttpURLConnection = osm.openConnection() as HttpURLConnection
//        return connection.inputStream.readAllBytes().toString()
//    }

//    fun getXMLFile(location: String?): Document? {
//        val dbfac = DocumentBuilderFactory.newInstance()
//        val docBuilder = dbfac.newDocumentBuilder()
//        return docBuilder.parse(location)
//    }

//    fun getOSMNodesInVicinity(lat: Double, lon: Double, vicinityRange: Double): List<OSMNode> {
//        return OSMAPI.getNodes(getXML(lon, lat, vicinityRange))
//    }

//    fun getOSMNodesInVicinity(bounds: Bounding): List<OSMNode> {
//        return getNodes(getXML(bounds))
//    }

//    fun getNodesViaOverpass(query: String): Document? {
//        val hostname = OVERPASS_API
//        val queryString = readFileAsString(query)
//        val osm = URL(hostname)
//        val connection: HttpURLConnection = osm.openConnection() as HttpURLConnection
//        connection.setDoInput(true)
//        connection.setDoOutput(true)
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
//        val printout = DataOutputStream(connection.getOutputStream())
//        printout.writeBytes("data=" + URLEncoder.encode(queryString, "utf-8"))
//        printout.flush()
//        printout.close()
//        val dbfac = DocumentBuilderFactory.newInstance()
//        val docBuilder = dbfac.newDocumentBuilder()
//        return docBuilder.parse(connection.getInputStream())
//    }
}

class Bounding(val minLat: Double, val maxLat: Double, val minLon: Double, val maxLon: Double)

@JacksonXmlRootElement
data class osm(
//    @JacksonXmlElementWrapper(useWrapping = false)
    var node: List<node> = listOf()
)


data class node(
    val id: String,
    val lat: String,
    val lon: String,
    val tags: List<tag>,
    val version: String)

data class relation(
    val id: String,
    val visible: String,
    val version: String,
    val members: List<member>,
    val tags: List<tag>
)

data class tag(val k: String, val v: String)

data class member(
    val type: String,
    val ref: String,
    val role: String
)