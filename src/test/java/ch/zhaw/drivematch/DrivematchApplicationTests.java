package ch.zhaw.drivematch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Optional: Use a specific profile for testing if needed
class DrivematchApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context is loaded without issues.
    }

    @Test
    void main() {
        // This test ensures that the main method can be run without issues.
        DrivematchApplication.main(new String[] {});
    }
}
