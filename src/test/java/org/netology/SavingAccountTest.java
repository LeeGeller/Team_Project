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


    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/InitialBalanceTest")
    public void initialBalance(int init, int expected) {
        SavingAccount savingAccount = new SavingAccount(init, 5, 500, 1);

        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void initialBalanceLessMinBalance() { //нужно дописать исключения при initialBalance<minBalance
        SavingAccount savingAccount = new SavingAccount(10, 20, 500, 1);
        int excepted = 10;
        int actual = savingAccount.getBalance();
        Assertions.assertEquals(excepted, actual);
    }


    @Test
    public void initialBalanceMoreMaxBalance() { //нужно дописать исключения при initialBalance>maxBalance
        SavingAccount savingAccount = new SavingAccount(600, 20, 500, 1);
        int excepted = 600;
        int actual = savingAccount.getBalance();
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void minBalanceMoreMaxBalance() {
        SavingAccount savingAccount = new SavingAccount(30, 20, 19, 1);
        int excepted = 20;
        int actual = savingAccount.getMinBalance();
        Assertions.assertEquals(excepted, actual);
    }


    @Test
    public void minBalanceMoreMax() {  //тест не пройден !!!!!! дописать исключения при maxBalance<minBalance
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

        int actual = savingAccount.getBalance();
        int expected = 100;

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void addBalanceToMax() { //нужно добавить <= вместо < в метод add там где if true
        SavingAccount savingAccount = new SavingAccount(50, 5, 500, 1);
        savingAccount.add(450);

        int actual = savingAccount.getBalance();
        int expected = 50;

        Assertions.assertEquals(expected, actual);
    }


    @ParameterizedTest
    //граничные значения и валидное значение;//тест не проходит ,нужно добавить ||balance-amount<minBalance на 47 после 0.
    @CsvFileSource(files = "src/test/resources/PayTestCase")
    public void methodPayValues(int input, int expected) {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);
        savingAccount.pay(input);

        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ifPaySumMoreMinBalance() {
        SavingAccount savingAccount = new SavingAccount(100, 5, 500, 1);
        savingAccount.pay(96);

        int actual = savingAccount.getBalance();
        int expected = 4;

        Assertions.assertEquals(expected, actual);
    }



    @ParameterizedTest
    //граничные значения и валидное значение //нужно добавить <= на 75 строке вместо = (метод add)//
    // ??возможно нужно ограничить % за остаток на счету maxbalance??//??считает коряво из за int, а не double??
    @CsvFileSource(files = "src/test/resources/YearChangeTestCase")
    public void methodYearChangeValues(int input, int rate, int expected) {
        SavingAccount savingAccount = new SavingAccount(input, 0, 500, rate);
//        savingAccount.add(input);
//        savingAccount.setRate(rate);

        int actual = savingAccount.yearChange();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void yearChangeIfNegativeBalance() {   //тест не проходит!!! чтобы исправить, нужно заменить код в методе YearChange на код под этим тестом
        SavingAccount savingAccount = new SavingAccount(-100, -200, 500, 500);

        int actual = savingAccount.yearChange();
        int expected = -500;

        Assertions.assertEquals(expected, actual);
    }
//      if (balance / 100 * rate < 0){
//        return 0;}
//        else {
//        return balance / 100 * rate;
//    }
//}
    @Test //отрицательный процент
    public void setRateIsNegative() {
        SavingAccount savingAccount = new SavingAccount(200, 0, 500, 0);
        savingAccount.setRate(-15);

        int actual = savingAccount.yearChange();
        int expected = -30;

        Assertions.assertEquals(expected, actual);
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
