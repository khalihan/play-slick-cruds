package models

import play.api.db.slick._

import scala.slick.dao.{CRUDable, AbstractDAO}
import scala.slick.driver.JdbcProfile
//import scala.slick.dao.DBConnection.profile.simple._

//import models.cruds.{CrudComponent, IdentifiableTable}
//import play.api.db.slick.Profile

case class Cat(id: Option[Long], name: String, color: String)

/**
 * This Cat component contains the database representation of your
 * furry friends
 *
 * This pattern is called the cake pattern (I think it is because
 * it tastes good :P),
 *
 * Do not worry about the scary and yummy name, it is easily copyable!
 *
 * Just follow the steps
 * for each Table you want to have:
 *  1. the play.api.db.slick.Profile "self-type" (see below for an example)
 *  2. the import profile.simple._
 *
 * The reason you want to use the cake pattern here is because
 * we imagine we have multiple different databases for production
 * and tests
 */
trait CatComponent {
  this: Profile => //<- step 1: you must add this "self-type"
  import profile.simple._ //<- step 2: then import the correct Table, ... from the profile

  class CatsTable(tag: Tag) extends Table[Cat](tag, "accounts") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name", O.PrimaryKey)
    def color = column[String]("city", O.NotNull)

    def * = (id.?, name, color) <> (Cat.tupled, Cat.unapply _)
  }

  trait ICatDao extends AbstractDAO with CRUDable[CatsTable, Long] {

    val entities: TableQuery[CatsTable] = TableQuery[CatsTable]

    def selectBy(entity: Cat) = {
      for (e <- entities if e.id === entity.id) yield e
    }

    def selectById(id: Long) = {
      for (e <- entities if e.id === id) yield e
    }

    private val allQuery = Compiled{ name: Column[String] =>
      for{ e <- entities if e.name === name } yield e
    }

    def getByName(name: String): List[Cat] = {
      allQuery(name).run.toList
    }

  }

  class CatDao(override val profile: JdbcProfile)(implicit session: Session) extends ICatDao  with Profile

}
