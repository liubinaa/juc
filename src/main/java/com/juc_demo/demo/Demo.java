package com.juc_demo.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author : liubin
 * @date : 2021/7/8 13:53
 */
public class Demo {
    public static void main(String[] args) {
        LocalDate calendarDay = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse("2021-01-03");
        String groupDateType = "WEEK";
        List<String> allDate  = getAllDate(start, calendarDay, DateType.valueOf(groupDateType));
        System.out.println(allDate);

    }

    /**
     * 获取两个时间段的所有 年/月/日/周
     * @param start 开始时间
     * @param end 结束时间
     * @param dateType 枚举类型
     * @return 结果
     */
    private static List<String> getAllDate(LocalDate start, LocalDate end, DateType dateType) {
        List<String> allDate = new ArrayList<>();
        switch (dateType) {
            case DAY:
                long dayDistance = ChronoUnit.DAYS.between(start, end);
                if (dayDistance > -1) {
                    Stream.iterate(start, d -> d.plusDays(1)).limit(dayDistance + 1).forEach(f -> allDate.add(f.toString()));
                }
                break;
            case WEEK:
                //todo:跨年周有些问题，等待标准
                long weekDistance = ChronoUnit.WEEKS.between(start, end);
                if (weekDistance > -1) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    Stream.iterate(start, d -> d.plusWeeks(1)).limit(weekDistance + 1).forEach(f -> {

                        String calendar = fmt.format(f);
                        String year = calendar.substring(0, 4);

                        WeekFields weekFields = WeekFields.ISO;
                        String week;
                        int i = f.get(weekFields.weekOfWeekBasedYear());
                        if (i < 10) {
                            week = "0" + i;
                        } else {
                            week = String.valueOf(i);
                        }
                        if (f.getDayOfYear() < 7 && i > 50) {
                            year = Integer.toString(Integer.parseInt(year) - 1);
                        }
                        allDate.add(year + "-" + week);
                    });
                }
                break;
            case MONTH:
                long monthDistance = ChronoUnit.MONTHS.between(start, end);
                if (monthDistance > -1) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
                    Stream.iterate(start, d -> d.plusMonths(1)).limit(monthDistance + 1).forEach(f -> allDate.add(dtf.format(f)));
                }
                break;
            case YEAR:
                long yearDistance = ChronoUnit.YEARS.between(start, end);
                if (yearDistance > -1) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
                    Stream.iterate(start, d -> d.plusYears(1)).limit(yearDistance + 1).forEach(f -> allDate.add(dtf.format(f)));
                }
                break;
            default:
        }
        return allDate;
    }
}
