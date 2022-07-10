package Model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneConversion {

    public static String checkESTSchedule(LocalDateTime selectedTime){
        String errorMessage = null;


        ZonedDateTime zonedLocalDateTimeStart = selectedTime.atZone(ZoneId.systemDefault());
        ZoneId userZoneID = ZoneId.systemDefault();
        ZoneId scheduleZoneID = ZoneId.of("America/New_York");
        LocalDateTime scheduleDateTime = selectedTime.atZone(userZoneID).withZoneSameInstant(scheduleZoneID).toLocalDateTime();
        // Check if Locate Date is within EST business hours


        // Check if Locale Date is a week day
         DayOfWeek day = scheduleDateTime.getDayOfWeek();
        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            errorMessage = "You cannot schedule during a weekend. Please select another day. Thank you";
        }



        return errorMessage;
    }
}
