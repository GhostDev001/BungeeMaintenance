package de.mj.bungeemaintenance.utils;

public class Data {

    private boolean enabled;
    private String prefix;
    private String toggleState;
    private String enabledMSG;
    private String disabledMSG;
    private String allreadyEnabled;
    private String allreadyDisabled;
    private String nowEnabled;
    private String nowDisabled;
    private String disconnectMSG;

    public boolean isEnabled() {
        return enabled;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getToggleState() {
        return toggleState;
    }

    public void setToggleState(String toggleState) {
        this.toggleState = toggleState;
    }

    private String getEnabledMSG() {
        return enabledMSG;
    }

    public void setEnabledMSG(String enabledMSG) {
        this.enabledMSG = enabledMSG;
    }

    private String getDisabledMSG() {
        return disabledMSG;
    }

    public void setDisabledMSG(String disabledMSG) {
        this.disabledMSG = disabledMSG;
    }

    public String getAllreadyEnabled() {
        return allreadyEnabled;
    }

    public void setAllreadyEnabled(String allreadyEnabled) {
        this.allreadyEnabled = allreadyEnabled;
    }

    public String getAllreadyDisabled() {
        return allreadyDisabled;
    }

    public void setAllreadyDisabled(String allreadyDisabled) {
        this.allreadyDisabled = allreadyDisabled;
    }

    public String getNowEnabled() {
        return nowEnabled;
    }

    public void setNowEnabled(String nowEnabled) {
        this.nowEnabled = nowEnabled;
    }

    public String getNowDisabled() {
        return nowDisabled;
    }

    public void setNowDisabled(String nowDisabled) {
        this.nowDisabled = nowDisabled;
    }

    public String getDisconnectMSG() {
        return disconnectMSG;
    }

    public void setDisconnectMSG(String disconnectMSG) {
        this.disconnectMSG = disconnectMSG;
    }

    public String getStateMSG() {
        if (isEnabled()) return getEnabledMSG();
        else return getDisabledMSG();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
