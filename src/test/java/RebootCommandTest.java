import org.redcraft.redcraftreboot.commands.RebootCommand;

import junit.framework.*;

public class RebootCommandTest extends TestCase {

    RebootCommand rebootCommand;

    protected void setUp() {
        rebootCommand = new RebootCommand();
    }

    public void testReasonParsing() {
        String[] commandArgs = new String[] { "10", "Updating", "plugins", "(maintenance)" };

        String expectedReason = "Updating plugins (maintenance)";
        String actualReason = rebootCommand.getRebootReason(commandArgs);
        assertEquals(expectedReason, actualReason);
    }

    public void testDurationParsing() {
        String[] commandArgs = new String[] { "10", "Updating", "plugins", "(maintenance)" };

        int expectedDuration = 600;
        int actualDuration = rebootCommand.getSecondsBeforeReboot(commandArgs);
        assertEquals(expectedDuration, actualDuration);
    }

}
