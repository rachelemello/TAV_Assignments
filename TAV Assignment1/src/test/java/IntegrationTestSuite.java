import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ArduinoControllerTest.class, DTBSTestCases.class, ReadFromOutputBufferTest.class, ReadSpeedTorqueTest.class, SimpleUITest.class, USBConnectionReadSpeedTorqueTest.class, USBConnectionSendSensorDataTest.class, WriteToInputBufferTest.class })
public class IntegrationTestSuite {

}