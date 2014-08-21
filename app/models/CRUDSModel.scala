package models

/*import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

/**
* Helper for otherwise verbose Slick model definitions
*/
trait CRUDModel[T <: AnyRef { val id: Option[Long] }] { self: Table[T] =>

  def id: Column[Long]

  def * : scala.slick.lifted.ColumnBase[T]

  def autoInc = * returning id

  def insert(entity: T) = {
    DB.withSession { implicit session =>
      autoInc.insert(entity)
    }
  }

  def insertAll(entities: Seq[T]) {
    DB.withSession { implicit session =>
      autoInc.insertAll(entities: _*)
    }
  }

  def update(id: Long, entity: T) {
    DB.withSession { implicit session =>
      tableQueryToUpdateInvoker(
        tableToQuery(this).where(_.id === id)
      ).update(entity)
    }
  }

  def delete(id: Long) {
    DB.withSession { implicit session =>
      queryToDeleteInvoker(
        tableToQuery(this).where(_.id === id)
      ).delete
    }
  }

  def count = DB.withSession { implicit session =>
    Query(tableToQuery(this).length).first
  }

}*/