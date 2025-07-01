package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

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

    // ========== Конструктор ==========

    @Test
    public void shouldThrowExceptionForNegativeRate() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new CreditAccount(1_000, 5_000, -10)
        );

        Assertions.assertEquals(
                "Накопительная ставка не может быть отрицательной, а у вас: -10",
                exception.getMessage()
        );
    }

    @Test
    public void shouldThrowExceptionForNegativeRateZero() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new CreditAccount(1_000, 5_000, 0)
        );

        Assertions.assertEquals(
                "Накопительная ставка не может быть отрицательной, а у вас: 0",
                exception.getMessage()
        );
    }

    @Test
    public void shouldThrowExceptionForNegativeInitialBalance() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new CreditAccount(-1_000, 5_000, 15)
        );

        Assertions.assertEquals(
                "Начальный баланс не может быть отрицательным",
                exception.getMessage()
        );
    }

    @Test
    public void shouldThrowExceptionForNegativeCreditLimit() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new CreditAccount(1_000, -5_000, 15)
        );

        Assertions.assertEquals(
                "Кредитный лимит не может быть отрицательным",
                exception.getMessage()
        );
    }

    @Test
    public void shouldCreateAccountWithValidParameters() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(5_000, account.getCreditLimit());
    }

    // ========== Метод pay() ==========

    @Test
    public void shouldSuccessfullyPayWithinLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        boolean result = account.pay(3_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void shouldRejectPaymentExceedingLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        boolean result = account.pay(7_000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldRejectNegativePayment() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        boolean result = account.pay(-500);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldRejectZeroPayment() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        boolean result = account.pay(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldHandlePaymentAtCreditLimitBoundary() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean result = account.pay(5_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-5_000, account.getBalance());
    }

    // ========== Метод add() ==========

    @Test
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean result = account.add(3_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToExistingBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        boolean result = account.add(500);

        Assertions.assertTrue(result);
        Assertions.assertEquals(1_500, account.getBalance());
    }

    @Test
    public void shouldRejectNegativeAddition() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        boolean result = account.add(-500);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldRejectZeroAddition() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        boolean result = account.add(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    // ========== Метод yearChange() ==========

    @Test
    public void shouldCalculateInterestForNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15
        );

        int result = account.yearChange();

        Assertions.assertEquals(-30, result);
    }

    @Test
    public void shouldReturnZeroForPositiveBalance() {
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldReturnZeroForZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldCalculateInterestForSmallNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -105,
                5_000,
                17
        );

        int result = account.yearChange();

        Assertions.assertEquals(-17, result);
    }

    // ========== Граничные условия ==========

    @Test
    public void shouldHandleMaximumValues() {
        CreditAccount account = new CreditAccount(
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                100
        );

        boolean payResult = account.pay(Integer.MAX_VALUE);

        Assertions.assertTrue(payResult);
        Assertions.assertEquals(0, account.getBalance());
    }

}
