package com.awesome.science;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.DateTime;

public class ScienceOfLambdas {
	
	public static String CURRENT_PERIOD = "currentYear";
	public static String PREVIOUS_PERIOD = "previousYear";
	private NumberTimeline ntl = new NumberTimeline();

	public static void main(String[] args) {
		ScienceOfLambdas sol = new ScienceOfLambdas();
		
		ArrayList<DateNumber> numberList = new ArrayList<DateNumber>();
		
		DateTime date = new DateTime();
		
		int month = 11;
		int year = 2016;
		int number = 5;
		
		sol.fillDates(CURRENT_PERIOD, date, 2);
		
		sol.addNumber(CURRENT_PERIOD, year, month, number);
		
		sol.ntl.getCurrentPeriod().stream().forEach(pp -> System.out.println(pp.getYear()+ " " +pp.getMonth() + " " + pp.getNumber()));
		

	}
	
	
	public void fillDates(String bucket, DateTime date, int monthRange){
		for (int i = 0; i < (monthRange*3-1); i++){
			int month = i-monthRange;
			addNumber(bucket, date.getYear(), date.plusMonths(month).getMonthOfYear(), 0);
		}
	}

	public void addNumber(String bucket, int year, int month, int number)
	{
		ntl.add(bucket, year, month, number);
	}
	
	public class NumberTimeline{
		  String rateType = "USD";
		  HashMap<String, ArrayList<DateNumber>> ntl = new HashMap<>();

	    public void add(String bucket, int year, int month, int number)
	    {
				if(ntl.get(bucket) == null)
					ntl.put(bucket, new ArrayList<>());
				List list = ntl.get(bucket).stream().filter(pp -> {if (pp.getMonth()== month && pp.getYear() == year) {pp.setNumber(number); return true ;}else {return false;}} ).collect(Collectors.toList());
				if (!(list.size() > 0))
				ntl.get(bucket).add(new DateNumber(year, month, number));
	    }

		
		public String getRateType()
		{
			return rateType;
		}


	    public List<DateNumber> getCurrentPeriod()
	    {
				if (ntl.get(CURRENT_PERIOD) != null)
					return ntl.get(CURRENT_PERIOD);
				else
					return new ArrayList();
	    }


		public List<DateNumber> getPreviousPeriod()
		{
			if (ntl.get(PREVIOUS_PERIOD) != null)
				return ntl.get(PREVIOUS_PERIOD);
			else
				return new ArrayList();
		}
	  }
	
	
	public class DateNumber{
	    int year;
	    int month;
	    int number;

			
		public DateNumber(int year, int month, int number)
	    {
	      this.month = month;
	      this.year = year;
	      this.number = number;
	    }


	    public int getMonth()
	    {
	      return this.month;
	    }


	    public int getNumber()
	    {
	      return this.number;
	    }


		public int getYear()
		{
			return year;
		}

		public void setNumber(int value){
			number = value;
		}
	}
	

}
