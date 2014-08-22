package models

/**
 * Created by kishon on 8/12/14.
 */

import play.api.db.slick.Profile

import scala.slick.driver.JdbcDriver.simple._
//import scala.slick.driver._


//trait Profile {
//  val profile: JdbcProfile
//}

trait CrudComponent {
  this: Profile =>

  import profile.simple._

  abstract class Crud[T <: Table[E] with IdentifiableTable[PK], E <: Entity[PK], PK: BaseColumnType] {

    val query: TableQuery[T]

    def count(implicit session: Session): Int = {
      query.length.run
    }

    def findAll(implicit session: Session): List[E] = {
      query.list
    }

    def queryById(id: PK)(implicit session: Session) = query.filter(_.id === id)

    def findOne(id: PK)(implicit session: Session): Option[E] = queryById(id).firstOption

    def add(m: E)(implicit session: Session): PK = (query returning query.map(_.id)) += m

    def withId(model: E, id: PK)(implicit session: Session): E

    def extractId(m: E)(implicit session: Session): Option[PK] = m.id

    def save(m: E)(implicit session: Session): E = extractId(m) match {
      case Some(id) => {
        queryById(id).update(m)
        m
      }
      case None => withId(m, add(m))
    }

    def saveAll(ms: E*)(implicit session: Session): Option[Int] = query ++= ms

    def deleteById(id: PK)(implicit session: Session): Int = queryById(id).delete

    def delete(m: E)(implicit session: Session): Int = extractId(m) match {
      case Some(id) => deleteById(id)
      case None => 0
    }
  }

}




trait Entity[PK] {
  def id: Option[PK]
}

trait IdentifiableTable[I] {
  def id: Column[I]
}

