package p02_services;

public class Main {

    public static void main(String[] args) {
        CompositeNotifinactionService service = new CompositeNotifinactionService(true,
                new EmailNotificationService(true), new SmsNotificationService(true));

        OnlineStoreOrder onlineStoreOrder = new OnlineStoreOrderImpl(service);
        onlineStoreOrder.process();
    }

}
