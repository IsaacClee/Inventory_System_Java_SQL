package Model;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneConversion {

    public static boolean checkESTSchedule(LocalDateTime selectedTime){
        boolean canSchedule = true;

        ZoneId userZoneID = ZoneId.systemDefault();
        ZoneId scheduleZoneID = ZoneId.of("America/New_York");
        LocalDateTime scheduleDateTime = selectedTime.atZone(userZoneID).withZoneSameInstant(scheduleZoneID).toLocalDateTime();
        // Check if Locate Date is within EST business hours
        int selectedHour = scheduleDateTime.getHour();
        System.out.println(selectedHour);
        if(selectedHour < 8 || selectedHour > 20){
            canSchedule = false;
            JOptionPane.showMessageDialog(null,
                    "We cannot schedule this appointment. " +
                            "The appointment is outside business hours: 8:00am to 10:00pm EST");
        }

        // Check if Locale Date is a week day
         DayOfWeek day = scheduleDateTime.getDayOfWeek();
        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            canSchedule = false;
            JOptionPane.showMessageDialog(null,
                    "We cannot schedule this appointment. The appointment is not on a weekday");
        }


        return canSchedule;
    }
}
