package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    //При отрицательной ставке должна возникать исключительная ситуация
    public void shouldThrowsExceptionWhenNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -5
            );
        });
    }

    @Test
    //При нулевой ставке не должна возникать исключительная ситуация, т.к. по условиям ставка
    // - неотрицательное число
    public void shouldNotThrowsExceptionWhenZeroRate() {

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                0
        );

        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(0, account.getRate());
    }

    @Test
    //При положительной ставке не должна возникать исключительная ситуация, т.к. по условиям ставка
    // - неотрицательное число
    public void shouldNotThrowsExceptionWhenPositiveRate() {

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если минимальный баланс больше максимального, должна возникать исключительная ситуация
    public void shouldThrowsExceptionWhenMinBalanceMoreThanMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    500,
                    5
            );
        });
    }

    @Test
    //Если минимальный баланс меньше максимального, не должна возникать исключительная ситуация
    public void shouldNotThrowsExceptionWhenMinBalanceLessThanMaxBalance() {

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если минимальный баланс равен максимальному, не должна возникать исключительная ситуация
    //т.к. по условиям - баланс должен быть в пределах минимального и максимального включительно,
    // т.е., по идее, он может быть равен минимальному и максимальному балансу одновременно
    public void shouldNotThrowsExceptionWhenMinBalanceAndMaxBalanceEqual() {

        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                1_000,
                5
        );

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(1_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если начальный баланс больше максимального, должна возникать исключительная ситуация
    public void shouldThrowsExceptionWhenInitialBalanceMoreThanMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    11_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    //Если начальный баланс меньше максимального, не должна возникать исключительная ситуация
    public void shouldNotThrowsExceptionWhenInitialBalanceLessThanMaxBalance() {

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если начальный баланс равен максимальному, не должна возникать исключительная ситуация
    public void shouldNotThrowsExceptionWhenInitialBalanceAndMaxBalanceEqual() {

        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(10_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если начальный баланс меньше минимального, должна возникать исключительная ситуация
    public void shouldThrowsExceptionWhenInitialBalanceLessThanMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    //Если начальный баланс больше минимального, не должна возникать исключительная ситуация
    public void shouldNotThrowsExceptionWhenInitialBalanceMoreThanMinBalance() {

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если начальный баланс равен минимальному, не должна возникать исключительная ситуация
    public void shouldNotThrowsExceptionWhenInitialBalanceAndMinBalanceEqual() {

        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если минимальный баланс отрицательный, должна возникать исключительная ситуация
    public void shouldThrowsExceptionWhenNegativeMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    -1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    //Если начальный баланс положительный, не должна возникать исключительная ситуация
    public void shouldNotThrowsExceptionWhenPositiveMinBalance() {

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Если минимальный баланс равен нулю, не должна возникать исключительная ситуация
    public void shouldNotThrowsExceptionWhenZeroMinBalance() {

        SavingAccount account = new SavingAccount(
                1_000,
                0,
                10_000,
                5
        );

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(0, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    //Не должна проходить оплата, если сумма покупки отрицательная
    //итоговый баланс должен быть равен первоначальному
    public void shouldNotPayWhenNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(-500));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    //Не должна проходить оплата, если сумма покупки равна нулю
    //итоговый баланс должен быть равен первоначальному
    public void shouldNotPayWhenZeroAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    //Не должна проходить оплата, если итоговый баланс меньше минимального
    //итоговый баланс должен быть равен первоначальному
    public void shouldNotPayWhenBalanceLessThenMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(1_500));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    //Должна проходить оплата, если итоговый баланс больше минимального
    //Итоговый баланс равен первоначальному балансу минус сумма оплаты
    public void shouldPayWhenBalanceMoreThenMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.pay(500));
        Assertions.assertEquals(1_500, account.getBalance());
    }

    @Test
    //Должна проходить оплата, если итоговый баланс равен минимальному, т.к. по условиям, баланс должен быть
    // в пределах минимального и максимального включительно
    //Итоговый баланс равен первоначальному балансу минус сумма оплаты
    public void shouldPayWhenBalanceAndMinBalanceEqual() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.pay(1_000));
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    //Не должно проходить пополнение, если сумма поплнения отрицательная
    //Итоговый баланс должен быть равен первоначальному
    public void shouldNotAddWhenNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertEquals(false, account.add(-500));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    //Не должно проходить пополнение, если сумма поплнения равна нулю
    //Итоговый баланс должен быть равен первоначальному
    public void shouldNotAddWhenZeroAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertEquals(false, account.add(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    //Должнo проходить пополнение, если итоговый баланс меньше максимального,
    // итоговый баланс должен быть равен первоначальному плюс сумма пополнения
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.add(3_000));
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    //Должнo проходить пополнение, если итоговый баланс равен максимальному,
    // итоговый баланс должен быть равен максимальному
    public void shouldAddWhenBalanceAndMaxBalanceEqual() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.add(8_000));
        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    //Не должнo проходить пополнение, если итоговый баланс больше максимального,
    // итоговый баланс должен быть равен первоначальному
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.add(9_000));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    //Проверяем, что округление выполняется праильно
    public void yearChange() {
        SavingAccount account = new SavingAccount(
                2_555,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(127, account.yearChange());
    }
}
