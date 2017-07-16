package p02_services;

public class OnlineStoreOrderImpl implements OnlineStoreOrder {

    private NotificationService notificationServices;

    public OnlineStoreOrderImpl(NotificationService notificationServices) {
        this.notificationServices = notificationServices;
    }

    @Override
    public void process() {

        if(notificationServices.isActive()) {
            notificationServices.sendNotification();
        }

    }


}
