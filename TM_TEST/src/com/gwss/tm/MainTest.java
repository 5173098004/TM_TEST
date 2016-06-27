package com.gwss.tm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class MainTest {
	// 模拟:收文起算,建立审限
	public static void main(String[] args) {
		ConstractShenxian();
	}

	// 建立审限
	public static void ConstractShenxian(){
		
		System.out.println("+++++++++++++++++++++++++++++++++-----------------------");
		
		System.out.println("===========开始建立<商标注册>审限==============");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("收文日期：" + sf.format(new Date()) + "，当日不计入审限");	
		
		System.out.println("12312312");
		// 商标注册审限周期
		int dayInterval = SHENXIAN_INTERVAL.get("1")*30;
		System.out.println("审限周期为：" + dayInterval + "天");	
		System.out.println("审限标准结束日应该为：" + getBeforeAfterDate(sf.format(new Date()),dayInterval));
		
		// 发文中止
		System.out.println("遇到发文中止，中止日期仍计入审限......");
		System.out.println("发文打印核对日为：206-07-09");
		
		// 审限结束日 = (恢复日 - 中止日)*n完整  + （若当前有未恢复的中止，当前日—中止日） + 标准审限结束日
		// 剩余审限     = (审限结束日 - 当前日)
	}
	
	
	// 月，按其中的自然日计算
	public static final HashMap<String, Integer> SHENXIAN_INTERVAL = new HashMap<String, Integer>(){
		{
			put("1", 9);  //商标审限
		}
	};
	
	
	
	/** 
     *  
     * @param datestr 日期字符串 
     * @param day  相对天数，为正数表示之后，为负数表示之前 
     * @return 指定日期字符串n天之前或者之后的日期 
     */  
    public static java.sql.Date getBeforeAfterDate(String datestr, int day) {  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        java.sql.Date olddate = null;  
        try {  
            df.setLenient(false);  
            olddate = new java.sql.Date(df.parse(datestr).getTime());  
        } catch (ParseException e) {  
            throw new RuntimeException("日期转换错误");  
        }  
        Calendar cal = new GregorianCalendar();  
        cal.setTime(olddate);  
  
        int Year = cal.get(Calendar.YEAR);  
        int Month = cal.get(Calendar.MONTH);  
        int Day = cal.get(Calendar.DAY_OF_MONTH);  
  
        int NewDay = Day + day;  
  
        cal.set(Calendar.YEAR, Year);  
        cal.set(Calendar.MONTH, Month);  
        cal.set(Calendar.DAY_OF_MONTH, NewDay);  
  
        return new java.sql.Date(cal.getTimeInMillis());  
    }  
}
