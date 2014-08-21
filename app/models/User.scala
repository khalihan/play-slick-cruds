package models

import play.api.db.slick.Profile


/**
 * Created by kishon on 8/12/14.
 */
case class User(id: Option[Long], first: String, last: String) extends Entity[Long]

trait UserComponent extends CrudComponent {
  this: Profile =>

  import profile.simple._

  class UsersTable(tag: Tag) extends Table[User](tag, "users") with IdentifiableTable[Long] {

    override def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def first = column[String]("first")
    def last = column[String]("last", O.NotNull)

    def * = (id.?, first, last) <>(User.tupled, User.unapply)

  }

}