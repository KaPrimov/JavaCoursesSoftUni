package app.gamestore.utils;

import app.gamestore.dto.bindingDtos.user.LoggedInUser;

public class Session {
    private static LoggedInUser loggedInUser;

    public static LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(LoggedInUser newUser) {
        loggedInUser = newUser;
    }
}
