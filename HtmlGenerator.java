//Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
//Jad home page: http://www.kpdus.com/jad.html
//Decompiler options: packimports(3) 
//Source File Name:   HtmlGenerator.java

package com.netscout.automation.reporting;

import java.io.FileWriter;
import java.io.IOException;


public class HtmlGenerator {

	FileWriter htmlwriter;
	static String reportFile;
	static int logcount = 0;
	//static String jscript = "<script language=\"JavaScript\">	function cv(src1,src2,src3,dirpath) {var a= dirpath + '\\InternalFiles\\\\xlsc.exe'; var WshShell = new ActiveXObject( 'WScript.Shell' ); ReturnCode = WshShell.Run(a+' \"'+src1+'\" \"'+src2+'\" /r:'+src3+' /close /closef /mcm:l /cr:2 /sr:50 /nc /dr+'); } </script>";
	static String installation_log = System.getProperty("user.dir")+"\\Logs\\sg_install_verify.log"; 
	static String simulation_log = System.getProperty("user.dir")+"\\Logs\\sg_simulation.log";

	//Winmerge
	/*static String jscript = "<script language=\"JavaScript\">	function cv(src1,src2,src3,dirpath) {var a= dirpath + '\\InternalFiles\\\\xlsc.exe'; var WshShell = new ActiveXObject( 'WScript.Shell' ); ReturnCode = WshShell.Run(a+' \"'+src1+'\" \"'+src2+'\" /r:'+src3+' /close /closef /mcm:l /cr:2 /sr:50 /nc /dr+'); } </script>";
	 static String jscript1 = "<script language=\"JavaScript\">	" +
	 				"function winmerge(src1,src2,src3,dirpath) " +
	 				"{" +
	 				"var a= dirpath + '\\\\InternalFiles\\\\WinMerge-2.8.4-exe\\\\WinMerge.exe'; " +
	 				"var WshShell = new ActiveXObject( 'WScript.Shell' ); " +
	 				"ReturnCode = WshShell.Run(a+' \"' + src1 + '\" \"'+ src2 +'\"'); " +
	 				"} " +
	 				"</script>";

	 static String jscript2 = "<script language=\"JavaScript\">	" +
		"function AraxisMerger_Script(src1,src2,src3,dirpath) " +
		"{" +
		//"var a= dirpath + '\\\\InternalFiles\\\\WinMerge-2.8.4-exe\\\\WinMerge.exe'; " +
		"var a= dirpath + '\\\\Compare.exe'; " +
		"var WshShell = new ActiveXObject( 'WScript.Shell' ); " +
		"ReturnCode = WshShell.Run(a+' \"' + src1 + '\" \"'+ src2 +'\"'); " +
		"} " +
		"</script>";*/ 

	static String jscript = "<script language=\"JavaScript\">	function cv(src1,src2,src3,dirpath) {var a= dirpath + '\\InternalFiles\\\\xlsc.exe'; var WshShell = new ActiveXObject( 'WScript.Shell' ); ReturnCode = WshShell.Run(a+' \"'+src1+'\" \"'+src2+'\" /r:'+src3+' /close /closef /mcm:l /cr:2 /sr:50 /nc /dr+'); } </script>";
	static String jscript1 = "<script language=\"JavaScript\">	" +
	"function winmerge(src1,src2,src3,dirpath) " +
	"{" +
	"var a= dirpath + '\\\\InternalFiles\\\\WinMerge-2.8.4-exe\\\\WinMerge.exe'; " +
	"var WshShell = new ActiveXObject( 'WScript.Shell' ); " +
	"ReturnCode = WshShell.Run(a+' \"' + src1 + '\" \"'+ src2 +'\"'); " +
	"} " +
	"</script>";

	static String jscript2 = "<script language=\"JavaScript\">	" +
	"function AraxisMerger_Script(src1,src2) " +
	"{" +
	//"var a= dirpath + '\\\\InternalFiles\\\\WinMerge-2.8.4-exe\\\\WinMerge.exe'; " +
	"var a= \'\"C:\\\\Program Files (x86)\\\\Araxis\\\\Araxis Merge v6.5\\\\Merge.exe\"'; " +
	"var WshShell = new ActiveXObject( 'WScript.Shell' ); " +
	"ReturnCode = WshShell.Run(a+' \"' + src1 + '\" \"'+ src2 +'\"'); " +
	"} " +
	"</script>";
	static String index = "0";
	public HtmlGenerator(String path)
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
	public static String Get_Index()
	{
		Integer x = Integer.valueOf(index);
		int ind = x.intValue() +1;
		index = String.valueOf(ind); 
		return index;
	}

	public HtmlGenerator(String path, boolean val)
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

	// public void  Close()
	 public   void  Close()
	{
		try
		{
			htmlwriter.close();
		}
		catch(Exception exception) { }
	}

	 public void setTitle(String title)

	{
		try
		{
			String NetScout_Logo = System.getProperty("user.dir")+"\\InternalFiles\\Images\\Netscout.bmp";
			htmlwriter.write("<HTML>\r\n");
			htmlwriter.write("<HEAD>\r\n");

			htmlwriter.write("<BR><TITLE>" + title + "</TITLE>\r\n");
			htmlwriter.write(jscript);
			htmlwriter.write(jscript1);
			htmlwriter.write(jscript2);
			htmlwriter.write("</HEAD>\r\n");
			htmlwriter.write("<BR>\r\n");
			htmlwriter.write("<body bgcolor=\"#9EC0FF\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" topmargin=\"0\" text=\"#000000\" link=\"#C50021\" vlink=\"#C50021\" alink=\"#0000CC\">\r\n");
			htmlwriter.write("<table border=\"1\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("<td colspan=2 BORDER=\"1\" width=\"99%\" bgcolor=\"#222222\"><center><font face=\"serif\" color = \"FFFFFF\" size=\"4\"><b>"+title+"</b></font></center></td>\r\n");
			//String lnk = "<img  BORDER=\"1\"src="+NetScout_Logo+" alt=\"NetScout\"/>";
			//htmlwriter.write("<td bgcolor=\"#363D4D\">"+lnk+"</td>\r\n");
			htmlwriter.write("</tr>\r\n");
			htmlwriter.write("</table>\r\n");
			htmlwriter.write("<BR>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	 public void setContentRightJustified(String content)
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
	 public void SetNavigationText(String content)
	{
		try
		{  if(content.contains(" >> "))
			htmlwriter.write("<Left><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"2\">" + content +"</FONT></Left>");
		else
			htmlwriter.write("<Left><FONT face=\"Trebuchet MS\" color = \"003300\" size=\"2\">" + content +"</FONT></Left>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 public void setContentLeftJustified(String content)
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
	 public void setContentCenterJustified(String content)
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

	 public void setContent(String content)
	{
		try
		{
			if((content.contains("nSI")||content.contains("PM")||content.contains("IS(s)")))
			{
				String str1 = content.split("-")[0].trim();
				String str2 = content.split("-")[1];

				htmlwriter.write("&nbsp;&nbsp;&nbsp;&nbsp;<Left><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">" + str1 +": "+ "<b>"+str2+"</FONT></b></Left>");
			}

			else
			{
				htmlwriter.write("&nbsp;&nbsp;&nbsp;&nbsp;<Left><FONT face=\"Trebuchet MS\" color = \"BLACK\" size=\"2\">" + content + "</FONT></Left>");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	 public void setKeyAndValue(String key, String value)
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

	 public void setLogFilePath()
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
	 public void setSimulationLogFilePath()
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

	 public void setKeyAndValues(String keys[], String values[])
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

	 public void initialize_StartTime_TestStatus_Table()
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
	 public void initializeTable()
	{
		try
		{
			htmlwriter.write("<BR><table BORDER=\"1\" frame=\"border\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			logcount = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 public void SetDateTime(String StartTime, String EndTime) throws IOException
	{
		htmlwriter.write("<table  BORDER=\"0\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
		htmlwriter.write("<tr>\r\n");
		htmlwriter.write("<td align =left>Start Time: "+StartTime+"</td>\r\n");
		htmlwriter.write("<td align =right>End Time: "+EndTime+"</td>\r\n"); 
		htmlwriter.write("</tr>\r\n");
		htmlwriter.write("</table><BR>\r\n");
	}

	 public void setMainTable(String titles[])
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

	 public void setTableTitleValues(String titles[])
	{
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==0)
				{
					htmlwriter.write("<td colspan=\"1\" width=\"0\" align=left bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
				}
				else
				{
					htmlwriter.write("<td colspan=\"1\" width=\"0\" align=left bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
				}

			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	 public void setTableTitleValueswithColSpan(String titles[])
	{
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==0)
				{
					htmlwriter.write("<td colspan=\"1\" width=\"0\" align=left bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
				}
				else
					if(i==1)
					{
						htmlwriter.write("<td colspan=\"6\" width=\"0\" align=center bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
					}
					else
						if(i==2)
						{
							htmlwriter.write("<td colspan=\"1\" width=\"0\" align=center bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}
						else
							if(i>=3)
							{
								htmlwriter.write("<td colspan=\"1\" width=\"0\" align=center bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
							}


			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 public void setTableTitleValueswithColSpan1(String titles[])
	{
		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==0)
				{
					htmlwriter.write("<td colspan=\"1\" width=\"0\" align=left bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
				}
				else
					if(i==1)
					{
						htmlwriter.write("<td colspan=\"1\" width=\"0\" align=center bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
					}
					else
						if(i==2)
						{
							htmlwriter.write("<td colspan=\"2\" width=\"0\" align=center bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
						}
						else
							if(i>=3)
							{
								htmlwriter.write("<td colspan=\"2\" width=\"0\" align=center bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+titles[i]+"</b></font></td>\r\n");
							}


			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 public void setTableValuesAPI(String titles[])
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

		try
		{
			htmlwriter.write("<tr>\r\n");
			for(int i = 0; i < titles.length; i++)
			{
				if(i==0)
				{
					htmlwriter.write("<td  colspan=\"1\" align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + titles[i] + "</font></td>\r\n");
				}
				else
					if(i==1)
					{
						htmlwriter.write("<td colspan=\"2\" align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + titles[i] + "</font></td>\r\n");
					}



			}

			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 public void setTableTitleValues(String StartTime, String TestStatus, String EndTime)
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
	 public void setTableValues(String msg)
	{
		String color = "#FFDbB9";
		try {
			htmlwriter.write("<tr>\r\n");
			htmlwriter.write("<td width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"3\"> &nbsp;&nbsp;<B><font color=\"BLUE\">" + msg +"</B></font></td>\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 public void Set_Summary_Table(String values[]) throws IOException
	{
		htmlwriter.write("<tr>\r\n");
		for(int i = 0; i < values.length; i++)
		{
			if(i==0)
			{
				htmlwriter.write("<td align = \"left\" width=\"0\" bgcolor=#FFFFFF><font color=#0000CC face=\"Trebuchet MS\" size=\"2\">" + values[i] + "</B></font></td>\r\n");
				//htmlwriter.write("<td  width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
			else
			{
				htmlwriter.write("<td align = \"center\" width=\"0\" bgcolor=#FFFFFF><font color=#0000CC face=\"Trebuchet MS\" size=\"2\">" + values[i] + "</B></font></td>\r\n");

			}

		}
		htmlwriter.write("</tr>\r\n");
	}

	 public void Set_Intreface_Details (String values[]) throws IOException
	{
		htmlwriter.write("<tr>\r\n");
		for(int i = 0; i < values.length; i++)
		{
			if(i==0)
				htmlwriter.write("<td align = \"left\" width=\"0\" bgcolor=#FFFFFF><font color=#0000CC face=\"Trebuchet MS\" size=\"2\">" + values[i] + "</B></font></td>\r\n");
			else
				htmlwriter.write("<td colspan=2 align = \"center\" width=\"0\" bgcolor=#FFFFFF><font color=#0000CC face=\"Trebuchet MS\" size=\"2\">" + values[i] + "</B></font></td>\r\n");
		}
		htmlwriter.write("</tr>\r\n");
	}

	 public void setMainTableValues(String values[]) throws IOException
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
			if(i!=0)
			{
				htmlwriter.write("<td align = \"center\" width=\"0\" bgcolor=\""+color+"\"><b><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></b></td>\r\n");
			}
			else
			{
				htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
		}


	}

	 public void setTableValuesWithRowSpan(String values[],int rowSpan)
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
		try
		{

			htmlwriter.write("<tr border=\"1\">\r\n");
			for(int i = 0; i < values.length; i++)
			{
				if(values[i].contains("> "))
					values[i].replace("> ", ">");
				if(values[i].contains(" <"))
					values[i].replace(" <", "<");

				if ((values[i].contains(">pass<")) || (values[i].equalsIgnoreCase("pass")))
				{
					//color = "#336600";
					htmlwriter.write("<td align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"GREEN\">" + values[i] + "</B></font></td>\r\n");
				}
				else if ((values[i].contains(">fail<")) || (values[i].equalsIgnoreCase("fail")) )
				{

					htmlwriter.write("<td align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font align = center face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"RED\">" + values[i] + "</B></font></td>\r\n");
				}
				else if ((values[i].contains(">warning<")) || (values[i].equalsIgnoreCase("warning")))
				{
					htmlwriter.write("<td  align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"ORANGE\">!!" + values[i] + "!!</B></font></td>\r\n");
				}
				else if ((values[i].contains(">error<")) || (values[i].equalsIgnoreCase("error")))
				{
					htmlwriter.write("<td  align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"violet\">" + values[i] + "</B></font></td>\r\n");
				}
				else if(i!=0)
				{
					htmlwriter.write("<td align = \"left\" width=\"0\" bgcolor=\""+color+"\"><b><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></b></td>\r\n");
				}
				else
				{

					if(i==0)
						htmlwriter.write("<td rowspan =\""+rowSpan+"\" align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
					else
						htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
				}
			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	 public void setTableNoRowSpanValues(String values[]) throws IOException
	{
		String color = "";
		String fontcolor="";
		color="#FFFFFF";

		htmlwriter.write("<tr>\r\n");

		for (int i = 1; i<values.length; i++)
		{
			if(values[i].compareToIgnoreCase("PASS")==0)
				fontcolor="green";
			else
				if(values[i].compareToIgnoreCase("FAIL")==0)
					fontcolor="red";
				else
					fontcolor="black";

			//htmlwriter.write("<td style=\"border: 1px solid black;\" align = \"justify\" width=\"0\" bgcolor=\""+color+ "\"><font color=#black face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");

			if(i==1)
			{
				htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
			else
			{
				htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
			}
		}
		htmlwriter.write("</tr>\r\n");
	}
	
	 public void setTableValues1(String values[])
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
				if((i==0))
				{
					htmlwriter.write("<td  style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
				}
				else if(i==1)
				{
					
					htmlwriter.write("<td  style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
				}
				else
				{
					htmlwriter.write("<td  style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
				}
				
			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 public void setTableValues(String values[])
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
		try
		{

			htmlwriter.write("<tr border=\"1\">\r\n");
			for(int i = 0; i < values.length; i++)
			{
				if(values[i].contains("> "))
					values[i].replace("> ", ">");
				if(values[i].contains(" <"))
					values[i].replace(" <", "<");

				if ((values[i].contains(">pass<")) || (values[i].equalsIgnoreCase("pass")))
				{
					//color = "#336600";
					htmlwriter.write("<td align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"GREEN\">" + values[i] + "</B></font></td>\r\n");
				}
				else if ((values[i].contains(">fail<")) || (values[i].equalsIgnoreCase("fail")) )
				{

					htmlwriter.write("<td align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font align = center face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"RED\">" + values[i] + "</B></font></td>\r\n");
				}
				else if ((values[i].contains(">warning<")) || (values[i].equalsIgnoreCase("warning")))
				{
					htmlwriter.write("<td  align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"ORANGE\">!!" + values[i] + "!!</B></font></td>\r\n");
				}
				else if ((values[i].contains(">error<")) || (values[i].equalsIgnoreCase("error")))
				{
					htmlwriter.write("<td  align = \"center\" width=\"0\" bgcolor=\""+color+"\"><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;<B><font color=\"violet\">" + values[i] + "</B></font></td>\r\n");
				}
				else if(i!=0)
				{
					htmlwriter.write("<td align = \"left\" width=\"0\" bgcolor=\""+color+"\"><b><font face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></b></td>\r\n");
				}
				else
				{

					//htmlwriter.write("<td  align = \"center\"  width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
					htmlwriter.write("<td  align = \"left\" width=\"0\" bgcolor=\""+color+"\"><font color=#0000CC face=\"Trebuchet MS\" size=\"2\"> &nbsp;&nbsp;" + values[i] + "</font></td>\r\n");
				}
			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	 public void closeTable()
	{
		try
		{
			htmlwriter.write("</table><BR>\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	 public void InitialiseTableNOSpace()
	{
		try
		{
			htmlwriter.write("<table BORDER=\"1\" frame=\"border\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
			logcount = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	 public void closeTableNOSpace()
	{
		try
		{
			htmlwriter.write("</table>\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	 public void generate()
	{
		try
		{
			htmlwriter.write("</body></HTML>\n");
			try
			{
				htmlwriter.close();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	 public void write(String line)
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

	 public static String getFilePath()
	{
		return reportFile;
	}

	 public void SetTotalExecutionTime(String TimeString) throws IOException
	{
		htmlwriter.write("<table  BORDER=\"0\" cellspacing=\"0\" width=\"100%\" id=\"AutoNumber1\" cellpadding=\"0\">\r\n");
		htmlwriter.write("<tr>\r\n");
		htmlwriter.write("<td align =left>"+TimeString+"</td>\r\n");

		htmlwriter.write("</tr>\r\n");
		htmlwriter.write("</table><BR>\r\n");
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
	 public void setAppTableFooter(String footerString,int headerLength)
	{
		String result[]=footerString.split(",");
		try
		{
			int i=0;
			htmlwriter.write("<tr>\r\n");
			for(String str:result)
			{
				str=str.replace("TOTAL ", "");
				if(i<=0)
					htmlwriter.write("<td rowspan =\"1\" colspan="+"\""+headerLength+"\"" +"width=\"0\" align=\"center\" bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+str+"</b></font></td>\r\n");
				else
					htmlwriter.write("<td rowspan =\"1\" colspan=\"1\" width=\"0\" align=\"center\" bgcolor=\"663300\"><font face=\"Trebuchet MS\" color = \"FFFF08\" size=\"2\"><b>"+str+"</b></font></td>\r\n");
				i++;
			}
			htmlwriter.write("</tr>\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}    
	 public void generateFrame (String baseHTML,String testHTML) throws IOException

	{
		htmlwriter.write("<frameset cols=\"50%,50%\">");
		htmlwriter.write("<frame src="+baseHTML+" scrolling=\"yes\" border=\"50\" frameborder=\"20\"/>");
		htmlwriter.write("<frame src="+testHTML+"  scrolling=\"yes\"  border=\"55\" frameborder=\"20\"/>");
		htmlwriter.write("</frameset>");
	}
	 public void printLine (String line) throws IOException

	{
		htmlwriter.write(line+"\n");

	}
	public void setMainTableValues1(String values[], int rowspan) throws IOException 
	{
		String color = "";
		String fontcolor = "";
		color="#FFFFFF";

		htmlwriter.write("<tr>\r\n");

		for (int i = 0; i<values.length; i++)
		{
			//htmlwriter.write("<td rowspan=\"2\" style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=\""+color+ "\"><font color=#blue face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");
			if(values[i].compareToIgnoreCase("PASS")==0)
				fontcolor="green";
			else
				if(values[i].compareToIgnoreCase("FAIL")==0)
					fontcolor="red";
				else
					fontcolor="black";
			if(i==0 || i==1)
			{
				htmlwriter.write("<td rowspan="+"\""+rowspan+"\""+" style=\"border: 1px solid black;\" align = \"left\" width=\"0\" bgcolor=\""+color+ "\"><font color=#000000 face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");
			}
			/*else if(i==1)
			{
				htmlwriter.write("<td style=\"border: 1px solid black"+";\" align = \"left\" width=\"0\" bgcolor=\""+color+ "\"><font color="+fontcolor+" face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");
			}*/
			else
			{
				htmlwriter.write("<td style=\"border: 1px solid black"+";\" align = \"left\" width=\"0\" bgcolor=\""+color+ "\"><font color="+fontcolor+" face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");
			}
		}
		htmlwriter.write("</tr>\r\n");
	}
	public void setMainTableValues2(String values[]) throws IOException 
	{
		String color = "";
		String fontcolor="";
		color="#FFFFFF";

		htmlwriter.write("<tr>\r\n");

		for (int i = 2; i<values.length; i++)
		{
			if(values[i].compareToIgnoreCase("PASS")==0)
				fontcolor="green";
			else
				if(values[i].compareToIgnoreCase("FAIL")==0)
					fontcolor="red";
				else
					fontcolor="black";

			//htmlwriter.write("<td style=\"border: 1px solid black;\" align = \"justify\" width=\"0\" bgcolor=\""+color+ "\"><font color=#black face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");

			if(i==2)
			{
				htmlwriter.write("<td style=\"border: 1px solid black"+";\" align = \"left\" width=\"0\" bgcolor=\""+color+ "\"><font color="+fontcolor+" face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");
			}
			else
			{
				htmlwriter.write("<td style=\"border: 1px solid black"+";\" align = \"left\" width=\"0\" bgcolor=\""+color+ "\"><font color="+fontcolor+" face=\"verdana\" size=\"2\"> "+ values[i] + "</font></td>\r\n");
			}
		}
		htmlwriter.write("</tr>\r\n");
	}

}





