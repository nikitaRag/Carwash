package com.example.nekit.start;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.nekit.start", appContext.getPackageName());
    }
    @Test
        public void doInBackground() throws Exception{
        DataBase db = new DataBase();
        db.execute("test.php","test=test");
        assertEquals(db.get(),"success");
    }
    @Test
    public void getDate() throws Exception{
        assertEquals(Calendar.getDate(),"2017-12-15");
    }
}
