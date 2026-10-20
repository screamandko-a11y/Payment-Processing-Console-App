package org.example.payment;


// created factories for PayPal and gift card
public class PaymentMethodFactory {

    public static PaymentMethod createCreditCardPayment(String cardNumber,String cardHolderName){
        return new CreditCardPayment(cardNumber,cardHolderName);
    }
    public static PaymentMethod createCreditPayPall(String email){
        return new PaypalPayment(email);
    }

    public static PaymentMethod createGiftCardPayment(String code, double balance){
        return new GiftCardPayment("Gift Card", code, balance);
    }



}
