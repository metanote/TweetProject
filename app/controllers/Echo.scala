package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Forms._
import play.api.data._

/**
 * Created with IntelliJ IDEA.
 * User: Moncef
 * Date: 11/04/13
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
object Echo extends Controller{

def get(t:String)= Action(parse.raw){ req =>
  Ok(views.html.echo(t,req))

}
  def post(t:String)= Action(parse.raw){   req =>
    Ok(views.html.echo(t,req))

  }
  def put(t:String)= Action(parse.raw){   req =>
    Ok(views.html.echo(t,req))
  }
}
