import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAppTest {

    private final LoginApp loginApp = new LoginApp();

    @Test
    public void testValidEmail() {
        String email = "zeeshan@topg.com";
        String result = loginApp.authenticateUser(email);
        assertNotNull(result, "Login should be successful with a valid email.");
    }

    @Test
    public void testInvalidEmail() {
        String email = "invalid@example.com";
        String result = loginApp.authenticateUser(email);
        assertNull(result, "Login should fail with an incorrect email.");
    }

    @Test
    public void testEmptyEmail() {
        String email = "";
        String result = loginApp.authenticateUser(email);
        assertNull(result, "Login should fail when the email field is empty.");
    }

    @Test
    public void testSqlInjectionInEmail() {
        String email = "admin' OR '1'='1";
        String result = loginApp.authenticateUser(email);
        assertNull(result, "Login should fail if there is an SQL injection attempt in the email field.");
    }

    @Test
    public void testNonExistentEmail() {
        String email = "nonExistentUser@example.com";
        String result = loginApp.authenticateUser(email);
        assertNull(result, "Login should fail if the email does not exist in the database.");
    }
}
