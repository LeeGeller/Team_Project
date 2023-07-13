package org.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTest {


    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/CreditPayTestCase")
    public void payValues(int amount, int expected) {
        CreditAccount account = new CreditAccount(1_000, 1_000, 5);
        account.pay(amount);

        Assertions.assertEquals(expected, account.getBalance());
    }

    @Test
    public void checkAddWithSmallValue() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.add(1);

        Assertions.assertEquals(101, account.getBalance());
    }

    @Test
    public void yearChangePositiveBalanceValue() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        account.add(4_000);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void addFalseValue() {
        CreditAccount account = new CreditAccount(0, 1_000, 0);
        Assertions.assertFalse(account.add(-5));
    }

    @Test
    public void yearChangeIfBalanceLessZero() {
        CreditAccount account = new CreditAccount(0, 1_000, 15);
        account.pay(500);

        Assertions.assertEquals(-75, account.yearChange());
    }

    @Test
    public void booleanPayIfAmountLessZero() {
        CreditAccount account = new CreditAccount(0, 1_000, 15);

        Assertions.assertFalse(account.pay(-1));
    }

    @Test
    public void booleanPayIfBalanceWithAmountLessLimit() {
        CreditAccount account = new CreditAccount(0, 1_000, 15);

        Assertions.assertTrue(account.pay(500));
    }

    @Test
    public void booleanPayIfBalanceWithAmountMoreLimit() {
        CreditAccount account = new CreditAccount(0, 1_000, 15);

        Assertions.assertFalse(account.pay(1500));
    }

    @Test
    public void booleanPayIfDoNotComeInLimit() {
        CreditAccount account = new CreditAccount(3000, 1_000, 15);

        Assertions.assertTrue(account.pay(1500));
    }

    @Test
    public void booleanPayIf() {
        CreditAccount account = new CreditAccount(0, 1, 0);

        Assertions.assertFalse(account.pay(2));
    }

    @Test
    public void ifPayIsNegative(){
        CreditAccount account = new CreditAccount(0, 1, 0);

        Assertions.assertFalse(account.pay(-2));

        account.add(4_000);


        Assertions.assertEquals(0, account.yearChange());//Надо if, на случай, если баланс > 0
    }

    @Test
    public void yearChangePositiveBalance123() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        account.add(4_000);

        Assertions.assertEquals(0, account.yearChange());//Надо if, на случай, если баланс > 0
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/CreditAddTestCase")
    public void addValues(int amount, int pay, int expected) {
        CreditAccount account = new CreditAccount(1000, 1_000, 5);
        account.add(amount);
        account.pay(pay);

        Assertions.assertEquals(expected, account.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/CreditYearChangeValues")
    public void yearChangeValues(int amount, int pay, int rate, int expected) {
        CreditAccount account = new CreditAccount(0, 1_000, rate);
        account.add(amount);
        account.pay(pay);


        Assertions.assertEquals(expected, account.yearChange());
    }

    @Test
    public void getCreditLimit() {
        CreditAccount account = new CreditAccount(0, 1_000, 0);
        Assertions.assertEquals(1000, account.getCreditLimit());
    }

    @Test
    public void yearChangeIfBalanceMoreZero() {
        CreditAccount account = new CreditAccount(500, 1_000, 15);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void checkPayWithValid() {
        CreditAccount account = new CreditAccount(0, 1000, 5);

        account.add(1_000);
        account.pay(500);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void checkPayWhenBalansMoreZero() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 5);

        account.pay(500);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void checkPayWhenCreditLimitEnough() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 5);

        Assertions.assertTrue(account.pay(1_500));
    }

    @Test
    public void checkPayWhenBalanceEnough() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 5);

        Assertions.assertTrue(account.pay(500));
    }

    @Test
    public void checkPayWhenCreditLimitNotsEnough() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertFalse(account.pay(5_000));
    }

    @Test
    public void checkPayWithChikenRoupAndPlusInCreditLimit() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertTrue(account.pay(1));
    }

    @Test
    public void checkPayWithoutCreditLimit() {
        CreditAccount account = new CreditAccount(0, 0, 5);

        Assertions.assertFalse(account.pay(1));
    }

    @Test
    public void checkPayWithABigMoney() {


        CreditAccount account = new CreditAccount(0, 1_000_000_000, 5);

        Assertions.assertTrue(account.pay(1_000_000));
    }

    @Test
    public void checkSmallPay() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertFalse(account.pay(1_001));
    }

    @Test
    public void checkPay() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertTrue(account.pay(999));
    }

    @Test
    public void checkPayZero() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        Assertions.assertFalse(account.pay(0));
    }


    @Test
    public void checkBalanceWhenPayZero() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.pay(0);

        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void checkThanCreditLimitIsCurrent() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        Assertions.assertEquals(1_000, account.getCreditLimit());
    }

    @Test
    public void checkAddWithZero() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.add(0);

        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void checkAddWithSmall() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.add(1);

        Assertions.assertEquals(101, account.getBalance());
    }


    @Test
    public void checkAddWithLarge() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.add(1_000_000_000);

        Assertions.assertEquals(1_000_000_100, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void yearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(4_000);
        int excepted = -4_000 / 100 * 15;

        Assertions.assertEquals(excepted, account.yearChange());
    }

    @Test
    public void yearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(4_000);


        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void yearChangeNegativeBalanceWithSmallRate() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                1
        );

        account.pay(4_000);
        int excepted = -4_000 / 100 * 1;

        Assertions.assertEquals(excepted, account.yearChange());
    }

    @Test
    public void yearChangeNegativeBalanceWithHightRate() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                100
        );

        account.pay(4_000);
        int excepted = -4_000 / 100 * 100;

        Assertions.assertEquals(excepted, account.yearChange());
    }

    @Test
    public void IllegalArgumentExceptionNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(100, 20, -2);
        });
    }

    @Test
    public void IllegalArgumentExceptionNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(100, -20, 1);
        });
    }

    @Test
    public void IllegalArgumentExceptionNegativeInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-100, 20, 1);
        });
    }
}