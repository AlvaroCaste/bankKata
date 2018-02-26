package bank

import java.time.LocalDate

case class Account(transfers: List[Deposit] = List.empty) {
  def makeATransfer(deposit: Deposit) = Account(List(deposit))
}

case class Deposit(amount: Int, date: LocalDate)

