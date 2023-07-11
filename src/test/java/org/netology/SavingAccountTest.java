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

    /*@ParameterizedTest
    @CsvFileSource(files = "src/test/resources/InitialBalanceTest")
    public void initialBalance(int init, int expected) {
        SavingAccount savingAccount = new SavingAccount(init, 5, 500, 1);
        int expected = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }*/

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
    public void initialBalanceMoreMaxBalance() { //нужно дописать исключения при initialBalance>maxBalance
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


    @Test //тест не пройден !!!!!! дописать исключения при maxBalance<minBalance
    public void minBalanceMoreMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10, 20, 19, 1);
        });
    }

    @Test
    public void ExceptionInitialBalanceMoreMaxBalance() {  //тест не пройден !!!!!!нужно дописать исключения при initialBalance>maxBalance||initialBalance<minBalance
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1000, 20, 500, 1);
        });
    }

    @Test
    public void ExceptionInitialBalanceLessMinBalance() {  //тест не пройден нужно дописать исключения!!!!!!||initialBalance>maxBalance||initialBalance<minBalance
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10, 20, 500, 1);
        });
    }

    @ParameterizedTest
    //граничные значения и валидное значение;//тест не проходит ,нужно добавить <= вместо < в метод add и balance + amount вместо amount
    @CsvFileSource(files = "src/test/resources/AddTestCase")
    public void methodAddValues(int input, int expected) {
        SavingAccount savingAccount = new SavingAccount(10, 5, 500, 1);
        savingAccount.add(input);

        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addToBalance() { //balance + amount вместо amount в метод add там где if true
        SavingAccount savingAccount = new SavingAccount(50, 5, 500, 1);
        savingAccount.add(100);

        int expected = savingAccount.getBalance();
        int actual = 150;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addBalanceToMax() { //нужно добавить <= вместо < в метод add там где if true
        SavingAccount savingAccount = new SavingAccount(50, 5, 500, 1);
        savingAccount.add(450);

        int actual = 500;
        int expected = savingAccount.getBalance();
        Assertions.assertEquals(expected, actual);
    }

/*@ParameterizedTest
    //граничные значения и валидное значение;//тест не проходит ,нужно добавить ||balance-amount<minBalance на 47 после 0.
    @CsvFileSource(files = "src/test/resources/PayTestCase")
    public void methodPayValues(int input, int expected) {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);
        savingAccount.pay(input);

        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }*/

    @Test
    public void methodPayValue() {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);

        Assertions.assertTrue(savingAccount.pay(94));
    }

    @Test
    public void balanceAfterPay(){

        SavingAccount savingAccount = new SavingAccount(1_000,5,1_500,1);

        savingAccount.pay(500);

        Assertions.assertEquals(500,savingAccount.getBalance());
    }

    @Test
    public void balanceAfterPayWithFalse(){
        SavingAccount savingAccount = new SavingAccount(1_000,5,1_500,1);

        savingAccount.pay(999);

        Assertions.assertEquals(1_000,savingAccount.getBalance());
    }


    @Test
    public void ifPaySumMoreMinBalance() {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);

        Assertions.assertFalse(savingAccount.pay(96));
    }


    /*@ParameterizedTest
=======
    }

    @Test
    public void ifPaySumMoreMinBalance() {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);
        savingAccount.pay(96);

        int actual = savingAccount.getBalance();
        int expected = 4;

        Assertions.assertEquals(expected, actual);
    }
    //граничные значения и валидное значение //нужно добавить <= на 75 строке вместо = (метод add)//
    // ??возможно нужно ограничить % за остаток на счету maxbalance??//??считает коряво из за int, а не double??
    @CsvFileSource(files = "src/test/resources/YearChangeTestCase")
    public void methodYearChangeValues(int input, int rate, int expected) {
        SavingAccount savingAccount = new SavingAccount(input, 0, 500, rate);
//        savingAccount.add(input);
//        savingAccount.setRate(rate);

        int actual = savingAccount.yearChange();

        Assertions.assertEquals(expected, actual);
<<<<<<< HEAD
    }*/

    @Test
    public void yearChangeIfNegativeBalance() {
        SavingAccount savingAccount = new SavingAccount(-100, -200, 500, 500);

        int actual = 0;
        int expected = savingAccount.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    @Test //отрицательный процент
    public void setRateIsNegative() {
        SavingAccount savingAccount = new SavingAccount(200, 0, 500, 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {savingAccount.setRate(-30);});
    }


    @Test
    public void setRateIsPositive(){

        SavingAccount savingAccount = new SavingAccount(200,0,500,1);
        savingAccount.setRate(15);

        Assertions.assertEquals(15,savingAccount.getRate());
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

}

