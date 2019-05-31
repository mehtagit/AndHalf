package com.learning.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dmfs.rfc5545.DateTime;
import org.dmfs.rfc5545.Weekday;
import org.dmfs.rfc5545.recur.InvalidRecurrenceRuleException;
import org.dmfs.rfc5545.recur.RecurrenceRule;
import org.dmfs.rfc5545.recur.RecurrenceRule.WeekdayNum;

//
//@SpringBootApplication
//@Component
//@EnableAutoConfiguration
//@EnableCaching
public class DemoApplication {

	public static void main(String[] args) throws InvalidRecurrenceRuleException {
		// ApplicationContext context = SpringApplication.run(DemoApplication.class,
		// args);

		RecurrenceRule rule = new RecurrenceRule("FREQ=WEEKLY;BYDAY=MO,SU,TU;UNTIL=20190531T000000Z");
		DateTime dateTime = rule.getUntil();
		System.out.println(rule);
		System.out.println(modifyWeeklyRule(rule));
	}

	public static RecurrenceRule modifyWeeklyRule(RecurrenceRule rule) {
		try {
			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			Date dateUtc = new Date();
			dateUtc.setDate(1);
			dateUtc.setMonth(5);
			cal.setTime(dateUtc);

			System.out.println("date:" + date + ", dateUtc:" + dateUtc);
			int dayOfWeekInUTC = cal.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == dayOfWeekInUTC) {
				// return actual rule, nothing do
			} else {
				if (dayOfWeekInUTC > dayOfWeek) {
					// Change DAY to next DAY
					List<WeekdayNum> newWeekDayNum = modifyWeekDays(rule, true);
					rule.setByDayPart(newWeekDayNum);
				} else {
					// Change DAY to previous DAY
					List<WeekdayNum> newWeekDayNum = modifyWeekDays(rule, false);
					rule.setByDayPart(newWeekDayNum);
				}
			}
		} catch (Exception ex) {

		}
		return rule;
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
