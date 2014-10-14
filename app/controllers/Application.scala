package controllers


import dao.Company
import models._
import play.api.Play.current
import play.api.data.Forms._
import play.api.data._
import play.api.db.slick._
import play.api.libs.json.Json._
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._
//stable imports to use play.api.Play.current outside of objects:
import dao.current.dao._
import dao.DaoFactory._

object Application extends Controller{
  def index = DBAction { implicit rs =>
    Ok(views.html.index(catDao.findAll))
   // Ok(toJson(Cats.list))
  }

  def listCats =  DBAction { implicit rs =>

    Ok(toJson(catDao.findAll))
  }


  def getByName(name: String) = DBAction{ implicit rs =>
    Ok(toJson(getCompanyDao.getByName(name)))
  }

  def getByNamex(name: String) =
    Ok(toJson(catDao.getByName(name)))



  def get(id: Long) =  DBAction{ implicit rs =>
      Ok(toJson(getCompanyDao.findById(id)))
  }

  def insert = DBAction{ implicit rs =>
    val cat = catForm.bindFromRequest.get
    catDao.insert(cat)
    //Cats.insert(cat)
    Redirect(routes.Application.index)
  }

  val catForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> text(),
      "color" -> text()
    )(Cat.apply)(Cat.unapply)
  )

  implicit val implicitCompanyWrites = new Writes[Company] {
    def writes(company: Company): JsValue = {
      Json.obj(
        "id" ->   company.id.toString,
        "name" -> company.name,
        "color" -> company.email,
        "phone" -> company.phone,
        "fax" -> company.fax,
        "url" -> company.url
      )
    }
  }

  implicit val implicitCatWrites = new Writes[Cat] {
    def writes(cat: Cat): JsValue = {
      Json.obj(
        "id" ->   cat.id.toString,
        "name" -> cat.name,
        "city" -> cat.color
      )
    }
  }
}
