package scala.slick.dao


import play.api.db.slick._

import scala.slick.driver.JdbcProfile

object DBConnection extends Profile {

  val profile:JdbcProfile = DB(play.api.Play.current).driver

  val databasePool = profile.backend.Database.forDataSource(DB(play.api.Play.current).datasource)
}