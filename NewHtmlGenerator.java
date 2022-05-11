package com.netscout.automation.reporting;


import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;


public class NewHtmlGenerator {

	public  FileWriter htmlwriter;
	public ReportingLibraryNew RP = new ReportingLibraryNew();

	String reportFile;
	int logcount = 0;
	// String jscript = "<script language=\"JavaScript\">	function cv(src1,src2,src3,dirpath) {var a= dirpath + '\\InternalFiles\\\\xlsc.exe'; var WshShell = new ActiveXObject( 'WScript.Shell' ); ReturnCode = WshShell.Run(a+' \"'+src1+'\" \"'+src2+'\" /r:'+src3+' /close /closef /mcm:l /cr:2 /sr:50 /nc /dr+'); } </script>";
	String installation_log = System.getProperty("user.dir")+"\\Logs\\sg_install_verify.log"; 
	String simulation_log = System.getProperty("user.dir")+"\\Logs\\sg_simulation.log";
	String jscript = "<script language=\"JavaScript\">	function cv(src1,src2,src3,dirpath) {var a= dirpath + '\\InternalFiles\\\\xlsc.exe'; var WshShell = new ActiveXObject( 'WScript.Shell' ); ReturnCode = WshShell.Run(a+' \"'+src1+'\" \"'+src2+'\" /r:'+src3+' /close /closef /mcm:l /cr:2 /sr:50 /nc /dr+'); } </script>";
	String jscript1 = "<script language=\"JavaScript\">	" +
			"function winmerge(src1,src2,src3,dirpath) " +
			"{" +
			"var a= dirpath + '\\\\InternalFiles\\\\WinMerge-2.8.4-exe\\\\WinMerge.exe'; " +
			"var WshShell = new ActiveXObject( 'WScript.Shell' ); " +
			"ReturnCode = WshShell.Run(a+' \"' + src1 + '\" \"'+ src2 +'\"'); " +
			"} " +
			"</script>";



	String index = "0";
	public NewHtmlGenerator(String path)
	{
		try
		{
			reportFile = path;
			htmlwriter = new FileWriter(reportFile);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	public  String Get_Index()
	{
		Integer x = Integer.valueOf(index);
		int ind = x.intValue() +1;
		index = String.valueOf(ind); 
		return index;
	}

	public NewHtmlGenerator(String path, boolean val)
	{
		try
		{
			reportFile = path;
			htmlwriter = new FileWriter(reportFile, val);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	public   void  Close()
	{
		try
		{
			htmlwriter.close();
		}
		catch(Exception exception) { }
	}


	public void writeScripts()
	{
		try
		{
			htmlwriter.write("<HTML>\r\n");
			htmlwriter.write("<HEAD>\r\n");
			String title = "sCII Backend";
			htmlwriter.write("<TITLE >" + title + "</TITLE>\r\n");
			htmlwriter.write(RP.getHeaderFooterCSS());
			htmlwriter.write(RP.getJQueryURL());
			htmlwriter.write(RP.getExpandCollpaseJavaScript());


			htmlwriter.write("</HEAD>\r\n");
		}
		catch(Exception exception) { }
	}
	public void setTitle_deprecated(String title)
	{
		String OsVersion=System.getProperty("os.arch");

		String jscript2 = "";

		if(OsVersion.contains("32"))
		{
			jscript2="<script language=\"JavaScript\">	" +
					"function AraxisMerger_Script(src1,src2) " +
					"{" +
					"var a= \'\"C:\\\\Program Files\\\\Araxis\\\\Araxis Merge v6.5\\\\Merge.exe\"'; " +
					"var WshShell = new ActiveXObject( 'WScript.Shell' ); " +
					"ReturnCode = WshShell.Run(a+' \"' + src1 + '\" \"'+ src2 +'\"'); " +
					"} " +
					"</script>";
		}
		else
		{
			jscript2="<script language=\"JavaScript\">	" +
					"function AraxisMerger_Script(src1,src2) " +
					"{" +
					"var a= \'\"C:\\\\Program Files (x86)\\\\Araxis\\\\Araxis Merge v6.5\\\\Merge.exe\"'; " +
					"var WshShell = new ActiveXObject( 'WScript.Shell' ); " +
					"ReturnCode = WshShell.Run(a+' \"' + src1 + '\" \"'+ src2 +'\"'); " +
					"} " +
					"</script>";
		}
		try
		{
			htmlwriter.write("<HTML>\r\n");
			htmlwriter.write("<HEAD>\r\n");

			htmlwriter.write("<TITLE >" + title + "</TITLE>\r\n");
			htmlwriter.write(jscript);
			htmlwriter.write(jscript1);
			htmlwriter.write(jscript2);
			htmlwriter.write("</HEAD>\r\n");
			htmlwriter.write("<BR>\r\n");

			htmlwriter.write("<body bgcolor=\"#9EC0FF\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" topmargin=\"0\" text=\"#000000\" link=\"#C50021\" vlink=\"#C50021\" alink=\"#0000CC\">\r\n");
			htmlwriter.write("<header>nG1 PA Service Automation Suite</header>\r\n");
			htmlwriter.write("<table cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");

			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("<td colspan=2 BORDER=\"1\" width=\"99%\" bgcolor=\"#222222\"><center><font face=\"serif\" color = \"FFFFFF\" size=\"4\"><b>"+title+"</b></font></center></td>\r\n");
			htmlwriter.write("</tr>\r\n");
			htmlwriter.write("</table>\r\n");
			htmlwriter.write("<BR>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void setContentRightJustified(String content)
	{
		try
		{
			String Str_StartTime = content.split(":")[0];
			String StartTime = content.split(":")[1];
			htmlwriter.write("<Left><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"2\">" + Str_StartTime +":"+ "</FONT></Left>&nbsp;");
			htmlwriter.write("<B><Left><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"2\">" + StartTime + "</FONT></Left></B> ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void SetNavigationText(String content)
	{
		try
		{  if(content.contains(" >> "))
			htmlwriter.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<Left><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"2\">" + content +"</FONT></Left>");
		else
			htmlwriter.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<Left><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"2\">" + content +"</FONT></Left>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void setContentLeftJustified(String content)
	{
		try
		{   
			if(content.contains("Test Bed"))
			{
				htmlwriter.write("<Left><b><u><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">" + content +":"+ "</FONT></b></u></Left>");
			}
			else if(content.contains("nEI-nEI")||content.contains("Baseline"))
			{
				String str1 = content.split(":")[0];
				String str2 = content.split(":")[1];
				//htmlwriter.write("&nbsp;&nbsp;&nbsp;&nbsp;<Left><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"2\">" + str1 +" :"+ "<b>"+str2+"</FONT></b></Left>");
				htmlwriter.write("<Left><b><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">" + str1 +" :"+"</FONT></b></Left>");
				htmlwriter.write("<Left><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">" + str2 +"</FONT></Left>");
			}
			else
				htmlwriter.write("<Left><u><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"3\">" + content +"</FONT></u></Left>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void setContentCenterJustified(String content)
	{
		try
		{
			htmlwriter.write("<B><center><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">&nbsp;" + content + "</FONT></center></B>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addLine(String Line) {
		try {
			htmlwriter.write("<BR>\n");
			htmlwriter.write("<font face=\"Trebuchet MS\" color = \"BLACK\" size=\"3\"><u><b>"+Line+"</u></b></font>");
			htmlwriter.write("<BR>\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void setReportTitle(String text)
	{
		try 
		{
			htmlwriter.write("<BR>");
			htmlwriter.write("<table border=\"0\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			htmlwriter.write("<tr>\r\n"); 
			htmlwriter .write("<td bgcolor=\"#C8FF33\" colspan=2 BORDER=\"1px solid green\" width=\"99%\" ><center><font face=\"serif\" color = \"black\" size=\"5\"><b>"+ text + "</b></font></center></td>\r\n"); 
			htmlwriter.write("</tr>\r\n");  
			htmlwriter.write("</table>");
			htmlwriter.write("<BR>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	} 


	public void setContent(String content)
	{

		try {
			//htmlwriter.write("<h4> "+"&nbsp;&nbsp;&nbsp;"+content+"</h4>");
			htmlwriter.write("<B><FONT FACE =\"Times New Roman\" size =\"4\""+">"+"&nbsp;&nbsp;&nbsp;"+content+"</FONT></B><BR>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try
		{
			htmlwriter.write("<Left><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">" + content + "</FONT></Left>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}


	public void setContent(String id,String content)
	{

		try {
			//htmlwriter.write("<h4 id="+"\""+id+"\""+">"+"&nbsp;&nbsp;&nbsp;"+content+"</h4>");
			htmlwriter.write("<B><FONT FACE =\"Times New Roman\" size =\"4\" id="+"\""+id+"\""+">"+"&nbsp;&nbsp;&nbsp;"+content+"</FONT></B><BR>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try
		{
			htmlwriter.write("<Left><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">" + content + "</FONT></Left>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}

	public  void setSubReportTableHeader()
	{

		try {
			htmlwriter.write("<table border=\"1%\" frame=\"border\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");

			htmlwriter.write("<tr>\r\n"); 

			htmlwriter.write("<th style=\"border: 1px solid black;\" BGCOLOR=\"#CF7933\" >SI NO</th>"); 
			htmlwriter.write("<th style=\"border: 1px solid black;\" BGCOLOR=\"#CF7933\" >Test Cases</th>");
			htmlwriter.write("<th style=\"border: 1px solid black;\" BGCOLOR=\"#CF7933\" colspan=\"2\">Frame Count (Base/Test)</th>");
			htmlwriter.write("<th style=\"border: 1px solid black;\" BGCOLOR=\"#CF7933\" colspan=\"2\">Protocol Layer Count (Base/Test)</th>");
			htmlwriter.write("<th style=\"border: 1px solid black;\" BGCOLOR=\"#CF7933\" colspan=\"2\">Line Count (Base/Test)</th>");
			htmlwriter.write("<th style=\"border: 1px solid black;\" BGCOLOR=\"#CF7933\" >Result</th>");
			htmlwriter.write("</tr>\r\n");  

		} 
		catch (IOException e) {

			e.printStackTrace();
		}
	}
	public  void setKeyAndValue(String key, String value)
	{
		try
		{
			htmlwriter.write("<B><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">&nbsp;&nbsp;&nbsp;&nbsp;" + key + " :<FONT face=\"Trebuchet MS\" color = \"GREEN\" size=\"2\"> " + value + "</FONT></FONT></B><BR>\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void setLogFilePath()
	{
		try
		{
			htmlwriter.write("<a href=\"file:///"+installation_log+"\">   Click here to view the log file</a>\n");
			htmlwriter.write("\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setSimulationLogFilePath()
	{
		try
		{
			htmlwriter.write("<a href=\"file:///"+simulation_log+"\">   Click here to view the log file</a>\n");
			htmlwriter.write("\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void setKeyAndValues(String keys[], String values[])
	{
		try
		{
			for(int i = 0; i < keys.length; i++)
			{
				setKeyAndValue(keys[i], values[i]);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void initialize_StartTime_TestStatus_Table()
	{
		try
		{

			htmlwriter.write("<BR><table  cellspacing=\"0\" width=\"99%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void setHeader()
	{
		try
		{
			//htmlwriter.write("<BR><table border=\"3%\" frame=\"border\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			htmlwriter.write("<header>nG1 PA Service Automation Suite</header>\r\n");
			logcount = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setFooter()
	{
		try
		{
			//htmlwriter.write("<BR><table border=\"3%\" frame=\"border\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			htmlwriter.write("<p> @ BEC AUTOMATION TEAM </p>");
			logcount = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void initializeSummaryTable(String Report_Title)
	{
		try
		{
			htmlwriter.write("<body>\r\n");
			htmlwriter.write("<div id=\"container\">\r\n");
			htmlwriter.write("<div class=\"header\">"+Report_Title+"</div>\r\n");
			htmlwriter.write("<table width=\"50%\">\r\n");
			logcount = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void initializeTable()
	{
		try
		{

			htmlwriter.write("<table width=\"50%\">\r\n");
			logcount = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public  void SetDateTime(String StartTime, String EndTime) throws IOException
	{
		htmlwriter.write("<table  BORDER=\"0\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
		htmlwriter.write("<tr>\r\n");
		htmlwriter.write("<td align =left>Start Time: "+StartTime+"</td>\r\n");
		htmlwriter.write("<td align =right>End Time: "+EndTime+"</td>\r\n"); 
		htmlwriter.write("</tr>\r\n");
		htmlwriter.write("</table><BR>\r\n");
	}

	public  void SetTotalExecutionTime(String TimeString) throws IOException
	{
		htmlwriter.write("<table  BORDER=\"0\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
		htmlwriter.write("<tr>\r\n");
		htmlwriter.write("<td align =left>"+TimeString+"</td>\r\n");

		htmlwriter.write("</tr>\r\n");
		htmlwriter.write("</table><BR>\r\n");
	}
	public  void setMainTable(String titles[])
	{
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==0)
				{
					htmlwriter.write("<td >"+titles[i]+"</td>\r\n");
				}
			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public  void setTableHeaders(String titles[])
	{
		try
		{
			htmlwriter.write("<tr style=\"border: 1px solid black\">\n");
			for(int i = 0; i < titles.length; i++)
			{
				htmlwriter.write("<th class=\"theader\" colspan=\"2\">"+titles[i]+"</th>\r\n");
			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setChildTableHeaders(String titles[])
	{
		try
		{
			htmlwriter.write("<tbody class=\"hideTr\">\n");
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==(titles.length-1)||i==(titles.length-2))
				{
					htmlwriter.write("<th colspan=\"2\">"+titles[i]+"</font></th>\r\n");
				}
				else
				{
					htmlwriter.write("<th colspan=\"1\">"+titles[i]+"</font></th>\r\n");
				}

			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void setTableFooter(String titles[],int sessionHeaderLength,int connectionHeaderLength)
	{
		int colspan=1+sessionHeaderLength+1+connectionHeaderLength;
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==0)
				{
					htmlwriter.write("<td rowspan=\"2\" colspan ="+colspan+" width=\"0\" align=left bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
				}
				else
					if(i>0 && i<titles.length-1)
					{
						htmlwriter.write("<td rowspan=\"2\" colspan=\"3\" width=\"0\" align=center bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
					}
					else
					{
						htmlwriter.write("<td rowspan=\"2\" colspan=\"1\" width=\"0\" align=center bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
					}
			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setMainReportFooter(String titles[])
	{
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==4)
				{
					htmlwriter.write("<td rowspan=\"2\" colspan=\"1\" width=\"0\" align=right bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
				}

				else
				{
					htmlwriter.write("<td rowspan=\"2\" colspan=\"1\" width=\"0\" align=center bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
				}
			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void initializeBuildDetails(String startTime,String endTime,String nG1BaseBuild,String nGTestBuild,String IsbaseBuild,String IsTestBuild)
	{
		try
		{    	
			setContent("Top","Execution-Details");
			htmlwriter.write("<table border=\"2\" cellspacing=\"0\" width=\"30%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			htmlwriter.write("<tr bgcolor=\"green\">\r\n");
			htmlwriter.write("<th colspan=\"2\"  class=\"theader\">Description</th>\r\n");
			htmlwriter.write("<th colspan=\"2\" class=\"theader\">Base Build</th>\r\n");
			htmlwriter.write("<th colspan=\"2\" class=\"theader\">Test Build</th>\r\n");
			htmlwriter.write("</tr>\r\n");
			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("<td colspan=\"2\">nGeniusONE Build</td>\r\n");
			htmlwriter.write("<td colspan=\"2\">"+nG1BaseBuild+"</td>\r\n");
			htmlwriter.write("<td colspan=\"2\">"+nGTestBuild+"</td>\r\n");
			htmlwriter.write("</tr>\r\n");
			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("<td colspan=\"2\">Infinistream Build</td>\r\n");
			htmlwriter.write("<td colspan=\"2\">"+IsbaseBuild+"</td>\r\n");
			htmlwriter.write("<td colspan=\"2\">"+IsTestBuild+"</td>\r\n");
			htmlwriter.write("</tr>\r\n");

			if(startTime.compareToIgnoreCase("")!=0 && endTime.compareToIgnoreCase("")!=0)
			{
				htmlwriter.write("<td colspan=\"2\">Duration</td>\r\n");
				htmlwriter.write("<td colspan=\"2\">"+startTime+"</td>\r\n");
				htmlwriter.write("<td colspan=\"2\">"+endTime+"</td>\r\n");
				htmlwriter.write("</tr>\r\n");
			}

			/*	htmlwriter.write("<tr>\r\n");
				htmlwriter.write("<td colspan=\"2\">Execution Time</td>\r\n");
				htmlwriter.write("<td colspan=\"2\">"+baseExecutionTime+"</td>\r\n");
				htmlwriter.write("<td colspan=\"2\">"+testExecutionTime+"</td>\r\n");
				htmlwriter.write("</tr>\r\n");*/
			htmlwriter.write("</table>\r\n");
			htmlwriter.write("<BR>\r\n");
		}



		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	synchronized public void setTableTitleValues(String titles[],int width)
	{
		try
		{   


			htmlwriter.write("<table width="+width+"%>\r\n");
			htmlwriter.write("<tr >\r\n");
			for(int i = 0; i < titles.length; i++)
			{




				//htmlwriter.write("<td width=\"20%\" align=left bgcolor=\"BLACK\"><font face=\"Trebuchet MS\" color = \"WHITE\" size=\"2\">"+titles[i]+"</font></td>\r\n");
				if(i==0)
				{
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
				else if(i==1)

				{
					titles[i]=titles[i].replace("|",",");
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
				else if(i==2)
				{
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
				else if(i==3)
				{
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
				else if(i==4)
				{
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
				else if(i==5)
				{
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
				else if(i==6)
				{
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
				else if(i==7)
				{
					htmlwriter.write("<th>"+titles[i]+"</th>\r\n");
				}
			}

			htmlwriter.write("</tr>\r\n");




		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setSubReportColSpanTableTitleValues(String titles[],int x,int y,boolean header)
	{
		int colspan=x+y+1;
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(header==false)
				{
					if(i==0 ||  i == titles.length-1 || i == titles.length-2||i == titles.length-3)
					{
						htmlwriter.write("<td width=\"0\" rowspan=\"2\" align=left bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
					}
					else
						if(i==1)
						{
							htmlwriter.write("<td colspan ="+colspan+" width=\"0\" align=center bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}
					/*else
						if(i==2)
						{
							htmlwriter.write("<td colspan ="+colspan+" width=\"0\" align=center bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}*/
						else if(i>1 && i<(titles.length-3))
						{
							htmlwriter.write("<td colspan =\"3\" width=\"0\" align=center bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}
						else
						{
							htmlwriter.write("<td colspan =\"1\" width=\"0\" align=center bgcolor=\"#CDCD66\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}
				}
				else
					if(header==true)
					{
						colspan=colspan+1;
						if(i==0)
						{
							htmlwriter.write("<td colspan ="+colspan+" width=\"0\" align=center bgcolor=\"#FFFFFF\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}
						else if(i==1)
						{
							htmlwriter.write("<td colspan =\"7\" width=\"0\" align=center bgcolor=\"#33CDFF\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}
						else
							if(i==2)
							{
								htmlwriter.write("<td colspan =\"2\" width=\"0\" align=center bgcolor=\"#33CDFF\"><font face=\"Trebuchet MS\" color = \"black\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
							}
					}

			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setTestTableTitleValues(String header,int sessionheaderlength,int connectionheaderlength)
	{
		int colspan=1+sessionheaderlength+1+connectionheaderlength+3+3+1;
		try
		{

			htmlwriter.write("	<td colspan ="+colspan+" width=\"0\" bgcolor=\"#CDCD66\" align=left><B><font align=\"Center\" face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">&nbsp;&nbsp;&nbsp;"+header+"</font></B></td>\r\n");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setTableTitleValues(String StartTime, String TestStatus, String EndTime)
	{
		try
		{
			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("	<td width=\"0\" bgcolor=\"#ffffb7\"><font align=LEFT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">&nbsp;&nbsp;&nbsp;"+StartTime+"</font></td>\r\n");
			htmlwriter.write("	<td width=\"0\" bgcolor=\"#ffffb7\"><font align=Center face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">&nbsp;&nbsp;"+TestStatus+"</font></td>\r\n");
			htmlwriter.write("	<td width=\"0\" bgcolor=\"#ffffb7\"><font align=RIGHT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+EndTime+"</font></td>\r\n");
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setTableValues(String msg)
	{
		String color = "#FFDbB9";
		try {
			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("<td width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"3\"> &nbsp;&nbsp;<B><font color=\"BLUE\">" + msg +"</B></font></td>\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public  void Set_Summary_Table(String values[]) throws IOException
	{
		htmlwriter.write("<tr>\r\n");
		for(int i = 0; i < values.length; i++)
		{
			if(i==0)
			{
				htmlwriter.write("<td style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=#FFFFFF><font color=#0000CC face=\"Trebuchet MS\" size=\"2\">" + values[i] + "</font></td>\r\n");
				//htmlwriter.write("<td  width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
			else
				if(i==1)
				{
					htmlwriter.write("<td style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=#FFFFFF><font color=#0000CC face=\"Trebuchet MS\" size=\"2\">" + values[i].toUpperCase() + "</font></td>\r\n");
					//htmlwriter.write("<td  width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
				}
				else
				{
					htmlwriter.write("<td style=\"border: 1px solid black;\" colspan =1 align = \"center\" width=\"0\" bgcolor=#FFFFFF><font color=#0000CC face=\"Trebuchet MS\" size=\"2\">" + values[i] + "</font></td>\r\n");

				}

		}
		htmlwriter.write("</tr>\r\n");
	}




	public  void setMainTableValues(String values[]) throws IOException
	{
		String color = "";

		if( logcount == 1 )
		{
			color = "#EEEEEE";
			logcount = 0;
		}
		else
		{
			color = "#FFFFFF";
			logcount = 1;
		}
		htmlwriter.write("<tr>\r\n");
		for(int i = 0; i < values.length; i++)
		{
			
			if(i>1)
			{
				htmlwriter.write("<td align = \"center\" width=\"0\" bgcolor=\""+color+"\"><b><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></b></td>\r\n");
			}
			else
			{
				htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
		}
		htmlwriter.write("</tr>\r\n");

	}


	public  void setTableValues(String values[])
	{
		String color = "";
		int fail_count=0;
		int mismatch_count=0;
		if( logcount == 1 )
		{
			color = "#EEEEEE";
			logcount = 0;
		}
		else
		{
			color = "#FFFFFF";
			logcount = 1;
		}
		try
		{

			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < values.length; i++)
			{
				if((i==0)||(i==1))
				{
					htmlwriter.write("<td colspan=\"1\" align = \"left\" width=\"0\" bgcolor=\""+color+"\">" + values[i] + "</td>\r\n");
				}
				/*else if((i==2)||(i==3)||(i==4)||(i==5)||(i==6)||(i==7))
				{
					htmlwriter.write("<td  style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=\""+color+"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + values[i] + "</td>\r\n");
				}*/
				else if(i==(values.length-1))
				{
					htmlwriter.write("<td colspan=\"1\" align = \"center\" width=\"0\" bgcolor=\""+color+"\">" + values[i] + "</td>\r\n");
				}
				else if(i==(values.length-2))
				{
					htmlwriter.write("<td colspan=\"1\" align = \"center\" width=\"0\" bgcolor=\""+color+"\">" + values[i] + "</td>\r\n");
				}
				else 
				{
					htmlwriter.write("<td colspan=\"1\" align = \"center\" width=\"0\" bgcolor=\""+color+"\">" + values[i] + "</td>\r\n");
				}

				//htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setSummaryTableValues(String values[])
	{
		String color = "";
		int fail_count=0;
		int mismatch_count=0;
		if( logcount == 1 )
		{
			color = "#EEEEEE";
			logcount = 0;
		}
		else
		{
			color = "#FFFFFF";
			logcount = 1;
		}
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < values.length; i++)
			{
				htmlwriter.write("<td colspan=\"2\">" + values[i] + "</td>\r\n");
			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void setTableValuesNew(String values[])
	{
		String color = "";
		int fail_count=0;
		int mismatch_count=0;
		if( logcount == 1 )
		{
			color = "#EEEEEE";
			logcount = 0;
		}
		else
		{
			color = "#FFFFFF";
			logcount = 1;
		}
		try
		{
			htmlwriter.write("<tr data-toggle=\"toggle\">\r\n");
			for(int i = 0; i < values.length; i++)
			{

				if(i==0)
				{
					String classname="plusminus"+values[i];
					htmlwriter.write("<td colspan=\"1\"><a id="+values[i]+"><span class=\""+classname+"\">[+] </span></a>" + values[i] + "</td>\r\n");
				}
				else if((i==1))
				{
					htmlwriter.write("<td colspan=\"1\">" + values[i] + "</td>\r\n");
				}
				else
				{
					htmlwriter.write("<td colspan=\"1\">" + values[i] + "</td>\r\n");
				}

				//htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
			String SubReportContent = values[values.length-1];
			SubReportContent = SubReportContent.split("href=")[1].split(">")[0].trim();
			String hostname=null;
			String SharedLocation=null;
			try {
				hostname = Inet4Address.getLocalHost().getHostAddress();
				SharedLocation = "http:\\\\" + hostname +":8080";
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			SubReportContent = SubReportContent.replace(SharedLocation, System.getProperty("user.dir"));
			SubReportContent = RP.readSubReportHTMLFile(SubReportContent);

			htmlwriter.write(SubReportContent);
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public  void closeSummaryTable()
	{
		try
		{

			//	htmlwriter.write("</table><BR>\n");
			htmlwriter.write("</table>\n");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void closeTable()
	{
		try
		{
			htmlwriter.write("</table>\n");
			//		htmlwriter.write(" <div class=\"footer\"></div>\n");
			/*	htmlwriter.write("</body>\n");
			htmlwriter.write("</div>\n");
			htmlwriter.write("</html>\n");*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void closetbody()
	{
		try
		{
			htmlwriter.write("</tbody>\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public  void write(String line)
	{
		try
		{
			htmlwriter.write(line + "\r\n");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	public  String getFilePath()
	{
		return reportFile;
	}

	public void setTitle(String ReportTitle) throws IOException
	{
		htmlwriter.write("<HTML>\r\n");
		htmlwriter.write("<HEAD>\r\n");
		//htmlwriter.write("<BR><TITLE>" + PageTitle + "</TITLE>\r\n");
		htmlwriter.write("</HEAD>\r\n");
		htmlwriter.write("<body bgcolor=\"#FFFFFF\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" topmargin=\"0\" text=\"#000000\" link=\"#FFFFFF\" vlink=\"#C50021\" alink=\"#FF7D00\">\r\n");

		htmlwriter.write("<table  width=\"100%\" height:\"100%\" border=\"1\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n"); 
		htmlwriter.write("<tr>\r\n"); 
		htmlwriter.write("<td bgcolor=\"#C8FF33\" colspan=2 BORDER=\"1\" width=\"99%\" ><center><font face=\"serif\" color = \"black\" size=\"6\"><b>"+ ReportTitle + "</b></font></center></td>\r\n"); 

		htmlwriter.write("</tr>\r\n"); htmlwriter.write("</table>\r\n");
		//htmlwriter.write("<BR>\r\n");
	}

	public void setTitle1(String PageTitle, String ReportTitle) throws IOException
	{
		htmlwriter.write("<HTML>\r\n");
		htmlwriter.write("<HEAD>\r\n");
		htmlwriter.write("<BR><TITLE>" + PageTitle + "</TITLE>\r\n");
		htmlwriter.write("</HEAD>\r\n");
		htmlwriter.write("<body bgcolor=\"#FFFFFF\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" topmargin=\"0\" text=\"#000000\" link=\"#FFFFFF\" vlink=\"#C50021\" alink=\"#FF7D00\">\r\n");

		htmlwriter.write("<table  width=\"100%\" height:\"100%\" border=\"1\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n"); 
		htmlwriter.write("<tr>\r\n"); 
		htmlwriter.write("<td bgcolor=\"#C8FF33\" colspan=2 BORDER=\"1\" width=\"99%\" ><center><font face=\"serif\" color = \"black\" size=\"6\"><b>"+ ReportTitle + "</b></font></center></td>\r\n"); 

		htmlwriter.write("</tr>\r\n"); htmlwriter.write("</table>\r\n");
		htmlwriter.write("<BR>\r\n");
	}

	public void setHeader(String Header) throws IOException
	{
		//htmlwriter.write("<BR>\r\n");
		htmlwriter.write("<body bgcolor=\"#FFFFFF\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" topmargin=\"0\" text=\"#000000\" link=\"#FFFFFF\" vlink=\"#C50021\" alink=\"#FF7D00\">\r\n");
		htmlwriter .write("<table  width=\"75%\" height:\"100%\" border=\"0\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n"); 
		htmlwriter.write("<tr>\r\n"); 
		htmlwriter .write("<td bgcolor=\"#FFFFFF\" colspan=0 BORDER=\"0\" width=\"99%\" ><left><font face=\"serif\" color = \"black\" size=\"5\"><b>"+ Header + "</b></font></left></td>\r\n"); 

		htmlwriter.write("</tr>\r\n"); htmlwriter.write("</table>\r\n");
		//	htmlwriter.write("<BR>\r\n");
	}

	public void initializeMainTable(String id,String fileName)
	{
		try
		{    

			htmlwriter.write("<B><FONT FACE =\"Times New Roman\" size =\"4\" id="+"\""+id+"\""+">"+"&nbsp;&nbsp;&nbsp;"+fileName+"</FONT></B><BR>");
			//	htmlwriter.write("<h4 id="+"\""+id+"\""+">"+"&nbsp;&nbsp;&nbsp;"+fileName+"</h4>");

			/*htmlwriter.write("<BR><table border=\"1\" cellspacing=\"1\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			htmlwriter.write("<td colspan=\"3\" width=\"50%\" bgcolor=\"000099\"><font face=\"Trebuchet MS\" color = \"WHITE\" size=\"2\">TEST NAME: <b>nG1 UI Test</b></font></td>\r\n");
			htmlwriter.write("<td colspan=\"3\" width=\"50%\" bgcolor=\"000099\" align=\"right\"><font face=\"Trebuchet MS\" color = \"WHITE\" size=\"2\">OWNER: <b>Automation Team</b></font></td>\r\n");
			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("<td colspan=\"10\" width=\"100%\" align=\"left\"><font face=\"Trebuchet MS\" color = \"black\" size=\"3\"><b>"+filName+"</b></font></td>\r\n");
			htmlwriter.write("</tr>\r\n");
			//htmlwriter.write("</table><BR>\n");
			 */			
			logcount = 0;


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void initializeMainTableHeader(String header)
	{
		try
		{   
			//writeScripts();
			htmlwriter.write("<div>");
			htmlwriter.write("<div class=\"header\"><h2>"+header+"</h2></div>");
			htmlwriter.write("</div>");



		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void Set_MainTable_Header_2_1(String[] val) {
		try {
			htmlwriter.write("<tr>\n");
			for(int i=0;i<val.length;i++)
			{
				htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" align=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"1\">"+val[i]+"</font></td>\n");
				//htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" ALIGN=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\">Total Flows in Base/Test</font></td>\n");
				//htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" ALIGN=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\">Duplicate Flows in Base/Test</font></td>\n");
				//htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" ALIGN=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\">Missing Flows in Base/Test</font></td>\n");
				//htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" ALIGN=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\">Matched Flows</font></td>\n");
				//htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" ALIGN=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\">Diff Flows</font></td>\n");
				//htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" ALIGN=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"></font></td>\n");
				//htmlwriter.write("<td colspan=\"1\" style=\"border: 1px solid black;\" width=\"0\" ALIGN=center bgcolor=\"#663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"></font></td>\n");
			}
			htmlwriter.write("</tr>\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTableFooter(String footerString,int headerLength)
	{
		try
		{
			htmlwriter.write("<tr>\r\n");

			htmlwriter.write("<td rowspan =\"1\" colspan="+"\""+headerLength+"\"" +"width=\"0\" align=\"center\" bgcolor=\"white\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+footerString+"</b></font></td>\r\n");




			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}  


}





