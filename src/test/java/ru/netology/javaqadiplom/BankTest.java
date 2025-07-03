package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    @Test

    // тесты на вводимое количество

    //Не должен проходить перевод между любыми счетами, если сумма перевода отрицательная
    //итоговые балансы обоих счетов должны быть равны первоначальным
    public void shouldNotTransferWhenNegativeAmount() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5
        );

        SavingAccount to = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, -2_000));
        Assertions.assertEquals(9_000, from.getBalance());
        Assertions.assertEquals(2_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод между любыми счетами, если сумма перевода равна 0
    //итоговые балансы обоих счетов должны быть равны первоначальным
    public void shouldNotTransferWhenZeroAmount() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5
        );

        SavingAccount to = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 0));
        Assertions.assertEquals(9_000, from.getBalance());
        Assertions.assertEquals(2_000, to.getBalance());
    }


    // тесты на переводы между сберегательными счетами
    @Test
    //Должен проходить перевод между сберегательными счетами, если:
    // сумма перевода положительная,
    // итоговый баланс счета from больше минимального,
    // итоговый боланс счета to меньше максимального

    //итоговый баланс счета from должен уменьшиться на сумму перевода
    //итоговый боланс счета to должен увеличиться на сумму перевода
    public void shouldTransferFromSavingToSavingWhenBalancesUnderLimits() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5
        );

        SavingAccount to = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(7_000, from.getBalance());
        Assertions.assertEquals(4_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод между сберегательными счетами, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from меньше минимального (это не ОК),
    // итоговый боланс счета to меньше максимального (это ОК)

    //итоговые балансы обоих счетов должны быть равны первоначальным
    public void shouldNotTransferFromSavingToSavingWhenBalanceFromLessThanMin() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        SavingAccount to = new SavingAccount(
                7_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод между сберегательными счетами, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from больше минимального (это ОК),
    // итоговый боланс счета to больше максимального (это не ОК)

    //итоговые балансы обоих счетов должны быть равны первоначальным
    public void shouldNotTransferFromSavingToSavingWhenBalanceToMoreThanMax() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        SavingAccount to = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(3_000, from.getBalance());
        Assertions.assertEquals(10_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод между сберегательными счетами, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from меньше минимального (это не ОК),
    // итоговый боланс счета to больше максимального (это не ОК)

    //итоговые балансы обоих счетов должны быть равны первоначальнымм
    public void shouldNotTransferFromSavingToSavingWhenBalancesAboveLimits() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        SavingAccount to = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(10_000, to.getBalance());
    }


    // тесты на переводы между кредитными счетами

    @Test
    //Должен проходить перевод между кредитными счетами, если:
    // сумма перевода положительная,
    // итоговый баланс счета from больше кредитного лимита,

    //итоговый баланс счета from должен уменьшиться на сумму перевода
    //итоговый боланс счета to должен увеличиться на сумму перевода
    public void shouldTransferFromCreditToCreditWhenBalanceFromMoreThanLimit() {
        Bank bank = new Bank();

        CreditAccount from = new CreditAccount(
                9_000,
                1_000,
                5
        );

        CreditAccount to = new CreditAccount(
                1_000,
                2_000,
                5
        );

        Assertions.assertEquals(true, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(7_000, from.getBalance());
        Assertions.assertEquals(3_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод между кредитными счетами, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from меньше кредитного лимита (это не ОК),

    //итоговые балансы обоих счетов должны быть равны первоначальным

    public void shouldNotTransferFromCreditToCreditWhenBalanceFromLessThanLimit() {
        Bank bank = new Bank();

        CreditAccount from = new CreditAccount(
                0,
                1_000,
                5
        );

        CreditAccount to = new CreditAccount(
                1_000,
                2_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(0, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }


    // тесты на переводы со сберегательного на кредитный

    @Test
    //Должен проходить перевод со сберегательного счета на кредитный, если:
    // сумма перевода положительная,
    // итоговый баланс счета from больше минимального

    //итоговый баланс счета from должен уменьшиться на сумму перевода
    //итоговый боланс счета to должен увеличиться на сумму перевода
    public void shouldTransferFromSavingToCreditWhenBalanceFromMoreThanMin() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5
        );

        CreditAccount to = new CreditAccount(
                1_000,
                2_000,
                5
        );

        Assertions.assertEquals(true, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(7_000, from.getBalance());
        Assertions.assertEquals(3_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод со сберегательного счета на кредитный, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from меньше минимального (это не ОК),

    //итоговые балансы обоих счетов должны быть равны первоначальным

    public void shouldNotTransferFromSavingToCreditWhenBalanceFromLessThanMin() {
        Bank bank = new Bank();

        SavingAccount from = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        CreditAccount to = new CreditAccount(
                1_000,
                2_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }

    // тесты на переводы с кредитного счета на сберегательный

    @Test
    //Должен проходить перевод с кредитного счета на сберегательный, если:
    // сумма перевода положительная,
    // итоговый баланс счета from больше кредитного лимита,
    // итоговый баланс счета to меньше максимального

    //итоговый баланс счета from должен уменьшиться на сумму перевода
    //итоговый боланс счета to должен увеличиться на сумму перевода
    public void shouldTransferFromCreditToSavingWhenBalancesUnderLimits() {
        Bank bank = new Bank();

        CreditAccount from = new CreditAccount(
                1_000,
                2_000,
                5
        );

        SavingAccount to = new SavingAccount(
                7_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(-1_000, from.getBalance());
        Assertions.assertEquals(9_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод со сберегательного счета на кредитный, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from меньше кредитного лимита (это не ОК),
    // итоговый баланс счета to меньше максимального (это ОК)

    //итоговые балансы обоих счетов должны быть равны первоначальным
    public void shouldNotTransferFromCreditToSavingWhenBalanceFromLessThanCreditLimit() {
        Bank bank = new Bank();

        CreditAccount from = new CreditAccount(
                1_000,
                2_000,
                5
        );

        SavingAccount to = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 4_000));
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(5_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод с кредитного счета на сберегательный, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from больше кредитного лимита (это ОК),
    // итоговый баланс счета to больше максимального (это не ОК)

    //итоговые балансы обоих счетов должны быть равны первоначальным
    public void shouldNotTransferFromCreditToSavingWhenBalanceToMoreThanMax() {
        Bank bank = new Bank();

        CreditAccount from = new CreditAccount(
                3_000,
                2_000,
                5
        );

        SavingAccount to = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(3_000, from.getBalance());
        Assertions.assertEquals(9_000, to.getBalance());
    }

    @Test
    //Не должен проходить перевод с кредитного счета на сберегательный, если:
    // сумма перевода положительная (это ОК),
    // итоговый баланс счета from меньше кредитного лимита (это не ОК),
    // итоговый баланс счета to больше максимального (это не ОК)

    //итоговые балансы обоих счетов должны быть равны первоначальным
    public void shouldNotTransferFromCreditToSavingWhenBalancesAboveLimits() {
        Bank bank = new Bank();

        CreditAccount from = new CreditAccount(
                1_000,
                2_000,
                5
        );

        SavingAccount to = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, bank.transfer(from, to, 4_000));
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(9_000, to.getBalance());
    }
}

