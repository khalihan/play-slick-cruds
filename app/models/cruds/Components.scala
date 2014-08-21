package models.cruds

import models.{IdentifiableTable, Entity, CrudComponent}
import play.api.db.slick.Profile

import scala.slick.driver.JdbcProfile
import scala.slick.profile

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
    def last = column[String]("last")

    def * = (id.?, first, last) <> (User.tupled, User.unapply)

  }

  class UserRepository extends Crud[UsersTable, User, Long] {

    override val query = TableQuery[UsersTable]

    override def withId(user: User, id: Long)(implicit session: Session): User = user.copy(id = Option(id))
  }
}
