package bank

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}

class AccountTest extends FlatSpec with Matchers {

  "A client" should "be able to make a deposit of 1000 on 10-01-2012" in {
    val date = LocalDate.of(2012, 1, 10)
    val account = Account()
    val deposit = Deposit(1000, date)
    account.makeATransfer(deposit).transfers should contain (deposit)
  }
}
