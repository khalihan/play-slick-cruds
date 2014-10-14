package dao

import scala.slick.dao.DBConnection
import scala.slick.dao.DBConnection.profile

object DaoFactory {
  private implicit def session = DBConnection.databasePool.createSession()

  def getEmployeeDao: IEmployeeDao = new EmployeeDao(profile)

  def getPersonDao: IPersonDao = new PersonDao(profile)

  def getCompanyDao: ICompanyDao = new CompanyDao(profile)

}