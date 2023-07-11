package org.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTest {


    /**
     * Проверка исключения IllegalArgumentException.
     * Rate не может быть отрицательной (кредитная ставка)
     */
    @Test
    public void checkIllegalArgumentExceptionRate() {
        CreditAccount account = new CreditAccount(200, 1000, -5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.getRate();
        }); //Исключение выкидывает IllegalArgumentException, но тест не проходит.
    }

    /**
     * Проверка исключения IllegalArgumentException.
     * CreditLimit не может быть отрицательной (кредитный лимит)
     */
    @Test
    public void checkIllegalArgumentExceptionCreditLimit() {
        CreditAccount account = new CreditAccount(200, -1000, 5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.getCreditLimit();
        }); //Не ловит IllegalArgumentException
    }

    /**
     * Проверка исключения IllegalArgumentException.
     * InitialBalance не может быть отрицательной (начальный баланс)
     */
    @Test
    public void checkIllegalArgumentExceptionInitialBalance() {
        CreditAccount account = new CreditAccount(-200, 1000, 5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.getBalance();
        }); //Не ловит IllegalArgumentException
    }

    /**
     * Проверка pay. Сумма баланса должна быть положительной.
     */
    @Test
    public void checkPayWithValid() {
        CreditAccount account = new CreditAccount(0, 1000, 5);

        account.add(1_000);
        account.pay(500);
        Assertions.assertEquals(500, account.getBalance());
    } //Минусовой баланс. Если трата меньше, чем сумма на балансе, то можно умножить на -1.

    /**
     * Проверка pay. Сумма на балансе должна уменьшаться и быть положительной.
     */
    @Test
    public void checkPayWhenBalansMoreZero() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 5);

        account.pay(500);
        Assertions.assertEquals(500, account.getBalance());
    }

    /**
     * Проверка pay, когда покупка больше, чем сумма на балансе, но в пределах кред. лимита.
     * Баланс может быть отрицательным.
     * Покупка должна быть в пределах кредитного лимита.
     */
    @Test
    public void checkPayWhenCreditLimitEnough() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 5);

        Assertions.assertTrue(account.pay(1_500));
    }

    /**
     * Проверка pay, когда покупка меньше, чем сумма на балансе, но в пределах кред. лимита.
     * Баланс может быть отрицательным.
     * Покупка должна быть в пределах кредитного лимита.
     */
    @Test
    public void checkPayWhenBalanceEnough() {
        CreditAccount account = new CreditAccount(1_000, 1_000, 5);

        Assertions.assertTrue(account.pay(500));
    }

    /**
     * Проверка pay, когда покупка больше, чем кредитный лимит.
     * Баланс может быть отрицательным.
     * Должно вернуть false
     */
    @Test
    public void checkPayWhenCreditLimitNotsEnough() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertFalse(account.pay(5_000));
    }

    /**
     * Проверка pay.
     * 1 рубль, есть кредитный лимит.
     * Покупка должна быть в пределах кредитного лимита.
     */
    @Test
    public void checkPayWithChikenRoupAndPlusInCreditLimit() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertTrue(account.pay(1));
    }

    /**
     * Проверка pay.
     * 1 рубль, нет кредитного лимита.
     * Покупка должна быть в пределах кредитного лимита.
     */
    @Test
    public void checkPayWithoutCreditLimit() {
        CreditAccount account = new CreditAccount(0, 0, 5);

        Assertions.assertFalse(account.pay(1));
    }

    /**
     * Проверка pay.
     * Большие суммы. Есть кредитный лимит.
     * Покупка должна быть в пределах кредитного лимита.
     */
    @Test
    public void checkPayWithAbigMoney() {
        CreditAccount account = new CreditAccount(0, 1_000_000_000, 5);

        Assertions.assertTrue(account.pay(1_000_000));
    }

    /**
     * Проверка pay.
     * Граничащие значения. Кредитный лимит.
     * Покупка выходит за кредитный лимит.
     */
    @Test
    public void checkSmallPay() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertFalse(account.pay(1_001));
    }

    /**
     * Проверка pay.
     * Граничащие значения. Кредитный лимит.
     * Покупка не выходит за кредитный лимит.
     */
    @Test
    public void checkPay() {
        CreditAccount account = new CreditAccount(0, 1_000, 5);

        Assertions.assertTrue(account.pay(999));
    }

    /**
     * Проверка pay.
     * Покупка на 0.
     * Покупка не выходит за кредитный лимит.
     */
    @Test
    public void checkPayZero() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        Assertions.assertTrue(account.pay(0)); //Я б тут принимала оплату в 0 рублей. Либо тут нужно оповещение, что сумма должна быть больше нуля.
    }

    /**
     * Проверка pay.
     * Покупка на 0. Баланс не должен изменяться.
     */
    @Test
    public void checkBalanceWhenPayZero() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.pay(0);

        Assertions.assertEquals(100, account.getBalance());
    }

    /**
     * Проверка pay.
     * Десятичные значения.
     */
    @Test
    public void checkBalanceWhenPayDouble() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.pay(50.5);

        Assertions.assertEquals(49.5, account.getBalance());
    }//Поменять int на double

    /**
     * Проверка pay.
     * Десятичные значения с нулем.
     */
    @Test
    public void checkBalanceWhenPayDouble() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.pay(50.0);

        Assertions.assertEquals(50, account.getBalance());
    }//Поменять int на double


    /**
     * Проверка CreditLimit.
     * Десятичные значения с нулем.
     */
    @Test
    public void checkThanCreditLimitIsCurrent() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        Assertions.assertEquals(1_000, account.getCreditLimit());
    }

    /**
     * Проверка add.
     * С нулем.
     */
    @Test
    public void checkAddWithZero() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.add(0);

        Assertions.assertEquals(100, account.getBalance());
    }

    /**
     * Проверка add. Уже есть сумма на балансе.
     * С маленьким значением.
     */
    @Test
    public void checkAddWithSmall() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.add(1);

        Assertions.assertEquals(101, account.getBalance());
    } //Не учитывает факт того, что на балансе уже есть сумма. Надо бы добавить в класс add и учесть в pay

    /**
     * Проверка add. Уже есть сумма на балансе.
     * С большими значением.
     */
    @Test
    public void checkAddWithLarge() {
        CreditAccount account = new CreditAccount(100, 1_000, 5);

        account.add(1_000_000_000);

        Assertions.assertEquals(1_000_000_100, account.getBalance());
    }//Не учитывает факт того, что на балансе уже есть сумма. Надо бы добавить в класс add и учесть в pay

    /**
     * Проверка add. Нет суммы на балансе.
     */
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

    /**
     * Проверка yearChange.
     * Отрицательный баланс.
     */
    @Test
    public void yearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(4_000);
        int excepted = account.getBalance() / 100 * account.getRate();

        Assertions.assertEquals(excepted, account.yearChange());
    }

    /**
     * Проверка yearChange.
     * Положительный баланс.
     * При любой ставке должно быть 0, так как расчет только
     * на отрицательный баланс.
     */
    @Test
    public void yearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(4_000);


        Assertions.assertEquals(0, account.yearChange());//Надо if, на случай, если баланс > 0
    }

    /**
     * Проверка yearChange.
     * Отрицательный баланс.
     * При любой ставке должно быть 0, так как расчет только
     * на отрицательный баланс.
     */
    @Test
    public void yearChangeNegativeBalanceWithSmallRate() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                1
        );

        account.pay(4_000);
        int excepted = account.getBalance() / 100 * account.getRate();

        Assertions.assertEquals(excepted, account.yearChange());
    }

    /**
     * Проверка yearChange.
     * Отрицательный баланс.
     * При любой ставке должно быть 0, так как расчет только
     * на отрицательный баланс.
     */
    @Test
    public void yearChangeNegativeBalanceWithHightRate() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                100
        );

        account.pay(4_000);
        int excepted = account.getBalance() / 100 * account.getRate();

        Assertions.assertEquals(excepted, account.yearChange());
    }
}