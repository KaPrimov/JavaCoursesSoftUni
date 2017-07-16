package p02_services;

public class SmsNotificationService implements NotificationService {

    private boolean isActive;

    public SmsNotificationService(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public void setActive(boolean active) {
        this.isActive = active;
    }

    @Override
    public void sendNotification() {
        System.out.println("sms is active");
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }
}
