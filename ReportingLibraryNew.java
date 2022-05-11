package com.netscout.automation.reporting;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportingLibraryNew {

	public String getExpandCollpaseJavaScript()
	{
		String script  ="<script type=\"text/javascript\">  \r\n" + 
				"    $(document).ready(function () {  \r\n" + 
				"        debugger;  \r\n" + 
				"        $('.hideTr').slideUp(50);  \r\n" + 
				"     $('[data-toggle=\"toggle\"]').click(function () {  \r\n" + 
				"        if ($(this).parents().next(\".hideTr\").is(':visible')) {  \r\n" + 
				"            $(this).parents().next('.hideTr').slideUp(50);  \r\n" + 
				"            $(\".plusminus\" + $(this).children().children().attr(\"id\")).text('[+] ');  \r\n" + 
				"           $(this).css('background-color', 'white');  \r\n" + 
				"            }  \r\n" + 
				"        else {  \r\n" + 
				"            $(this).parents().next('.hideTr').slideDown(50);  \r\n" + 
				"            $(\".plusminus\" + $(this).children().children().attr(\"id\")).text('[-] ');  \r\n" + 
				"           $(this).css('background-color', '#c1eaff ');    \r\n" + 
				"        }  \r\n" + 
				"    });  \r\n" + 
				"    });  \r\n" + 
				"</script>\n ";
		return script;
	}

	public String getJQueryURL()
	{
		String JQueryURL ="<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n";
		return JQueryURL;
	}

	public static String readSubReportHTMLFile(String SubReportPath)
	{
		String content = "";
		try
		{
			content = new String ( Files.readAllBytes( Paths.get(SubReportPath) ) );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return content;
	}
	public static String getHeaderFooterCSS()
	{
		String css="<style type=\"text/css\">\r\n" + 
				"body{\r\n" + 
				"	margin:1;\r\n" + 
				"	height:98%;\r\n" + 
				"	}\r\n" + 
				"#container{\r\n" + 
				"	min-height:100%;\r\n" + 
				"	position:relative;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				" p {\r\n" + 
				"     text-align: center;\r\n" + 
				"}\r\n" + 
				".header{\r\n" + 
				"  position:relative; \r\n" + 
				"  right: 0;\r\n" + 
				"  bottom: 0;\r\n" + 
				"  left: 0;\r\n" + 
				"  background-color: #4682B4;\r\n" + 
				"  text-align: left;\r\n" + 
				"}\r\n"  
				 + 
				".theader{\r\n" + 
				"    border: 1px solid Black;\r\n" + 
				
				"    text-align: left;\r\n" + 
				"	background-color: #696969;\r\n" + 
				"}\r\n" + 
				"table{\r\n" + 
				"   margin-left: 60px;\r\n" +
				"  table-layout: fixed;\r\n" +
				"    border-collapse: collapse;\r\n" + 
				"}\r\n" + 
				"th {\r\n" + 
				"    background-color: #4682B4;\r\n" + 
				"    border: 1px solid Black;\r\n" + 
				
				"    text-align: center;\r\n" + 
				"}\r\n" + 
				"td {\r\n" + 
				"    border: 1px solid Black;\r\n" + 
							
				"}\r\n" + 
				"  \r\n" + 
				"tr:nth-child(even) {\r\n" + 
				"    background-color: #eee;\r\n" + 
				"}\r\n" + 
				"  \r\n" + 
				"tr:nth-child(odd) {\r\n" + 
				"    background-color: #fff;\r\n" + 
				"}    \r\n" + 
				"</style>";
		return css;
	}

}
