import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAppTest {

    private final LoginApp loginApp = new LoginApp();

    @Test
    public void testValidEmailAndPassword() {
        String email = "zeeshan@topg.com";
        String password = "password202";
        String result = loginApp.authenticateUser(email, password);
        assertNotNull(result, "Login should be successful with a valid email and password.");
    }

    @Test
    public void testInvalidEmail() {
        String email = "ffd@ddd.com";
        String password = "2212321";
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Incorrect email.");
    }

    @Test
    public void testEmptyEmail() {
        String email = "";
        String password = "anyPassword";  // Any password
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Email field is empty.");
    }

    @Test
    public void testSqlInjectionInEmail() {
        String email = "admin' OR '1'='1";
        String password = "anyPassword";
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "SQL injection attempt in the email field.");
    }

    @Test
    public void testNonExistentEmail() {
        String email = "nonExistentUser@example.com";
        String password = "anyPassword";
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Email does not exist in the database.");
    }

    @Test
    public void testInvalidPassword() {
        String email = "zeeshan@topg.com";  // Valid email
        String password = "invalidPassword";  // Invalid password
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Login should fail with a valid email but invalid password.");
    }
}
