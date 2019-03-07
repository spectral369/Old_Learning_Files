package random;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

public class Perioada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int h = 1;
		int m = 34;
		int s = 22;
		DateTime dt1 = DateTime.now();
		DateTime dt2 = new DateTime().withHourOfDay(6).withMinuteOfHour(10).withSecondOfMinute(20);
		
		
		Period p = new Period(dt1, dt2);
		if(p.getMinutes()<0) {
			dt2 = dt2.plusDays(1);
			p =  new Period(dt1,dt2);
		}
		
		System.out.println("DAYS: " + p.getDays());
		System.out.println("HOURS: " + (p.minusHours(h).getHours()));
		System.out.println("MINUTES: " + (p.minusMinutes(m).getMinutes()));
		System.out.println("SECONDS: " + (p.minusSeconds(s).getSeconds()));
		
	}
	

public static int getDiffHours(Date startDate, Date endDate){

  Interval interval = new Interval(startDate.getTime(), endDate.getTime());
  Period period = interval.toPeriod();
  return period.getHours();
}

}
