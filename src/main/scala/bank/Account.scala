package bank

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import bank.Account.printTransfer

case class Account(transfers: List[Transfer] = List.empty) {
  def makeATransfer(transfer: Transfer) = Account(this.transfers :+ transfer)
  def balance: Int = transfers.map(amount).sum
  def statement: String =
    s"date || credit || debit || balance${if (transfers.isEmpty) "" else printLine}"

  private def printLine = {
    val transfersSorted = transfers.sorted
    val balances = transfersSorted.map(amount).scanLeft(0)(_ + _).tail
    (transfersSorted zip balances).map{case (t, b) ⇒ printTransfer(t, b)}.reverse.mkString("\n", "\n", "")
  }
  private def amount(t: Transfer) = t match {
    case Deposit(amount, _) ⇒ amount
    case WithDraw(amount, _) ⇒ -amount
  }
  private implicit def sortDescByDate: Ordering[Transfer] = _.date compareTo _.date
}

object Account {
  def printTransfer(transfer: Transfer, balance: Int): String = {
    val date = transfer.date.format(DateTimeFormatter.ofPattern("DD/MM/YYYY"))
    val amount = s"${transfer.amount}.00"
    transfer match {
      case Deposit(_, _) ⇒ s"$date || $amount || || $balance.00"
      case WithDraw(_, _) ⇒ s"$date || || $amount || $balance.00"
    }
  }
}

sealed trait Transfer {
  val amount: Int
  val date: LocalDate
}
case class Deposit(amount: Int, date: LocalDate) extends Transfer
case class WithDraw(amount: Int, date: LocalDate) extends Transfer

