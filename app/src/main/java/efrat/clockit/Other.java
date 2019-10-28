package efrat.clockit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Other {

    public static String getCurrent(String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String current = new SimpleDateFormat(pattern, Locale.getDefault()).format(cal.getTime());

        return current;
    }
}
