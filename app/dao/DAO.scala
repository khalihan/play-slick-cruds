package dao

import models.{Cat, CatComponent}
import play.api.db.slick._

import scala.slick.driver.JdbcProfile
import scala.slick.lifted.TableQuery




/**
 * Created by kishon on 8/17/14.
 */
class DAO(override val profile: JdbcProfile) extends CatComponent with Profile {

  val Cats = TableQuery[CatsTable]

  val catR = new CatRepository
  private implicit def session = profile.backend.Database.forDataSource(DB(play.api.Play.current).datasource).createSession()
  val dao2 = new CatDao(profile)

}

object current {
  val dao = new DAO(DB(play.api.Play.current).driver)

}
