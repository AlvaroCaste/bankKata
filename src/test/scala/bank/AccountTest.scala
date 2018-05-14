package bank

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}

class AccountTest extends FlatSpec with Matchers {

  val account = Account()
  val deposit1000 = Deposit(1000, LocalDate.of(2012, 1, 10))
  val deposit2000 = Deposit(2000, LocalDate.of(2012, 1, 13))
  val withdraw500 = WithDraw(500, LocalDate.of(2012, 1, 14))

  "A client" should "be able to make a deposit of 1000 on 10-01-2012" in {
    account.makeATransfer(deposit1000).transfers should contain (deposit1000)
  }

  it should "be able to make another deposit of 2000 on 13-01-2012" in {
    account
      .makeATransfer(deposit1000)
      .makeATransfer(deposit2000)
      .transfers should contain allOf (deposit1000, deposit2000)
  }

  it should "be able to make a withdraw of 500 on 14-01-2012" in {
    account.makeATransfer(withdraw500).transfers should contain (withdraw500)
  }

  it should "be able to get total amount" in {
    account
      .makeATransfer(deposit1000)
      .makeATransfer(deposit2000)
      .makeATransfer(withdraw500)
      .total shouldBe 2500
  }

  it should "print her bank statement when is empty" in {
    val emptyStatement = "date || credit || debit || balance"
    account.statement shouldBe emptyStatement
  }

  it should "print her bank statement" in {
    val statement =
      """date || credit || debit || balance
        |14/01/2012 || || 500.00 || 2500.00
        |13/01/2012 || 2000.00 || || 3000.00
        |10/01/2012 || 1000.00 || || 1000.00
      """.stripMargin
    account
      .makeATransfer(deposit1000)
      .makeATransfer(deposit2000)
      .makeATransfer(withdraw500)
      .statement shouldBe statement
  }
}
