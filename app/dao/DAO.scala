package dao

import models.CatComponent
import play.api.db.slick._

import scala.slick.driver.JdbcProfile




/**
 * Created by kishon on 8/17/14.
 */
class DAO(override val profile: JdbcProfile) extends CatComponent with Profile {

  private implicit def session = profile.backend.Database.forDataSource(DB(play.api.Play.current).datasource).createSession()
  val catDao = new CatDao(profile)

}

object current {
  val dao = new DAO(DB(play.api.Play.current).driver)

}
