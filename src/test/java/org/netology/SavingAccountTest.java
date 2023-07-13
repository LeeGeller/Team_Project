package org.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingAccountTest {

    @Test
    public void rateLessZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(100, 20, 500, -2);
        });
    }

    @Test
    public void initialBalance() {
        SavingAccount savingAccount = new SavingAccount(5, 5, 1_000, 1);

        Assertions.assertEquals(savingAccount.getBalance(), 5);
    }

    @Test //нужно дописать исключения при initialBalance<minBalance
    public void initialBalanceLessMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10, 20, 500, -2);
        });
    }


    @Test
    public void initialBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1_000, 20, 500, -2);
        });
    }

    @Test
    public void minBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1_000, 500, 100, -2);
        });
    }


    @Test
    public void minBalanceMoreMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10, 20, 19, 1);
        });
    }

    @Test
    public void ExceptionInitialBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1000, 20, 500, 1);
        });
    }

    @Test
    public void ExceptionInitialBalanceLessMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10, 20, 500, 1);
        });
    }

    @ParameterizedTest

    @CsvFileSource(files = "src/test/resources/AddTestCase")
    public void methodAddValues(int input, int expected) {
        SavingAccount savingAccount = new SavingAccount(10, 5, 500, 1);
        savingAccount.add(input);

        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addToBalance() {
        SavingAccount savingAccount = new SavingAccount(50, 5, 500, 1);
        savingAccount.add(100);

        int expected = savingAccount.getBalance();
        int actual = 150;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addBalanceToMax() {
        SavingAccount savingAccount = new SavingAccount(50, 5, 500, 1);
        savingAccount.add(450);

        int actual = 500;
        int expected = savingAccount.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodPayValue() {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);

        Assertions.assertTrue(savingAccount.pay(94));
    }

    @Test
    public void balanceAfterPay() {

        SavingAccount savingAccount = new SavingAccount(1_000, 5, 1_500, 1);

        savingAccount.pay(500);

        Assertions.assertEquals(500, savingAccount.getBalance());
    }

    @Test
    public void balanceAfterPayWithFalse() {
        SavingAccount savingAccount = new SavingAccount(1_000, 5, 1_500, 1);

        savingAccount.pay(999);

        Assertions.assertEquals(1_000, savingAccount.getBalance());
    }


    @Test
    public void ifPaySumMoreMinBalance() {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);

        Assertions.assertFalse(savingAccount.pay(96));
    }

    @Test
    public void yearChangeIfNegativeBalance() {
        SavingAccount savingAccount = new SavingAccount(-100, -200, 500, 500);

        int actual = 0;
        int expected = savingAccount.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void setRateIsPositive() {

        SavingAccount savingAccount = new SavingAccount(200, 0, 500, 1);
        savingAccount.setRate(15);

        Assertions.assertEquals(15, savingAccount.getRate());
    }

    @Test
    public void getMinBalance() {
        SavingAccount savingAccount = new SavingAccount(10, 5, 500, 1);

        int actual = savingAccount.getMinBalance();
        int excepted = 5;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void getMaxBalance() {
        SavingAccount savingAccount = new SavingAccount(10, 5, 500, 1);

        int actual = savingAccount.getMaxBalance();
        int excepted = 500;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void buyWithAllYourMoney() {
        SavingAccount savingAccount = new SavingAccount(55, 0, 500, 1);
        savingAccount.pay(55);

        Assertions.assertEquals(0, savingAccount.getBalance());
    }

    @Test
    public void ifPayLessZero() {
        SavingAccount savingAccount = new SavingAccount(55, 0, 500, 1);

        Assertions.assertFalse(savingAccount.pay(-5));
    }

    @Test
    public void ifYearChangIsZero() {
        SavingAccount savingAccount = new SavingAccount(55, 0, 500, 5);

        int expected = 55 / 100 * 5;

        Assertions.assertEquals(expected,savingAccount.yearChange());

    }

}

