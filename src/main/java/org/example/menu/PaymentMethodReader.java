package org.example.menu;


import lombok.RequiredArgsConstructor;
import org.example.payment.PaymentMethod;
import org.example.payment.PaymentMethodFactory;

@RequiredArgsConstructor
public class PaymentMethodReader {
    private final ConsoleInput input;

    public PaymentMethod readPaymentMethod() {
        while (true) {
            int option = input.readInt("""
                    Select payment method:
                    1. Credit Card
                    2. PayPal
                    3. Gift Card
                    4. Cryptocurrency
                    """);

            switch (option) {
                case 1:
                    return readCreditCard();
                case 2:
                    return readPaypal();
                case 3:
                    return readGiftCard();
                case 4:
                    return readCrypto();
                default:
                    System.out.println("Invalid payment method.");
            }
        }
    }

    private PaymentMethod readCreditCard() {
        String cardNumber = input.readString("Card number:");
        String cardHolderName = input.readNonBlankString("Card holder name:");

        return PaymentMethodFactory.createCreditCardPayment(
                cardNumber, cardHolderName
        );
    }

    public PaymentMethod readGiftCard() {
        String code = input.readString("Gift card code:");
        double balance =
                input.readNonNegativeDouble("Gift card balance:");

        return PaymentMethodFactory.createGiftCardPayment(code, balance);
    }

    public PaymentMethod readPaypal() {
        String email = input.readString("Email:");
        return PaymentMethodFactory.createCreditPayPall(email);
    }

    private PaymentMethod readCrypto() {
        String cryptocurrency = input.readString(
                "Cryptocurrency (for example BTC, ETH or USDT):"
        ).trim();
        String walletAddress = input.readString("Public wallet address:").trim();

        return PaymentMethodFactory.createCryptoPayment(
                cryptocurrency, walletAddress
        );
    }
}
