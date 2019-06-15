package com.learning.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.dmfs.rfc5545.Weekday;
import org.dmfs.rfc5545.recur.Freq;
import org.dmfs.rfc5545.recur.InvalidRecurrenceRuleException;
import org.dmfs.rfc5545.recur.RecurrenceRule;
import org.dmfs.rfc5545.recur.RecurrenceRule.Part;
import org.dmfs.rfc5545.recur.RecurrenceRule.WeekdayNum;

//
//@SpringBootApplication
//@Component
//@EnableAutoConfiguration
//@EnableCaching
public class DemoApplication {

	public static int getSubstring(String a) {
		int small = 0;
		Set<Character> set = new HashSet<>();

		for (char s : a.toCharArray()) {
			if ((int) s >= 97 && (int) s < 123)
				set.add(s);
			else
				return -1;
		}

		for (int i = 0; i < a.length() - set.size(); i++) {
			int j = 0;
			while (true) {
				String m = a.substring(i, set.size() + j);
				boolean conatainsAll = true;
				for (char s : set) {
					if (m.contains(s + "")) {
					} else {
						conatainsAll = false;
						break;
					}
				}

				if (conatainsAll) {
					if (small == 0)
						small = m.length();
					if (small > m.length())
						small = m.length();
					break;
				}
				j++;
				if ((set.size() + j) > a.length())
					break;
			}
		}
		return small;
	}

	public static void main(String[] args) throws InvalidRecurrenceRuleException {
		// ApplicationContext context = SpringApplication.run(DemoApplication.class,
		// args);

		System.out.println(getSubstring("aa"));
		System.out.println(getSubstring("namdnfmanfds"));

		/*
		 * System.out.println(LocalDateTime.now().plusMinutes(720)); System.out.println(
		 * new DemoApplication().recurranceRuleFreq(
		 * "FREQ=MONTHLY;BYMONTHDAY=31;INTERVAL=2;UNTIL=20190630T000000Z", new Date(),
		 * LocalDate.now().plusDays(20)));
		 */
	}

	public List<Date> recurranceRuleFreq(String recurrenceRule, Date startDate, LocalDate tillDate) {
		List<Date> dailyDateList = null;
		// DAILY, WEEKLY, BYDAY, MONTHLY, BYMONTHDAY, YEARLY, BYMONTH, INTERVAL, COUNT,
		// UNTIL
		try {
			dailyDateList = new ArrayList<>();
			RecurrenceRule rule = new RecurrenceRule(recurrenceRule);
			Calendar cal = Calendar.getInstance();
			if (rule.getFreq().compareTo(Freq.DAILY) == 0) {
				cal.setTime(startDate);
				boolean status = endDateValidation(rule, cal);
				if (status) {
					System.out.println("Date " + cal.getTime());
					dailyDateList.add(cal.getTime());
					parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 1, cal.get(Calendar.DATE), tillDate);
				}
			} else if (rule.getFreq().compareTo(Freq.WEEKLY) == 0) {
				List<WeekdayNum> weekDayNum = rule.getByDayPart();
				for (WeekdayNum num : weekDayNum) {
					if (num.weekday.equals(Weekday.SU)) {
						cal.setTime(startDate);
						cal.add(Calendar.DATE, getDayOfWeek(cal, 1));
						boolean status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("======SU " + cal.getTime());
							dailyDateList.add(cal.getTime());
							parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 7, cal.get(Calendar.DATE),
									tillDate);
						}
					}
					if (num.weekday.equals(Weekday.MO)) {
						cal.setTime(startDate);
						cal.add(Calendar.DATE, getDayOfWeek(cal, 2));
						boolean status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("======MO " + cal.getTime());
							dailyDateList.add(cal.getTime());
							parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 7, cal.get(Calendar.DATE),
									tillDate);
						}
					}
					if (num.weekday.equals(Weekday.TU)) {
						cal.setTime(startDate);
						cal.add(Calendar.DATE, getDayOfWeek(cal, 3));
						boolean status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("======TU " + cal.getTime());
							dailyDateList.add(cal.getTime());
							parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 7, cal.get(Calendar.DATE),
									tillDate);
						}
					}
					if (num.weekday.equals(Weekday.WE)) {
						cal.setTime(startDate);
						cal.add(Calendar.DATE, getDayOfWeek(cal, 4));
						boolean status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("======WE " + cal.getTime());
							dailyDateList.add(cal.getTime());
							parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 7, cal.get(Calendar.DATE),
									tillDate);
						}
					}
					if (num.weekday.equals(Weekday.TH)) {
						cal.setTime(startDate);
						cal.add(Calendar.DATE, getDayOfWeek(cal, 5));
						boolean status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("======TH " + cal.getTime());
							dailyDateList.add(cal.getTime());
							parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 7, cal.get(Calendar.DATE),
									tillDate);
						}
					}
					if (num.weekday.equals(Weekday.FR)) {
						cal.setTime(startDate);
						cal.add(Calendar.DATE, getDayOfWeek(cal, 6));
						boolean status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("======FR " + cal.getTime());
							dailyDateList.add(cal.getTime());
							parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 7, cal.get(Calendar.DATE),
									tillDate);
						}
					}
					if (num.weekday.equals(Weekday.SA)) {
						cal.setTime(startDate);
						cal.add(Calendar.DATE, getDayOfWeek(cal, 7));
						boolean status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("======SA " + cal.getTime());
							dailyDateList.add(cal.getTime());
							parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 7, 0, tillDate);
						}
					}
				}
			} else if (rule.getFreq() == Freq.MONTHLY) {

				List<Integer> monthDays = rule.getByPart(Part.BYMONTHDAY);
				System.out.println("monthDays : " + monthDays);
				if (null != monthDays && !monthDays.isEmpty()) {
					Integer monthDay = monthDays.get(0);
					System.out.println("======ByMonthDay " + monthDay);
					cal.setTime(startDate);
					System.out.println("calender " + cal.getTime());
					boolean status = true;
					System.out.println("monthDay " + monthDay + ", " + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
					if (monthDay > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {

						cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						status = endDateValidation(rule, cal);
						System.out.println("Ignoring the date " + monthDay + " which is not supported to month "
								+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
					} else {
						cal.set(Calendar.DATE, monthDay);
						status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("Date.. " + cal.getTime());
							dailyDateList.add(cal.getTime());
						}
					}
					if (status)
						parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 1, monthDay, tillDate);
				}
			} else if (rule.getFreq().compareTo(Freq.YEARLY) == 0) {
				List<Integer> monthDays = rule.getByPart(Part.BYMONTHDAY);
				List<Integer> months = rule.getByPart(Part.BYMONTH);
				if ((null != monthDays && !monthDays.isEmpty()) && (null != months && !months.isEmpty())) {
					Integer monthDay = monthDays.get(0);
					Integer month = months.get(0);
					System.out.println("======ByMonthDay " + monthDay + " ByMonth: " + month);
					cal.setTime(startDate);
					cal.set(Calendar.MONTH, month);
					boolean status = true;
					if (monthDay > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
						cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						status = endDateValidation(rule, cal);
						System.out.println("Ignoring the date " + monthDay + " which is not supported to month "
								+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
					} else {
						cal.set(Calendar.DATE, monthDay);
						status = endDateValidation(rule, cal);
						if (status) {
							System.out.println("Date.. " + cal.getTime());
							dailyDateList.add(cal.getTime());
						}
					}
					if (status)
						parseRecurrenceRule(dailyDateList, cal.getTime(), rule, 1, monthDay, tillDate);
				}
			} else {
				System.out.println("RecurrenceRule Frequency " + rule.getFreq()
						+ " not matched with any, so ignoring to parse rule");
			}
			return dailyDateList;
		} catch (Exception ex) {
			return null;
		}
	}

	private void parseRecurrenceRule(List<Date> dailyDateList, Date startDate, RecurrenceRule rule, int val, int date,
			LocalDate tillDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int interval = 0;
		if (rule.getInterval() > 0)
			interval = rule.getInterval();
		int count = 0;
		if (rule.getCount() != null && rule.getCount() > 0)
			count = rule.getCount();
		if (count > 0) {
			while (count > 1) {
				validateRuleByFreq(rule, cal, interval, val, date);
				if ((rule.getFreq().equals(Freq.MONTHLY) || rule.getFreq().equals(Freq.YEARLY))
						&& date > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					System.out.println("Ignoring the date " + date + " which is not supported to month "
							+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
				} else {
					dailyDateList.add(cal.getTime());
					System.out.println("DATE " + cal.getTime());
				}
				count--;
			}
		} else if (rule.getUntil() != null && tillDate == null) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTimeInMillis(rule.getUntil().getTimestamp());
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(cal.getTime());
			cal2.set(Calendar.DATE, cal1.get(Calendar.DATE));
			cal2.set(Calendar.MONTH, cal1.get(Calendar.MONTH));
			cal2.set(Calendar.YEAR, cal1.get(Calendar.YEAR));
			validateRuleByFreq(rule, cal, interval, val, date);

			while (cal2.getTime().compareTo(cal.getTime()) >= 0) {
				if ((rule.getFreq().equals(Freq.MONTHLY) || rule.getFreq().equals(Freq.YEARLY))
						&& date > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					System.out.println("Ignoring the date " + date + " which is not supported to month "
							+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
				} else {
					if ((cal2.get(Calendar.DATE) != cal.get(Calendar.DATE))) {
						dailyDateList.add(cal.getTime());
						System.out.println("DATE1 " + cal.getTime());
					} else {
						dailyDateList.add(cal.getTime());
						System.out.println("DATE2 " + cal.getTime());
					}
				}
				validateRuleByFreq(rule, cal, interval, val, date);
			}
		} else {
			// INTERVAL - in case if there is no end time to frequency, restrict to 1 week
			// (DAILY, WEEKLY), 1 month (MONTHLY), 1 year (YEARLY) from start datetime to
			// test
			// Restrict to 1 week if needed to above two cases like COUNT, UNTIL
			// Change if there is a restriction required only to next 24 hrs
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(cal.getTime());

			if (tillDate == null)
				cal1.add(Calendar.DATE, 6);
			else {
				cal1.set(Calendar.DATE, tillDate.getDayOfMonth());
				cal1.set(Calendar.MONTH, tillDate.getMonthValue() - 1);
				cal1.set(Calendar.YEAR, tillDate.getYear());
			}

			while (cal1.getTime().compareTo(cal.getTime()) > 0) {
				validateRuleByFreq(rule, cal, interval, val, date);

				if ((rule.getFreq().equals(Freq.MONTHLY) || rule.getFreq().equals(Freq.YEARLY))
						&& date > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					System.out.println("Ignoring the date " + date + " which is not supported to month "
							+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
				} else {
					if ((rule.getFreq().equals(Freq.MONTHLY) && interval == 1)
							|| (rule.getFreq().equals(Freq.YEARLY) && interval < 3)
							|| (rule.getFreq().equals(Freq.DAILY) || rule.getFreq().equals(Freq.WEEKLY))) {
						// System.out.println(cal1.get(Calendar.DATE) + " ,,, "+cal.get(Calendar.DATE));
						// System.out.println(cal1.get(Calendar.MONTH) + " ,,,
						// "+cal.get(Calendar.MONTH));
						// System.out.println(cal1.get(Calendar.YEAR) + " ,,, "+cal.get(Calendar.YEAR));
						//
						if (cal1.getTime().compareTo(cal.getTime()) >= 0) {
							if (cal1.get(Calendar.MONTH) > cal.get(Calendar.MONTH)) {
								System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH) + " date@@@@ " + date
										+ " " + cal.getTime() + " ,,,,, "
										+ cal.getActualMaximum(Calendar.DAY_OF_MONTH));
								if (rule.getFreq().equals(Freq.YEARLY)
										&& (date == cal.getActualMaximum(Calendar.DAY_OF_MONTH))) {
									cal.set(Calendar.DATE, date);
									dailyDateList.add(cal.getTime());
									System.out.println("Date5 " + cal.getTime());
								} else {
									dailyDateList.add(cal.getTime());
									System.out.println("DATE4 " + cal.getTime());
								}
							} else if ((cal1.get(Calendar.DATE) >= cal.get(Calendar.DATE))) {
								if ((rule.getFreq().equals(Freq.MONTHLY) || rule.getFreq().equals(Freq.YEARLY))
										&& date > cal.get(Calendar.DATE))
									cal.set(Calendar.DATE, date);
								if (rule.getFreq().equals(Freq.WEEKLY)
										&& (cal1.get(Calendar.MONTH) != cal.get(Calendar.MONTH))) {
									System.out.println("Ignore ");
								} else {
									dailyDateList.add(cal.getTime());
									System.out.println("DATE3 " + cal.getTime());
								}
							}
						}

					}
				}
			}
		}
	}

	private int getDayOfWeek(Calendar cal, int day) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek > day) {
			dayOfWeek = (7 - dayOfWeek) + day;
		} else if (dayOfWeek < day) {
			dayOfWeek = day - dayOfWeek;
		} else {
			dayOfWeek = 0;
		}
		return dayOfWeek;
	}

	private void validateRuleByFreq(RecurrenceRule rule, Calendar cal, int interval, int val, int date) {
		if (rule.getFreq().equals(Freq.YEARLY)) {
			cal.add(Calendar.YEAR, interval * val);
		} else if (rule.getFreq().equals(Freq.MONTHLY)) {
			cal.add(Calendar.MONTH, interval * val);
			if (date > cal.getActualMaximum(Calendar.DAY_OF_MONTH))
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			else
				cal.set(Calendar.DATE, date);
		} else
			cal.add(Calendar.DATE, interval * val);
	}

	private boolean endDateValidation(RecurrenceRule rule, Calendar cal) {
		if (rule.getUntil() != null) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTimeInMillis(rule.getUntil().getTimestamp());
			if (cal1.getTime().compareTo(cal.getTime()) < 0) {
				System.out.println("Ignoring recurrence rule as start date  " + cal.getTime()
						+ "  is greater than end date " + cal1.getTime());
				return false;
			}
		}
		return true;
	}

	private static List<WeekdayNum> modifyWeekDays(RecurrenceRule rule, boolean addition) {
		List<WeekdayNum> weekDayNum = rule.getByDayPart();
		List<WeekdayNum> newWeekDayNum = new ArrayList<>();
		for (WeekdayNum num : weekDayNum) {
			if (num.weekday.equals(Weekday.SU)) {
				if (addition)
					newWeekDayNum.add(new WeekdayNum(0, Weekday.MO));
				else
					newWeekDayNum.add(new WeekdayNum(0, Weekday.SA));
			}
			if (num.weekday.equals(Weekday.MO)) {
				if (addition)
					newWeekDayNum.add(new WeekdayNum(0, Weekday.TU));
				else
					newWeekDayNum.add(new WeekdayNum(0, Weekday.SU));
			}
			if (num.weekday.equals(Weekday.TU)) {
				if (addition)
					newWeekDayNum.add(new WeekdayNum(0, Weekday.WE));
				else
					newWeekDayNum.add(new WeekdayNum(0, Weekday.MO));
			}
			if (num.weekday.equals(Weekday.WE)) {
				if (addition)
					newWeekDayNum.add(new WeekdayNum(0, Weekday.TH));
				else
					newWeekDayNum.add(new WeekdayNum(0, Weekday.TU));
			}
			if (num.weekday.equals(Weekday.TH)) {
				if (addition)
					newWeekDayNum.add(new WeekdayNum(0, Weekday.FR));
				else
					newWeekDayNum.add(new WeekdayNum(0, Weekday.WE));
			}
			if (num.weekday.equals(Weekday.FR)) {
				if (addition)
					newWeekDayNum.add(new WeekdayNum(0, Weekday.SA));
				else
					newWeekDayNum.add(new WeekdayNum(0, Weekday.TH));
			}
			if (num.weekday.equals(Weekday.SA)) {
				if (addition)
					newWeekDayNum.add(new WeekdayNum(0, Weekday.SU));
				else
					newWeekDayNum.add(new WeekdayNum(0, Weekday.FR));
			}
		}
		return newWeekDayNum;
	}
}
