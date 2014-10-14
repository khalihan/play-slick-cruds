package dao

import play.api.db.slick.Profile

import scala.slick.dao.DBConnection.profile.simple._
import scala.slick.dao.{AbstractDAO, CRUDable}
import scala.slick.driver.JdbcProfile

case class Company(
                    id: Option[Long],
                    email: String,
                    name: String,
                    phone: String,
                    fax: String,
                    url: String
                    )

/**
 * Created by kishon on 8/12/14.
 */


  class CompanyTable(tag: Tag) extends Table[Company](tag, "accounts") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def email = column[String]("email", O.NotNull)

    def name = column[String]("name", O.NotNull)

    def phone = column[String]("phone", O.NotNull)

    def url = column[String]("web_URL", O.NotNull)

    def fax = column[String]("fax", O.NotNull)

    def * = (id.?, email, name, phone, url, fax) <>((Company.apply _).tupled, Company.unapply _)
  }

  trait ICompanyDao extends AbstractDAO with CRUDable[CompanyTable, Long] {

    val entities: TableQuery[CompanyTable] = TableQuery[CompanyTable]

    def selectBy(entity: Company) = {
      for (e <- entities if e.id === entity.id) yield e
    }

    def selectById(id: Long) = {
      for (e <- entities if e.id === id) yield e
    }

    private val allQuery = Compiled { name: Column[String] =>
      for {e <- entities if e.name === name} yield e
    }

    def getByName(name: String): List[Company] = {
      allQuery(name).run.toList
    }


  }

  class CompanyDao(override val profile: JdbcProfile)(implicit session: Session) extends ICompanyDao with Profile

