package p05_security_system;

public class PinCodeCheck extends SecurityCheck {

    private PinCardUI pinCardUI;

    public PinCodeCheck(PinCardUI pinCardUI) {
        this.pinCardUI = pinCardUI;
    }

    @Override
    public boolean validateUser() {
        int pin = pinCardUI.requestPinCode();
        if (isValid(pin)) {
            return true;
        }

        return false;
    }

    private boolean isValid(int pin) {
        return true;
    }
}
