package models

/**
 * Created by kishon on 8/12/14.
 */
import scala.slick.driver.JdbcProfile
import scala.slick.lifted.TableQuery
import play.api.db.slick.Profile
import play.api.db.slick.DB

class CompanyDAO(override val profile: JdbcProfile) extends CompanyComponent with Profile {
  val Companys = TableQuery[CompanyTable]
}

object company_current {
  val compdao = new CompanyDAO(DB(play.api.Play.current).driver)
}

//  class UserRepository(implicit session: Session) extends Crud[UsersTable, User, Long] {
//
//    override def query = TableQuery[UsersTable]
//
//    override def withId(user: User, id: Long): User = user.copy(id = Option(id))
//  }