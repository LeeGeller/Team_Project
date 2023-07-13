package org.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    public class CreateAccounts {
        SavingAccount saving = new SavingAccount(0, 100, 2_000, 0);
        CreditAccount credit = new CreditAccount(0, 2_000, 0);

        @Test
        public void balanceInSavingLessAfterTransfer() {
            Bank bank = new Bank();

            saving.add(1_000);

            Assertions.assertEquals(1_000, saving.getBalance());
        }

        @Test
        public void transferFromSaving(){
            Bank bank = new Bank();
            saving.add(1_000);

            Assertions.assertTrue(bank.transfer(saving,credit,500));
        }

        @Test
        public void transferLessZero(){
            Bank bank = new Bank();
            saving.add(1_000);

            Assertions.assertFalse(bank.transfer(saving,credit,-500));
        }

        @Test
        public void balanceAfterTransferWithFalse(){
            Bank bank = new Bank();
            saving.add(1_000);
            bank.transfer(saving,credit,-500);

            Assertions.assertEquals(1_000, saving.getBalance());
        }

        @Test
        public void transferWithZero(){
            Bank bank = new Bank();
            saving.add(1_000);

            Assertions.assertFalse(bank.transfer(saving,credit,0));
        }

        @Test
        public void transferWithZeroBalance(){
            Bank bank = new Bank();

            Assertions.assertFalse(bank.transfer(saving,credit,500));
        }

        @Test
        public void balanceOfCreditAfterTransfer(){
            Bank bank = new Bank();

            saving.add(1_000);
            bank.transfer(saving,credit,500);

            Assertions.assertEquals(500,credit.getBalance());
        }
    }
}
