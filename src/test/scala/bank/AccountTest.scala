package bank

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}

class AccountTest extends FlatSpec with Matchers {

  val account = Account()
  val date = LocalDate.of(2012, 1, 10)
  val deposit1000 = Deposit(1000, date)

  "A client" should "be able to make a deposit of 1000 on 10-01-2012" in {
    account.makeATransfer(deposit1000).transfers should contain (deposit1000)
  }

  it should "be able to make another deposit of 2000 on 13-01-2012" in {
    val anotherDate = LocalDate.of(2012, 1, 13)
    val anotherDeposit = Deposit(2000, anotherDate)
    account
      .makeATransfer(deposit1000)
      .makeATransfer(anotherDeposit)
      .transfers should contain allOf (deposit1000, anotherDeposit)
  }
}
