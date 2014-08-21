package models

import play.api.db.slick.Profile

case class Company(
                    id: Option[Long],
                    email: String,
                    name: String,
                    phone: String,
                    fax: String,
                    url: String
                    ) extends Entity[Long]

/**
 * Created by kishon on 8/12/14.
 */
trait CompanyComponent {
  this: Profile => //<- step 1: you must add this "self-type"
  import profile.simple._ //<- step 2: then import the correct Table, ... from the profile

  class CompanyTable(tag: Tag) extends Table[Company](tag, "CAT") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("email", O.NotNull)
    def name = column[String]("name", O.NotNull)
    def phone = column[String]("phone", O.NotNull)
    def url = column[String]("web_URL", O.NotNull)
    def fax = column[String]("fax", O.NotNull)

    def * = (id.?, email, name, phone, url, fax) <> ((Company.apply _).tupled, Company.unapply _)
  }
}