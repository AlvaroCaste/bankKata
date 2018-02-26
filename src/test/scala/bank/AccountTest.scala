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

  it should "be able to make another deposit of 2000 on 13-01-2012" in {
    val date1 = LocalDate.of(2012, 1, 10)
    val date2 = LocalDate.of(2012, 1, 13)
    val account = Account()
    val deposit1 = Deposit(1000, date1)
    val deposit2 = Deposit(2000, date2)
    account
      .makeATransfer(deposit1)
      .makeATransfer(deposit2)
      .transfers should contain allOf (deposit1, deposit2)
  }
}
