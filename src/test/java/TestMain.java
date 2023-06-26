import org.calculatorApp.Main;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Using basicHandler")
    public class testUsingBasicHandler {

        @Test
        @Order(1)
        @DisplayName("Test add and mul operations")
        public void testAddMul() throws IOException, InterruptedException {
            String[] params = new String[1];
            params[0] = "src/test/files/basicTask1.txt"; // 2 * 3 + 4
            Double expected = 2.0 * 3.0 + 4;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Main.main(params);
            assertEquals(expected, Double.valueOf(outputStream.toString()));
        }

        @Test
        @Order(2)
        @DisplayName("Test all operations from basic")
        public void testAllOps() throws IOException, InterruptedException {
            String[] params = new String[1];
            params[0] = "src/test/files/basicTask2.txt"; // (1 + 2) * (3 - 5) / 4
            Double expected = (1.0 + 2.0) * (3.0 - 5.0) / 4;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Main.main(params);
            assertEquals(expected, Double.valueOf(outputStream.toString()));
        }

        @Test
        @Order(3)
        @DisplayName("Test all operations from basic in different order")
        public void testAllOpsChaotic() throws IOException, InterruptedException {
            String[] params = new String[1];
            params[0] = "src/test/files/basicTask2Chaotic.txt"; // (1 + 2) * (3 - 5) / 4
            Double expected = (1.0 + 2.0) * (3.0 - 5.0) / 4;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Main.main(params);
            assertEquals(expected, Double.valueOf(outputStream.toString()));
        }

        @Test
        @Order(4)
        @DisplayName("Test division by zero breaks nothing")
        public void testDivisionByZero() throws IOException, InterruptedException {
            String[] params = new String[1];
            params[0] = "src/test/files/basicTask3.txt"; // 1 / 0
            Double expected = 1.0 / 0;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Main.main(params);
            assertEquals(expected, Double.valueOf(outputStream.toString()));
        }

    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Using advancedHandler")
    public class testUsingAdvancedHandler {

        @Test
        @Order(1)
        @DisplayName("Test inherited handler supported")
        public void TestInheritedHandlerSupported() throws IOException, InterruptedException {
            String[] params = new String[1];
            params[0] = "src/test/files/advancedTask1.txt"; // (1 + 2) * (3 - 5) / 4
            Double expected = (1.0 + 2.0) * (3.0 - 5.0) / 4;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Main.main(params);
            assertEquals(expected, Double.valueOf(outputStream.toString()));
        }

        @Test
        @Order(2)
        @DisplayName("Test abs, sqr, sqrt operations")
        public void TestSqrSqrt() throws IOException, InterruptedException {
            String[] params = new String[1];
            params[0] = "src/test/files/advancedTask2.txt"; // sqrt(abs(zero - 3^2 - 4^2))
            Double expected = Math.sqrt(Math.abs(- (3.0 * 3.0) - 4.0 * 4.0));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Main.main(params);
            assertEquals(expected, Double.valueOf(outputStream.toString()));
        }

        @Test
        @Order(3)
        @DisplayName("Test trigonometry operations")
        public void TestTrigonometry() throws IOException, InterruptedException {
            String[] params = new String[1];
            params[0] = "src/test/files/advancedTask3.txt"; // acos(1 / (sin(3)^2 + cos(3)^2 + tg(4)^2))
            Double expected = Math.acos(1 / (Math.sin(3) * Math.sin(3) + Math.cos(3) * Math.cos(3) + Math.tan(4) * Math.tan(4)));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Main.main(params);
            assertEquals(expected, Double.valueOf(outputStream.toString()));
        }

    }

}
