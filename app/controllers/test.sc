import java.net.URL
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created with IntelliJ IDEA.
 * User: Moncef
 * Date: 09/04/13
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */

var nbr= new AtomicInteger(0)
val baseurl="http://localhost:9000/"

def createUrl: URL={
  new URL(baseurl+nbr.incrementAndGet())

}
createUrl


