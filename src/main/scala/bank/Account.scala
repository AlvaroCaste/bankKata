package bank

import java.time.LocalDate

case class Account(transfers: List[Transfer] = List.empty) {
  def makeATransfer(transfer: Transfer) = Account(this.transfers :+ transfer)
  def balance: Int = transfers.map {
    case Deposit(amount, _) ⇒ amount
    case WithDraw(amount, _) ⇒ -amount
  }.sum
  def sortedByDate = transfers.sortWith((x, y) ⇒ x.date isAfter y.date)
  def statement: String = "date || credit || debit || balance"
}

sealed trait Transfer {
  val amount: Int
  val date: LocalDate
}
case class Deposit(amount: Int, date: LocalDate) extends Transfer
case class WithDraw(amount: Int, date: LocalDate) extends Transfer

