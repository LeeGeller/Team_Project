package org.netology;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    SavingAccount saving = new SavingAccount(0, 0, 2_000, 0);
    CreditAccount credit = new CreditAccount(0, 2_000, 0);
    Bank bank = new Bank();

    @Test
    public void transToCredit() {  //перевод с сейвинг на кредит допустимое значение
        saving.add(500);
        bank.transfer(saving,credit,250);

        Assertions.assertEquals(250,credit.getBalance());
    }

    @Test
    public void transToSaving() {  //перевод с кредит на сейвинг допустимое значение
        credit.add(500);
        bank.transfer(credit,saving,250);

        Assertions.assertEquals(250,saving.getBalance());
    }
    @Test
    public void transAllToCredit() {   // перевод всех денег с сейвинг на кредит
        saving.add(500);
        bank.transfer(saving,credit,500);

        Assertions.assertEquals(500,credit.getBalance());
    }
    @Test
    public void transAllToSaving() {   // перевод всех денег с кредит на сейвинг
        credit.add(500);
        bank.transfer(credit,saving,500);

        Assertions.assertEquals(500,saving.getBalance());
    }
    @Test
    public void transMoreMaxBalanceToSaving() {   // перевод на сейвинг больше его макс баланса
        credit.add(2500);
        bank.transfer(credit,saving,2500);

        Assertions.assertEquals(0,saving.getBalance());
    }
    @Test
    public void transToSavinMoreCreditLimit() {   // перевод на cейвинг больше чем лимит на кредите

        bank.transfer(credit,saving,2500);

        Assertions.assertEquals(0,saving.getBalance());
    }
    @Test
    public void transToSavinInCreditLimit() {   // перевод на cейвинг в пределах лимита на кредите

        bank.transfer(credit,saving,2000);

        Assertions.assertEquals(2000,saving.getBalance());
    }
    @Test
    public void balanceCreditIfSavingToInCreditLimit() {   // перевод на cейвинг в пределах лимита на кредите запрос баланса credit

        bank.transfer(credit,saving,2000);

        Assertions.assertEquals(-2000,credit.getBalance());
    }
    @Test
    public void savingBalanceIfTransToCredit() {  //перевод с сейвинг на кредит допустимое значение запрос баланса сейвинг
        saving.add(500);
        bank.transfer(saving,credit,499);

        Assertions.assertEquals(1,saving.getBalance());
    }


    @Test
    public void balanceInSavingLessAfterTransfer() {
        Bank bank = new Bank();

        saving.add(1_000);

        Assertions.assertEquals(1_000, saving.getBalance());
    }

    @Test
    public void transferFromSaving() {
        Bank bank = new Bank();
        saving.add(1_000);

        Assertions.assertTrue(bank.transfer(saving, credit, 500));
    }

    @Test
    public void transferLessZero() {
        Bank bank = new Bank();
        saving.add(1_000);

        Assertions.assertFalse(bank.transfer(saving, credit, -500));
    }

    @Test
    public void balanceAfterTransferWithFalse() {
        Bank bank = new Bank();
        saving.add(1_000);
        bank.transfer(saving, credit, -500);

        Assertions.assertEquals(1_000, saving.getBalance());
    }

    @Test
    public void transferWithZero() {
        Bank bank = new Bank();
        saving.add(1_000);

        Assertions.assertFalse(bank.transfer(saving, credit, 0));
    }

    @Test
    public void transferWithZeroBalance() {
        Bank bank = new Bank();

        Assertions.assertTrue(bank.transfer(saving, credit, 500));
    }

    @Test
    public void balanceOfCreditAfterTransfer() {
        Bank bank = new Bank();

        saving.add(1_000);
        bank.transfer(saving, credit, 500);

        Assertions.assertEquals(500, credit.getBalance());
    }
}


