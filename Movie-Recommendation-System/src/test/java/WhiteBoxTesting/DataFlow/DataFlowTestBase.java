package WhiteBoxTesting.DataFlow;

import mainPackage.AppExceptions;
import mainPackage.ErrorCode;
import org.junit.Before;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public abstract class DataFlowTestBase {
    protected File tempFile;

    @Before
    public void setUp() throws IOException {
        tempFile = File.createTempFile("test_movies", ".txt");
        tempFile.deleteOnExit();
    }

    protected void writeToFile(String content) throws IOException {
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
    }

    protected void assertDoesNotThrow(Runnable runnable, String message) {
        try {
            runnable.run();
        } catch (Throwable t) {
            fail(message + ": " + t.getMessage());
        }
    }

    protected void assertValidationException(Runnable runnable, ErrorCode expectedErrorCode, String expectedMessage) {
        try {
            runnable.run();
            fail("Expected AppExceptions was not thrown.");
        } catch (AppExceptions e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedErrorCode, e.getErrorCode());
        }
    }
}