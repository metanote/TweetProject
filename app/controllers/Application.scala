package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import java.util.Date
import java.net.URL
import java.util.concurrent.atomic.AtomicInteger
import Tweet._
object Application extends Controller {

val tweetForm = Form(
mapping(
"tweet" -> text

)(tweetTxt => Tweet(tweetTxt,new Date(),Tweet.createUrl))(twt => Some(twt.tweet))
//apply and unapply methodes equivalent d'un constructeur/deconstructeur en JAVA
)

  def index = Action {
    Ok(views.html.index(Tweet.allTweets(),tweetForm))
  }
  //index une fontion qui permet de envoyer le formulaire
 /*
  def tweets = Action {
    Ok(views.html.index(Tweet.all(),tweetForm))
  }
   */

  def submitTweet = Action { implicit req =>
    tweetForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Tweet.allTweets(),errors)),
      tweet => {
        addToList(tweet)
        Redirect(routes.Application.index)
        }
    )
  }
//envoyer un nouveau tweet

  def deleteTweet=TODO
 /* def viewTweet=Action{  implicit req =>
    allTweets()
    Ok(views.html.myTweet)

  }*/

}
case class Tweet(tweet:String, update:Date, url: URL)
object Tweet{
  var nbr= new AtomicInteger(0)
  val baseurl="http://localhost:9000/"

  def createUrl: URL={
    new URL(baseurl+nbr.incrementAndGet())

  }

  var tweetDB : List[Tweet]=Nil

  def addToList(t:Tweet):List[Tweet]={
   tweetDB = t :: tweetDB
   tweetDB
  }
  def allTweets():List[Tweet]=tweetDB
  def apply(t:String): Tweet = Tweet(t,update =new Date(),createUrl)

  //def delete(t:String):Tweet{}
}