package dao

import models.CompanyComponent
import play.api.db.slick._

import scala.slick.driver.JdbcProfile
import scala.slick.lifted.TableQuery

/**
 * Created by kishon on 10/13/14.
 */
class CompanyDAO(override val profile: JdbcProfile) extends CompanyComponent with Profile {
  private implicit def session = profile.backend.Database.forDataSource(DB(play.api.Play.current).datasource).createSession()
  val Companys = TableQuery[CompanyTable]
  val compR = new CompanyRepository
}



object company_current {
  val compdao = new CompanyDAO(DB(play.api.Play.current).driver)
}
