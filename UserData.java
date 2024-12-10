package org.example;

public class UserData {
    String email;
    int logs;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getLogs() {
        return logs;
    }

    public void totalLogs() {
        this.logs++;
    }

    public boolean extensionEnabled(boolean enabled) {
        return enabled;
    }
}

