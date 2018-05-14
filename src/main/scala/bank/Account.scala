package bank

import java.time.LocalDate

case class Account(transfers: List[Transfer] = List.empty) {
  def makeATransfer(transfer: Transfer) = Account(this.transfers :+ transfer)
  def balance: Int = transfers.map {
    case Deposit(amount, _) ⇒ amount
    case WithDraw(amount, _) ⇒ -amount
  }.sum
  def statement: String = "date || credit || debit || balance"
}

sealed trait Transfer
case class Deposit(amount: Int, date: LocalDate) extends Transfer
case class WithDraw(amount: Int, date: LocalDate) extends Transfer

