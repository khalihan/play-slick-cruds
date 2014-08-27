package scala.slick.dao

//import org.h2.engine.Session

import play.api.db.slick.Profile

//import scala.slick.driver.JdbcProfile
//import DBConnection.profile.simple._
import scala.slick.driver.JdbcDriver.simple._

/**
 * User: Dimitr
 * Date: 08.03.14
 * Time: 14:40
 */
abstract class AbstractDAO(implicit innerSession: Session) {
  this: Profile =>
  import profile.simple._
  //val profile: JdbcProfile = DBConnection.profile
  val session: Session = innerSession
}

