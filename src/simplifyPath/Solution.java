package simplifyPath;

import java.util.LinkedList;

public class Solution {
	//Linux内核对字符串进行简化
	//path = "/home/", => "/home"
	//path = "/a/./b/../../c/", => "/c"
	public static String simplifyPath(String path) {  
	    if(path == null || path.length()==0)  
	    {  
	        return "";  
	    }  
	    LinkedList<String> stack = new LinkedList<String>();  
	    StringBuilder res = new StringBuilder();  
	    int i=0;  
	      
	    while(i<path.length())  
	    {  
	        int index = i;  
	        StringBuilder temp = new StringBuilder();  
	        while(i<path.length() && path.charAt(i)!='/')  
	        {  
	            temp.append(path.charAt(i));  
	            i++;  
	        }  
	        if(index!=i)  
	        {  
	            String str = temp.toString();  
	            if(str.equals(".."))  
	            {  
	                if(!stack.isEmpty())  
	                    stack.pop();  
	            }  
	            else if(!str.equals("."))  
	            {  
	                stack.push(str);  
	            }  
	        }  
	        i++;  
	    }  
	    if(!stack.isEmpty())  
	    {  
	        String[] strs = stack.toArray(new String[stack.size()]);  
	        for(int j=strs.length-1;j>=0;j--)  
	        {  
	          res.append("/"+strs[j]);  
	        }  
	    }  
	    if(res.length()==0)  
	        return "/";  
	    return res.toString();  
	}  
}
