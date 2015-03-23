package no.svv.nvdb.brobanken.apiwrite;

import no.svv.nvdb.brobanken.apiwrite.data.LoginState;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Login on the OpenAM login server using the end users LDAP username and password.
 * <p/>
 * User: Sigurd Stendal
 * Date: 16.03.15
 */
public class Authentication {

    private static Logger logger = LoggerFactory.getLogger(Authentication.class);

    public LoginState login(String username, String password, String loginServerUrl) {

        LoginState loginState = new LoginState();

        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();

            // The Login Server URL is the same URL as used when login with a browser
            HttpPost httpPost = new HttpPost(loginServerUrl);

            // Username, password and a module name is provided as HTTP parameters to the login server
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("IDToken1", username));
            nvps.add(new BasicNameValuePair("IDToken2", password));
            nvps.add(new BasicNameValuePair("module", "LDAP"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {

                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();

                // The login server will respond with http 302 if successfull
                if (statusLine.getStatusCode() == 302) {

                    // The login server will return a cookie with a name that starts with iPlanetDirectoryPro if successfull
                    Optional<Header> iPlanetCookie = getCookieHeader(response, "iPlanetDirectoryPro");
                    if (iPlanetCookie.isPresent()) {
                        try {
                            loginState.setSsoToken(cookieValue(iPlanetCookie.get()));
                            loginState.setSsoCookieName(cookieName(iPlanetCookie.get()));
                            loginState.setLoggedIn(true);
                        } catch (Exception e) {
                            logger.error("Failed while parsing cookie header: '" + iPlanetCookie + "'");
                            loginState.setLoggedIn(false);
                            loginState.setLastErrorMsg("Failed while parsing result from login server");
                        }
                    } else {
                        logger.error("Result from login server did not contain the required cookie with a sso token. Missing header should start with the string 'iPlanetDirectoryPro'");
                        loginState.setLoggedIn(false);
                        loginState.setLastErrorMsg("Failed while parsing result from login server");
                    }

                } else {
                    // Any other http response code is an error
                    logger.error("Result from login server was not http code 302");
                    loginState.setLoggedIn(false);
                    loginState.setLastErrorMsg("Failed while parsing result from login server");
                    logger.error("HTML from last response from login server:\n" + readBody(entity));
                }

                EntityUtils.consume(entity);

                return loginState;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed while communicating with login server", e);
        }

    }

    private Optional<Header> getCookieHeader(CloseableHttpResponse response, String cookieName) {
        Header[] headers = response.getAllHeaders();
        return Arrays.asList(headers).stream().filter(h -> h.getName().equals("Set-Cookie") && h.getValue().startsWith(cookieName)).findAny();
    }

    private String cookieValue(Header cookieHeader) {
        return cookieHeader.getValue().split("=")[1].split(";")[0];
    }

    private String cookieName(Header cookieHeader) {
        return cookieHeader.getValue().split("=")[0];
    }

    private StringBuilder readBody(HttpEntity entity) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line).append("\n");
        }
        in.close();
        return sb;
    }

}
