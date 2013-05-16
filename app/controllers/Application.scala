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

 def deleteTweet(twt:String)=Action { implicit request =>
     val l= delTweet(twt)
 Ok(views.html.index(l,tweetForm))
 }

 def viewTweet(t:String)=Action{  implicit req =>

    Ok(views.html.myTweet(t))
  }
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

 def removeFromList(t:Tweet):List[Tweet]={
   val (r,l)=tweetDB.span(_.url!=t.url)
   r ::: l.tail
 }

  def delTweet(t: String):List[Tweet] = {
    val ts : List[Tweet] = allTweets()
    val (head, tai) = ts.span(_.tweet!=t)
   tweetDB=head ::: tai.tail
    tweetDB
    }

  }