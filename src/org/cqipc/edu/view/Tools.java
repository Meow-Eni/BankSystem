package org.cqipc.edu.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.cqipc.edu.bean.Account;
import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.bean.Customer;

public class Tools {
	public static String isEmpty(String str){
		char[] ch = str.toCharArray();
		String s="";
		for(int i=0;i<ch.length;i++){
			if(ch[i] != ' '){
				s += ch[i];
			}
		}
		return s;
	}
 public static boolean isNumber (String str) {
	 char[]ch=str.toCharArray();
	 for (int i = 0; i < ch.length; i++) {
		 if (ch[i]<48||ch[i]>57) {
			 return false;	
		}
		
	}
	return true;
}
 public static boolean isPhoneNumber(String str) {
	 if (str.length()==11) {
		byte[] buf=str.getBytes();
		for (int i = 0; i < buf.length; i++) {
			if (buf[i]>=48&&buf[i]<=57) {
				continue;
			}else {
				return false;
			}
		}
		return true;
	}else {
		return false;
	}
 }
 public static boolean isEmail(String str) {
	 if (str.length()>=5) {
		if (str.endsWith(".com")||str.endsWith(".com.cn")) {
			int first=str.indexOf("@");
			if (first!=0) {
				int last=str.lastIndexOf("@");
				if (first==last) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}else {
		return false;
	}
 }
 public static String getDates() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}
 public static String[] getbankNames(List<Bank> data) {
		String[] names=new String[data.size()+1];
		names[0]="==«Î—°‘Ò==";
		for(int i=1;i<names.length;i++) {
			names[i]=data.get(i-1).getB_name();
		}
		return names;
	}
 public static String[] getuserNames(List<Customer> data) {
		String[] names=new String[data.size()+1];
		names[0]="==«Î—°‘Ò==";
		for(int i=1;i<names.length;i++) {
			names[i]=data.get(i-1).getC_name();
		}
		return names;
	}
 public static boolean isBalance(String str) {
		String number=str.replace(" ", "");
		if (number.length()!=0) {
			char[] buf=number.toCharArray();
			for (int i = 0; i < buf.length; i++) {
				if (buf[i]<48||buf[i]>57) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
 public static String[] getaccountIds(List<Account> data) {
		String[] names=new String[data.size()+1];
		names[0]="==«Î—°‘Ò==";
		for(int i=1;i<names.length;i++) {
			names[i]=Integer.toString(data.get(i-1).getA_id());
		}
		return names;
	}
 }