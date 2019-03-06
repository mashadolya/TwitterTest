package framework;

import com.aventstack.extentreports.ExtentTest;
import framework.report.ExtentManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public abstract class BaseTest extends BaseEntity {
    protected ExtentTest extentTest;

    @BeforeClass
    public static void setUp() {
        ExtentManager.getInstance();
    }

    @AfterClass
    public static void tearDown() {
        ExtentManager.saveReporter();
        logger.makeSeparator();
    }

    @Rule
    public TestRule testRule = new TestWatcher() {

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            extentTest.pass(String.format("Test pass! :: %s", description.getMethodName()));
        }

        @Override
        protected void failed(Throwable e, Description description) {
            extentTest.fail(String.format("Test failed! :: %s", e.getMessage()));
        }

        @Override
        protected void starting(Description description) {
            extentTest = ExtentManager.createTest(String.format("Test started.... :: %s", description.getMethodName()));
        }

    };

    protected String formatLogMsg(final String message) {
        return String.format("%1$s %2$s", this.getClass().getName(), message);
    }
}
