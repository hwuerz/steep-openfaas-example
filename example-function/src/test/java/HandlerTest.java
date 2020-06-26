import de.fhg.igd.steep.faas.Handler;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandlerTest {

    @Test public void handlerIsNotNull() {
        Handler handler = new Handler();
        assertTrue("Expected handler not to be null", handler != null);
    }

}
