package no.svv.nvdb.brobanken.apiwrite.data;

/**
 * User: Sigurd Stendal
 * Date: 19.03.15
 */
public class LoginState {

    private boolean isLoggedIn = false;
    private String ssoToken;
    private String ssoCookieName;
    private String lastErrorMsg;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getLastErrorMsg() {
        return lastErrorMsg;
    }

    public void setLastErrorMsg(String lastErrorMsg) {
        this.lastErrorMsg = lastErrorMsg;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    public String getSsoCookieName() {
        return ssoCookieName;
    }

    public void setSsoCookieName(String ssoCookieName) {
        this.ssoCookieName = ssoCookieName;
    }
}
