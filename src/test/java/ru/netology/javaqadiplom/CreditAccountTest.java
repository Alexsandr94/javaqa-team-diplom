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
                200,
                5_000,
                15
        );
        int initialResult = account.yearChange();
        Assertions.assertEquals(0, initialResult);

        boolean paymentResult = account.pay(400);
        Assertions.assertTrue(paymentResult);
        Assertions.assertEquals(-200, account.getBalance());

        int negativeResult = account.yearChange();
        Assertions.assertEquals(-30, negativeResult);
        Assertions.assertTrue(negativeResult < 0);
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

    @Test
    public void shouldDetectRoundingIssueInYearChange() {
        // Тестируем различные отрицательные балансы для выявления проблем с округлением

        // Тест 1: -199 с 15% должен давать -30 (а не -15)
        CreditAccount account1 = new CreditAccount(1, 5_000, 15);
        account1.pay(200); // Баланс станет -199
        Assertions.assertEquals(-199, account1.getBalance());

        // Правильный расчет: -199 * 0.15 = -29.85, округление до -30
        // При текущей реализации: -199 / 100 = -1, затем -1 * 15 = -15
        int result1 = account1.yearChange();
        Assertions.assertEquals(-30, result1);

        // Тест 2: -150 с 21% должен давать -32 (а не -21)
        CreditAccount account2 = new CreditAccount(50, 5_000, 21);
        account2.pay(200); // Баланс станет -150
        Assertions.assertEquals(-150, account2.getBalance());

        // Правильный расчет: -150 * 0.21 = -31.5, округление до -32
        // При текущей реализации: -150 / 100 = -1, затем -1 * 21 = -21
        int result2 = account2.yearChange();
        Assertions.assertEquals(-32, result2);

        // Тест 3: Поведение для баланса ровно -100 (граничный случай)
        CreditAccount account3 = new CreditAccount(100, 5_000, 10);
        account3.pay(200); // Баланс станет -100
        Assertions.assertEquals(-100, account3.getBalance());

        // -100 * 0.10 = -10, что должно работать корректно даже с текущей реализацией
        int result3 = account3.yearChange();
        Assertions.assertEquals(-10, result3);

        // Тест 4: Поведение для баланса менее -100 (например, -101)
        CreditAccount account4 = new CreditAccount(99, 5_000, 10);
        account4.pay(200); // Баланс станет -101
        Assertions.assertEquals(-101, account4.getBalance());

        // Правильный расчет: -101 * 0.10 = -10.1, округление до -11
        // При текущей реализации: -101 / 100 = -1, затем -1 * 10 = -10
        int result4 = account4.yearChange();
        Assertions.assertEquals(-11, result4);
    }

}
