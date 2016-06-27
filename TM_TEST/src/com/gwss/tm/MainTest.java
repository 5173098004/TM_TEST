package com.gwss.tm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class MainTest {
	// ģ��:��������,��������
	public static void main(String[] args) {
		ConstractShenxian();
	}

	// ��������
	public static void ConstractShenxian(){
		
		System.out.println("+++++++++++++++++++++++++++++++++-----------------------");
		
		System.out.println("===========��ʼ����<�̱�ע��>����==============");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("�������ڣ�" + sf.format(new Date()) + "�����ղ���������");	
		
		System.out.println("12312312");
		// �̱�ע����������
		int dayInterval = SHENXIAN_INTERVAL.get("1")*30;
		System.out.println("��������Ϊ��" + dayInterval + "��");	
		System.out.println("���ޱ�׼������Ӧ��Ϊ��" + getBeforeAfterDate(sf.format(new Date()),dayInterval));
		
		// ������ֹ
		System.out.println("����������ֹ����ֹ�����Լ�������......");
		System.out.println("���Ĵ�ӡ�˶���Ϊ��206-07-09");
		
		// ���޽����� = (�ָ��� - ��ֹ��)*n����  + ������ǰ��δ�ָ�����ֹ����ǰ�ա���ֹ�գ� + ��׼���޽�����
		// ʣ������     = (���޽����� - ��ǰ��)
	}
	
	
	// �£������е���Ȼ�ռ���
	public static final HashMap<String, Integer> SHENXIAN_INTERVAL = new HashMap<String, Integer>(){
		{
			put("1", 9);  //�̱�����
		}
	};
	
	
	
	/** 
     *  
     * @param datestr �����ַ��� 
     * @param day  ���������Ϊ������ʾ֮��Ϊ������ʾ֮ǰ 
     * @return ָ�������ַ���n��֮ǰ����֮������� 
     */  
    public static java.sql.Date getBeforeAfterDate(String datestr, int day) {  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        java.sql.Date olddate = null;  
        try {  
            df.setLenient(false);  
            olddate = new java.sql.Date(df.parse(datestr).getTime());  
        } catch (ParseException e) {  
            throw new RuntimeException("����ת������");  
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
