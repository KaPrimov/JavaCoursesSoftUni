package p02_services;

public class CompositeNotifinactionService implements NotificationService {

    private NotificationService[] notificationServices;
    private boolean isActive;

    public CompositeNotifinactionService(boolean isActive, NotificationService... notificationServices) {
        this.notificationServices = notificationServices;
        this.isActive = isActive;
    }

    @Override
    public void sendNotification() {
        for (NotificationService notificationService : this.notificationServices) {
            if(notificationService.isActive()) {
                notificationService.sendNotification();

            }
        }
    }

    @Override
    public boolean isActive() {
        return  this.isActive;
    }

    @Override
    public void setActive(boolean active) {
        this.isActive = active;
    }
}
