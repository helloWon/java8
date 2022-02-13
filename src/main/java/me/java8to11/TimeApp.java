package me.java8to11;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeApp {

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        long time = date.getTime(); // 날짜에서 시간을 가져옴?? epoch time: 기계용 시간
        System.out.println(date);
        System.out.println(time);

        // Thread safe 하지 않다.

        // Thread.sleep(1000 * 3);
        Date after3Sec = new Date();
        System.out.println(after3Sec);
        after3Sec.setTime(time); // mutable 한 객체, multi thread 환경에서 안전하지 않음
        System.out.println(after3Sec);

        // Type safe 하지 않다.
        Calendar birth = new GregorianCalendar(1995, 12, 4); // month 0부터 시작함 주의
        birth = new GregorianCalendar(1995, Calendar.NOVEMBER, 4);

        /**
         * API 실습
         */
        Date date1 = new Date();
        Instant instant = date1.toInstant();
        Date newDate = Date.from(instant); // UTC
        System.out.println("instant time: " + instant);

        // legacy api 지원
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()); // 특정 Zone 시간
        GregorianCalendar from = GregorianCalendar.from(dateTime);
        System.out.println("ZonedDateTime time: " + dateTime);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        System.out.println("Zone Changed time: " + timeZone);

        // 서버 시스템 시간
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birthday = LocalDateTime.of(1982, Month.JULY, 15, 0, 0, 0);
        System.out.println("LocalDateTime time: " + birthday);
        now.plus(10, ChronoUnit.DAYS);

        // 기간
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2022, Month.NOVEMBER, 4);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println("생일까지" + period.getMonths());
        System.out.println("생일까지" + period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        // 머신용 기간
        // Duration

        // Formatter
        LocalDateTime now1 = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("Formatter" + now1.format(MMddyyyy));

        // Parsing
        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println("Parsing" + parse);

    }
}
