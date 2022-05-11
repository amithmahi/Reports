package com.netscout.automation.reporting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.netscout.automation.AutomationUtils;
import com.netscout.automation.ComparisonResults;
import com.netscout.automation.portal.NewPortal;
import com.netscout.htmldiffviewer.HTML_DiffView;
import com.netscout.htmldiffviewer.HTML_LinkedList_Diffview;

import nG1CyberInvestigator.AutomationProperty;
import nG1CyberInvestigator.INIFile;
import nG1CyberInvestigator.Main;
import nG1CyberInvestigator.ParseConfigFile;
import nG1CyberInvestigator.TrackerFile;


public class CIReporting {

	static String inputSetupINI="";
	static String keyConfigINI="";
	static String keythresholdINI="";

	static String log4jproperties="";
	static String logFileName="";



	public CIReporting(String inputINI,String logProperties,String logName) {
		// TODO Auto-generated constructor stub
		inputSetupINI=inputINI;
		toolName=pcf.getToolName();
		aTAModule1=pcf.getaTAModule1();
		aTAModule2=pcf.getaTAModule2();
		aTAModule3=pcf.getaTAModule3();

		aTAModule1Tab1=pcf.getModule1Tab1();
		aTAModule1Tab2=pcf.getModule1Tab2();
		aTAModule1Tab3=pcf.getModule1Tab3();

		aTAModule2Tab1=pcf.getModule2Tab1();
		aTAModule2Tab2=pcf.getModule2Tab2();

		INIFile objINI = new INIFile(System.getProperty("user.dir")+"\\ConfigFiles\\"+pcf.getToolName()+"\\"+pcf.getToolName()+".ini");




		keyConfigINI=System.getProperty("user.dir")+"\\ConfigFiles\\"+pcf.getToolName()+"\\"+objINI.getStringProperty("KeyComparison", "KeyConfigFile");
		keythresholdINI=System.getProperty("user.dir")+"\\ConfigFiles\\"+pcf.getToolName()+"\\"+objINI.getStringProperty("KeyComparison", "KeyThresholdFile");

		this.log4jproperties=logProperties;
		this.logFileName=logName;

	}

	ParseConfigFile pcf=new ParseConfigFile(inputSetupINI);
	static String toolName="";
	static String aTAModule1=""; // Risk Visualization
	static String aTAModule2=""; // Host Investigation
	static String aTAModule3=""; // Host Investigation
	//static String aTAModule3=""; // Network Investigation

	static String aTAModule1Tab1=""; //SecurityRisk
	static String aTAModule1Tab2=""; //ThreatIndicator
	static String aTAModule1Tab3="";

	static String aTAModule2Tab1=""; //MatrixView
	static String aTAModule2Tab2=""; //AnalysisView
	static String updateNewPortalStatus="false";



	public  String generateMainReport(String baseFolder, String testFolder,ParseConfigFile config)
	{
		File HTMLFile;
		HTMLFile = new File(testFolder+"\\"+config.getToolName()+"\\"+config.getToolName()+".html");

		String Title = config.getMailTitle();
		Title = Title.replace("$sCIBuild", "");
		Title = Title.replace("$ISBuild", "");
		Title = Title.replace("$SetupType", "");

		String[] testCases=pcf.getTestCases();
		String testCaseName="";

		for(String testcase:testCases)
		{
			testCaseName=testcase.split(",")[0];
		}

		InetAddress addr=null;
		String hostname = null;
		try {
			addr = InetAddress.getLocalHost();
			hostname=addr.getHostAddress();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String testServerPath="";
		//String testServerPath=pcf.getServerPath();
		if(config.getMode().compareToIgnoreCase("Compare")==0 || config.getMode().compareToIgnoreCase("ReportOnly")==0)
		{
			//String UNCPath="\\"+hostname;
			String UNCPath=config.getServerPath();
			testServerPath=testFolder.replace(System.getProperty("user.dir")+"\\Results", UNCPath);
		}
		boolean setheader=true;
		if (!HTMLFile.exists()) {

			NewHtmlGenerator HtmlGenerator1=new NewHtmlGenerator(testFolder+"\\"+config.getToolName()+"\\"+config.getToolName()+".html",true);


			HtmlGenerator1.writeScripts();


			HtmlGenerator1.initializeMainTableHeader(Title);

			String startDate=Main.getStartDate(testFolder+"\\"+pcf.getToolName()+"\\Execution.ini");
			String endDate=Main.getDate();
			
			Main.set_Data(testFolder+"\\"+pcf.getToolName()+"\\Execution.ini", "ExecutionTime", "EndDate", endDate, "");
			long testSeconds=Main.getExecutionTime(startDate, endDate);
			
			String baseSeconds=Main.getBaseExecutionTime(baseFolder+"\\"+pcf.getToolName()+"\\Execution.ini");
			
			Main.set_Data(testFolder+"\\"+pcf.getToolName()+"\\Execution.ini", "ExecutionTime", "TotalTime", Long.toString(testSeconds), "");

			//HtmlGenerator1.SetNavigationText("View Lights Out Automation Execution Summary Results on Automation Dashboard @ http://dashboard-automation.netscout.com/dashboard.html [Requires IE 9 or above/chrome]");

			//HtmlGenerator1.SetNavigationText("<BR>");
			//HtmlGenerator1.SetNavigationText("<BR>");

			HtmlGenerator1.initializeBuildDetails(baseSeconds +" Seconds", testSeconds +" Seconds", config.getBase_aTA_Build(), config.getTest_aTA_Build(), config.getBase_IS_Build(), config.getTest_IS_Build());
			HtmlGenerator1.closeTable();
			/*	HtmlGenerator1.SetNavigationText("<B><U>aTA - Test Bed Details "+"</U></B>");
			HtmlGenerator1.initializeTable();

			String []header={"Description","BaseBuild","TestBuild"};
			HtmlGenerator1.setTableTitleValues(header);
			int counter=0;


			String []PMIP=new String[4];


			String []PMDetails=new String[3];
			PMDetails[0]="aTA Build";
			PMDetails[1]=config.getBase_aTA_Build();
			PMDetails[2]=config.getTest_aTA_Build();

			String []ISDetails=new String[3];
			ISDetails[0]="IS_Build";
			ISDetails[1]=config.getBase_IS_Build();
			ISDetails[2]=config.getTest_IS_Build();


			HtmlGenerator1.setTableValues(PMDetails);
			HtmlGenerator1.setTableValues(ISDetails);

			HtmlGenerator1.closeTable();*/

			//HtmlGenerator1.Close();


			String result=generateSubReport(baseFolder+"\\"+config.getToolName(), testFolder+"\\"+config.getToolName(),config);

			generateHtmlSubReport(baseFolder+"\\"+config.getToolName(), testFolder+"\\"+config.getToolName(),config);

			//HtmlGenerator1.initializeTable();
			
			
			
			HtmlGenerator1.initializeMainTable("1", pcf.getToolName()+" Validation");
			String header1[]={"Sl No","Testcase Description","Host Investigation","Risk Visualization","Network Investigation"};

			HtmlGenerator1.setTableTitleValues(header1,65);

			String row[]=new String[header1.length];

			row[0]=Integer.toString(testCases.length);
			row[1]=testCaseName;
			row[2]="N/A";
			row[3]="N/A";
			row[4]="N/A";
			
			INIFile objINI= new INIFile(System.getProperty("user.dir")+"\\ConfigFiles\\"+pcf.getToolName()+"\\"+pcf.getToolName()+".ini");
			
			String HostInvestigation=objINI.getStringProperty("Modules_To_Run", "HostInvetsigation");
			
			if(HostInvestigation.compareToIgnoreCase("true")==0)
			{
				List<String> hostInvestigation = fileToLines(testFolder + "\\" + config.getToolName() + "\\" + aTAModule2 + "\\Report.txt");

				for (String str : hostInvestigation) {

					if (str.contains("Total")) {
						if (str.contains("FAIL=0")) {
							row[2] = "<a style=\"color:green\" href=" + testServerPath + "\\" + config.getToolName()+ "\\" + aTAModule2 + "\\Report.html>" + str.replace("Total", "") + "</a>";
						} else {
							row[2] = "<a style=\"color:red\" href=" + testServerPath + "\\" + config.getToolName()+ "\\" + aTAModule2 + "\\Report.html>" + str.replace("Total", "") + "</a>";
						}
					}
				}
			}
			
			String RiskVisualization=objINI.getStringProperty("Modules_To_Run", "RiskVisualization");
			
			if(RiskVisualization.compareToIgnoreCase("true")==0)
			{

				List<String> riskVisualization = fileToLines(testFolder + "\\" + config.getToolName() + "\\" + aTAModule1 + "\\Report.txt");

				for (String str : riskVisualization) {
					if (str.contains("Total")) {
						if (str.contains("FAIL=0")) {
							row[3] = "<a style=\"color:green\" href=" + testServerPath + "\\" + config.getToolName()+ "\\" + aTAModule1 + "\\Report.html>" + str.replace("Total", "") + "</a>";
						} else {
							row[3] = "<a style=\"color:red\" href=" + testServerPath + "\\" + config.getToolName()+ "\\" + aTAModule1 + "\\Report.html>" + str.replace("Total", "") + "</a>";
						}
					}
				}
			}
			
			String NetworkInvestigation=objINI.getStringProperty("Modules_To_Run", "NetworkInvestigation");
			
			if(NetworkInvestigation.compareToIgnoreCase("true")==0)
			{
				List<String> networkInvestigation = fileToLines(testFolder + "\\" + config.getToolName() + "\\" + aTAModule3 + "\\Report.txt");

				for (String str : networkInvestigation) {

					if (str.contains("Total")) {
						if (str.contains("FAIL=0")) {
							row[4] = "<a style=\"color:green\" href=" + testServerPath + "\\" + config.getToolName()+ "\\" + aTAModule3 + "\\Report.html>" + str.replace("Total", "") + "</a>";
						} else {
							row[4] = "<a style=\"color:red\" href=" + testServerPath + "\\" + config.getToolName()+ "\\" + aTAModule3 + "\\Report.html>" + str.replace("Total", "") + "</a>";
						}
					}
				}
			}
			
			try {
				HtmlGenerator1.setMainTableValues(row);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			HtmlGenerator1.SetNavigationText("<BR>");
			HtmlGenerator1.SetNavigationText("<BR>");
			HtmlGenerator1.closeTable();
			HtmlGenerator1.initializeMainTableHeader("End of Report");
			HtmlGenerator1.Close();

			int pass=0;
			int fail=0;
			String passString=result.split(" ")[0].split("=")[1];
			String failString=result.split(" ")[1].split("=")[1];
			pass=Integer.parseInt(passString);
			fail=Integer.parseInt(failString);
			//sendEmail(config,pass,fail);

			if(config.getUpdateBaseline().compareToIgnoreCase("Yes")==0 && config.getMode().compareToIgnoreCase("createbaseline")==0)
			{
				config.setBaseLinePath(testFolder);
			}


			if(config.getMode().compareToIgnoreCase("Compare")==0 || config.getMode().compareToIgnoreCase("ReportOnly")==0)
			{
				String insertData[]=getwebArray(testFolder,config,pass,fail,0);
				updateNewPortalStatus=insertData[15];
				UpdateToWebDatabase(insertData);

				if(config.getUpdateBaseline().compareToIgnoreCase("Yes")==0)
					config.setBaseLinePath(testFolder);
			}
			sendEmail(config,pass,fail);
		}	


		new File(testFolder+"\\Report.html").delete();
		new File(testFolder+"\\"+pcf.getToolName()+"\\Report.html").delete();
		new File(testFolder+"\\Report.html").delete();
		
		return updateNewPortalStatus;

	}

	public static String generateSubReport(String baseFolder, String testFolder,ParseConfigFile config)
	{
		int passCounter=0,failCounter=0;

		if(new File(testFolder).exists())
		{
			writeToFile(testFolder+"\\Report.txt", "FileName,Result");

			String logFileName=System.getProperty("user.dir")+"\\InternalFiles\\LogProperties\\"+config.getToolName()+"log.log";
			String thresholdINI=System.getProperty("user.dir")+"\\ConfigFiles\\"+config.getToolName()+"\\"+config.getToolName()+"Threshold.ini";
			String ignoreConfigINI=System.getProperty("user.dir")+"\\ConfigFiles\\"+config.getToolName()+"\\"+config.getToolName()+"IgnoreConfig.ini";

			HTML_LinkedList_Diffview reportObject=new HTML_LinkedList_Diffview();

			AutomationUtils UtilObj=new AutomationUtils(logFileName,ignoreConfigINI,thresholdINI);

			String fileNames[]=new File(baseFolder).list();


			for(String fileName:fileNames)
			{
				if(fileName.contains(".html") || fileName.contains(".txt"))
					continue;

				if(fileName.contains(".csv"))
				{

					if(fileName.compareToIgnoreCase("SecurityType.csv")!=0)
					{
						AutomationProperty properties=new AutomationProperty(System.getProperty("user.dir")+"\\InternalFiles\\LogProperties\\Automation.Properties");

						String reporting= System.getProperty("ReportingMode");

						if(reporting.compareToIgnoreCase("Old")==0)
						{
							ComparisonResults compareResults=new ComparisonResults();
							try {
								if(new File(baseFolder+"\\"+fileName).exists() && new File(testFolder+"\\"+fileName).exists())
									compareResults=UtilObj.Compare(baseFolder+"\\"+fileName, testFolder+"\\"+fileName,true);
								else
								{
									compareResults.setResult(false);
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block

							}

							String outputFileName=testFolder+"\\"+fileName.replace(".csv",".html");

							//String baseServerPath=baseFile.toString().replace(System.getProperty("user.dir")+"\\Results", serverLocation);
							//String testServerPath=timeFolder.replace(System.getProperty("user.dir")+"\\Results", serverLocation);

							List<String> fileContent=fileToLines(testFolder+"\\"+fileName);
							for(String line:fileContent)
							{
								if(line.contains("NoData") || line.contains("No Data"))
								{
									compareResults.setResult(false);
									break;
								}
							}

							if(compareResults.isResult())
							{
								passCounter++;

								writeToFile(testFolder+"\\Report.txt", fileName+","+"<a href="+fileName.replace(".csv", ".html") +">"+ "<FONT COLOR =\"GREEN\">PASS</FONT>"+"</a>");
							}
							else
							{
								failCounter++;

								writeToFile(testFolder+"\\Report.txt", fileName+","+"<a href="+fileName.replace(".csv", ".html") +">"+ "<FONT COLOR =\"RED\">FAIL</FONT>"+"</a>");
							}

							if(new File(baseFolder+"\\"+fileName).exists() && new File(testFolder+"\\"+fileName).exists())
							{
								System.out.print("FileName:"+testFolder+"\\"+fileName + "Size:"+new File(testFolder+"\\"+fileName).length());

								try{
									reportObject.Report_LinkedList_Difference(compareResults.getBaseReturnList(),compareResults.getTestReturnList(),baseFolder+"\\"+fileName, testFolder+"\\"+fileName,",",outputFileName,"No");
								}
								catch(Exception e)
								{
									System.out.println("Problem with CSV");
								}

							}
						}
						else
							if(reporting.compareToIgnoreCase("New")==0)
							{
								HTML_DiffView hd = new HTML_DiffView(); 
								String base = baseFolder+"\\"+fileName;
								String test = testFolder+"\\"+fileName;

								String type = "csv"; 

								/*String ignore = System.getProperty("user.dir")+"\\ConfigFiles\\aTA-Backend\\aTA-BackendKeyConfig.ini";
								String threshold =System.getProperty("user.dir")+"\\ConfigFiles\\aTA-Backend\\aTA-BackendThreshold.ini";*/


								String ignore=keyConfigINI;
								String threshold=keythresholdINI;


								String result="";
								try{
								hd.html_dv(base,test,type,ignore,threshold,log4jproperties,logFileName); 
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
								common_api.MapObject mapResults=hd.getmapResults();


								if(new File(baseFolder+"\\"+fileName).exists() && new File(testFolder+"\\"+fileName).exists())
									result=hd.html_dv(base,test,type,ignore,threshold,log4jproperties,logFileName); 
								else
								{
									result="FAIL";
								}


								List<String> fileContent=fileToLines(testFolder+"\\"+fileName);
								for(String line:fileContent)
								{
									if(line.contains("NoData") || line.contains("No Data"))
									{
										result="FAIL";
										break;
									}
								}

								if(result.compareToIgnoreCase("PASS")==0)
								{
									passCounter++;

									writeToFile(testFolder+"\\Report.txt", fileName+","+"<a href="+fileName.replace(".csv", ".html") +">"+ "<FONT COLOR =\"GREEN\">PASS</FONT>"+"</a>");
								}
								else
								{
									failCounter++;

									writeToFile(testFolder+"\\Report.txt", fileName+","+"<a href="+fileName.replace(".csv", ".html") +">"+ "<FONT COLOR =\"RED\">FAIL</FONT>"+"</a>");
								}

								if(new File(baseFolder+"\\"+fileName).exists() && new File(testFolder+"\\"+fileName).exists())
								{
									System.out.print("FileName:"+testFolder+"\\"+fileName + "Size:"+new File(testFolder+"\\"+fileName).length());



								}

							}
					}
				}
				else
				{
					boolean subFolder=false;
					String parentFolderName= new File(new File(baseFolder+"\\"+fileName).getParent()).getName();

					if(parentFolderName.compareToIgnoreCase(aTAModule1Tab1)==0 || parentFolderName.compareToIgnoreCase(aTAModule1Tab2)==0 || parentFolderName.compareToIgnoreCase(aTAModule2Tab1)==0 || parentFolderName.compareToIgnoreCase(aTAModule2Tab2)==0 || parentFolderName.compareToIgnoreCase(aTAModule1Tab3)==0)
						subFolder=true;
					if(fileName.compareToIgnoreCase(toolName)==0 || fileName.compareToIgnoreCase(aTAModule1)==0 || fileName.compareToIgnoreCase(aTAModule2)==0  ||  fileName.compareToIgnoreCase(aTAModule3)==0 || fileName.compareToIgnoreCase(aTAModule1Tab1)==0 || fileName.compareToIgnoreCase(aTAModule1Tab2)==0 ||  fileName.compareToIgnoreCase(aTAModule2Tab1)==0 || fileName.compareToIgnoreCase(aTAModule2Tab2)==0 || fileName.compareToIgnoreCase(aTAModule1Tab3)==0 ||  fileName.contains("_") || subFolder==true && !fileName.contains("ASI") && !fileName.contains("ASR"))
					{

						String result=generateSubReport(baseFolder+"\\"+fileName, testFolder+"\\"+fileName,config);

						if(result.contains("FAIL=0"))
							writeToFile(testFolder+"\\Report.txt", fileName+","+"<a style=\"color:green\" href=./"+fileName+"\\Report.html>"+result+"</a>");
						else
							writeToFile(testFolder+"\\Report.txt", fileName+","+"<a style=\"color:red\" href=./"+fileName+"\\Report.html>"+result+"</a>");

						int pass=0;
						int fail=0;
						String passString=result.split(" ")[0].split("=")[1];
						String failString=result.split(" ")[1].split("=")[1];
						pass=Integer.parseInt(passString);
						fail=Integer.parseInt(failString);
						passCounter+=pass;


						failCounter+=fail;
					}
				}
			}

			if(failCounter>1)
				writeToFile(testFolder+"\\Report.txt", "<FONT COLOR=\"RED\"> Total PASS="+passCounter+" "+"FAIL="+failCounter+"</FONT>");
			else
				writeToFile(testFolder+"\\Report.txt", "<FONT COLOR=\"GREEN\"> Total PASS="+passCounter+" "+"FAIL="+failCounter+"</FONT>");

			return "PASS="+passCounter+" "+"FAIL="+failCounter;
		}
		else
		{
			writeToFile( new File(testFolder).getParent()+"\\Report.txt", new File(testFolder).getName() +","+"MISSING");
			failCounter++;
			return "PASS="+passCounter+" "+"FAIL="+failCounter + " MISSING";
		}
	}



	public static void generateHtmlSubReport(String baseFolder, String testFolder,ParseConfigFile config)
	{

		String fileNames[]=new File(testFolder).list();

		File SubHTMLFile = new File(testFolder+"\\Report.html");
		NewHtmlGenerator subHtmlGenerator=new NewHtmlGenerator(testFolder+"\\"+"Report.html",true);

		String Title = config.getMailTitle();
		Title = Title.replace("$sCIBuild", "");
		Title = Title.replace("$ISBuild", "");
		Title = Title.replace("$SetupType", "");

		//subHtmlGenerator.setTitle(Title);
		subHtmlGenerator.writeScripts();
		subHtmlGenerator.initializeMainTableHeader(Title);

		subHtmlGenerator.initializeBuildDetails("", "", config.getBase_aTA_Build(), config.getTest_aTA_Build(), config.getBase_IS_Build(), config.getTest_IS_Build());
		subHtmlGenerator.closeTable();

	//	subHtmlGenerator.SetNavigationText(new File(testFolder).getName());

		int passCounter=0,failCounter=0;
		//subHtmlGenerator.initializeTable();
		subHtmlGenerator.initializeMainTable("1", new File(testFolder).getName());
		for(String fileName:fileNames)
		{

			if(fileName.contains(".txt") && !fileName.contains(".html"))
			{
				List<String> reportList=fileToLines(testFolder+"\\Report.txt");
				for(int i=0;i<reportList.size();i++)
				{
					String row[]=new String[reportList.size()];
					row=reportList.get(i).split(",");

					if(i==0)
					{

						subHtmlGenerator.setTableTitleValues(row,30);
						//subHtmlGenerator.setTableTitleValues(row);
					}
					else
					{
						if(reportList.get(i).contains("TOTAL") || reportList.get(i).contains("Total") && !reportList.contains("Total_Traffic"))
						{
							subHtmlGenerator.setTableFooter(reportList.get(i), 2);
						}
						else
							if(!reportList.get(i).contains(" MISSING"))
								try {
									subHtmlGenerator.setMainTableValues(row);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					}

				}
				subHtmlGenerator.closeTable();
				
				subHtmlGenerator.SetNavigationText("<BR>");
				subHtmlGenerator.SetNavigationText("<BR>");
				
				subHtmlGenerator.initializeMainTableHeader("End of Report");
				
				subHtmlGenerator.Close();
			}
			else
			{
				if(new File(testFolder+"\\"+fileName).isDirectory())
				{

					boolean subFolder=false;
					String parentFolderName= new File(new File(baseFolder+"\\"+fileName).getParent()).getName();


					if(parentFolderName.compareToIgnoreCase(aTAModule1Tab1)==0 || parentFolderName.compareToIgnoreCase(aTAModule1Tab2)==0 || parentFolderName.compareToIgnoreCase(aTAModule2Tab1)==0 || parentFolderName.compareToIgnoreCase(aTAModule2Tab2)==0 || parentFolderName.compareToIgnoreCase(aTAModule1Tab3)==0)
						subFolder=true;

					if(fileName.compareToIgnoreCase(toolName)==0 || fileName.compareToIgnoreCase(aTAModule1)==0 || fileName.compareToIgnoreCase(aTAModule2)==0 || fileName.compareToIgnoreCase(aTAModule3)==0 || fileName.compareToIgnoreCase(aTAModule1Tab1)==0 || fileName.compareToIgnoreCase(aTAModule1Tab2)==0 || fileName.compareToIgnoreCase(aTAModule1Tab3)==0 || fileName.compareToIgnoreCase(aTAModule2Tab1)==0 || fileName.compareToIgnoreCase(aTAModule2Tab2)==0 || fileName.contains("_") || subFolder==true && !fileName.contains("ASI") && !fileName.contains("ASR"))
					{
						generateHtmlSubReport(baseFolder+"\\"+fileName, testFolder+"\\"+fileName, config);
					}
				}
			}
		}

	}

	public static void writeToFile(String fileName,String strToWrite)
	{
		String outputFileName=fileName;

		FileWriter outputFileWriter = null;
		try {
			outputFileWriter = new FileWriter(outputFileName,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		PrintWriter    outputStream  = new PrintWriter(outputFileWriter,true);
		//xmlString1="xml="+xmlString1;
		outputStream.println(strToWrite);

		outputStream.close();
	}

	public static List<String> fileToLines(String filename) {
		List<String> lines = new LinkedList<String>();
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static void sendEmail(ParseConfigFile config,int pass,int fail)
	{
		Process p=null;

		String MailSubject = config.getMailSubject();
		MailSubject = MailSubject.replace("$sCIBuild", config.getTest_aTA_Build());
		MailSubject = MailSubject.replace("$ISBuild", config.getTest_IS_Build());
		MailSubject = MailSubject.replace("$SetupType", config.getSetupType());
		MailSubject = MailSubject.replace("$PassVal", Integer.toString(pass));
		MailSubject = MailSubject.replace("$FailVal", Integer.toString(fail));

		try {
			String emailCommand=System.getProperty("user.dir")+"\\InternalFiles\\email\\blat.exe "+config.getTestPath()+"\\"+config.getToolName()+"\\"+config.getToolName()+".html"+" -to "+config.getMailTo()+" -server "+config.getMailServer().trim()+" -f automation@netscout.com -port 25 -u automation -pw Welcome2NS -subject"+" "+"\""+MailSubject+"\"";
			System.out.println(emailCommand);
			//logger.info(emailCommand);
			p = Runtime.getRuntime().exec("cmd /c start /wait " +emailCommand);
			//logger.info("cmd /c start /wait "+emailCommand);
			//logger.info("Mail Sent to Mail List");
		} catch (IOException e) {
			//logger.error("Error while sending report email "+e);
		}
	}

	public String[] getwebArray(String testFolder,ParseConfigFile config,int totalDBPass,int totalDBFail,int totalDBDiff){

		String version="";
		String[] InsertData = new String[16];
		try{


			InetAddress addr=null;
			String hostname = null;
			try {
				addr = InetAddress.getLocalHost();
				hostname=addr.getHostAddress();

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}


			SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
			Date now = new Date();			
			String currentDate = dateFormat1.format(now);
			//
			InsertData[0] = addr.getHostAddress();
			InsertData[1] = config.getSetupType();
			InsertData[2]=currentDate;
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("h:mm a");;
			InsertData[3] = dateFormat2.format(now);
			InsertData[4] = config.getToolName();
			InsertData[5] =	config.getTest_PM_Build().replaceAll(".\\d+$", "");
			InsertData[6] = config.getTest_PM_Build();
			InsertData[7] = config.getTest_IS_Build();
			InsertData[8] = "NA";
			InsertData[9] = "NA";
			InsertData[10] = Integer.toString(totalDBPass);
			InsertData[11] = Integer.toString(totalDBFail);
			InsertData[12] = Integer.toString(totalDBDiff);
			if(totalDBFail==0)
				InsertData[13]="PASS";
			else
				InsertData[13]="FAIL";

			/*if(config.getMode().compareToIgnoreCase("Compare")==0)
				InsertData[14] = "http://"+hostname+":8080"+"/Results/"+new File(testFolder).getName()+"/"+config.getToolName()+"/"+config.getToolName()+".html";
			 */

			//	if(config.getMode().compareToIgnoreCase("Compare")==0)
			InsertData[14] = config.getServerPath()+"/"+new File(testFolder).getName()+"/"+config.getToolName()+"/"+config.getToolName()+".html";

			String projectId=nG1CyberInvestigator.Main.getProjectID(pcf.getToolName());
			if(config.getMode().compareToIgnoreCase("Compare")==0 || config.getMode().compareToIgnoreCase("ReportOnly")==0)
			{
				TrackerFile tr = new TrackerFile();
				tr.InitiatenG1TrackerFile(inputSetupINI);
				//String projectId=config.getaTABoxType()+" Backend";
				//String trackerProjectId=config.getaTABoxType()+"-Backend";

				String trackerProjectId="nG1Suite";
				tr.writeTonG1TrackerFile(trackerProjectId+"%"+projectId +"|"+Integer.toString(totalDBPass)+"/"+Integer.toString(totalDBPass+totalDBFail)+"|"+InsertData[13]+"|"+InsertData[14],inputSetupINI);

			}

			/*
			 * New Portal Update Start
			 */

			NewPortal portalObject=new NewPortal(inputSetupINI);
			//String projectId=config.getaTABoxType()+" Backend";
			int totalTestCases=totalDBPass+totalDBFail;//Total Test cases
			int totalPassCases=totalDBPass;//Total Test Case Passed
			int failCases=totalDBFail;//Total Test Case Failed
			int notAttemptedCases=0;//Not Attempted Test Case

			String overAllResult;//OverAll Result - PASS/FAIL
			if(totalDBFail!=0)
				overAllResult="PASS";
			else
				overAllResult="FAIL";
			String suiteStatus="Completed";//Suite Status
			String reportLocation=InsertData[14];//Report Location
			String ListTcsAndStatus="";//Currently Blank
			String executionStatus="-";
			String logLocation="";

			if(config.getMode().compareToIgnoreCase("Compare")==0 || config.getMode().compareToIgnoreCase("ReportOnly")==0)
			{
				boolean updateStatus=portalObject.updateNewPortal(projectId,totalTestCases,totalPassCases,failCases,notAttemptedCases,overAllResult,suiteStatus,reportLocation,ListTcsAndStatus,executionStatus,logLocation);

				if(updateStatus==true)
				{
					InsertData[15]="true";
					System.out.println("[Final Update] Update to New Portal Success");
				}
				else
				{
					InsertData[15]="false";
					System.out.println("[Final Update] Update to New Portal Failed");
				}
			}

			/*
			 * New Portal Updation End
			 */


			return InsertData;
		}
		catch(Exception e)
		{
			//logger.error("Failed to insert the execution results to database");
		}
		return InsertData;





	}

	public void UpdateToWebDatabase(String[] insertData) 
	{
		try
		{
			/*
			 * 0-rpt_generation_date character varying,
                1-rpt_generation_time character varying,
                      2-product character varying,
                      3-productversion character varying,
                      4-pmbuild character varying,
                      5-isbuild character varying,
                      6-nsi_build character varying,
                      7-decodepackbuild character varying,
                      8-testcasepassed integer,
                      9-testcasefailed integer,
                      10-testcasediff integer,
                      11-overallresult character varying,
                      12-reportlocation text
			 */

			/*String driver = "org.postgresql.Driver";
			//String url = "jdbc:postgresql://172.22.32.60:5432/AutoWebServer";
			String url = "jdbc:postgresql://172.22.32.64:5432/AutoWebServer";
			String username = "prod_db_admin";
			String password = "netscout1";*/

			String propertiesFilePath=System.getProperty("user.dir")+"\\InternalFiles\\LogProperties\\Automation.properties";
			AutomationProperty properties=new AutomationProperty(propertiesFilePath);

			String driver = properties.getPropertyValue("driver");
			String url = properties.getPropertyValue("url");
			String username = properties.getPropertyValue("username");
			String password = properties.getPropertyValue("password");
			String tableName= properties.getPropertyValue("dbtablename");

			String myDataField = null;
			Connection myConnection = null;
			ResultSet myResultSet = null;
			PreparedStatement myPreparedStatement =null;


			String myQuery = "INSERT INTO "+tableName+" VALUES ('"+insertData[0]+"','"+insertData[1]+"','"+insertData[2]+"','"+insertData[3]+"','"+insertData[4]+"','"+insertData[5]+"','"+insertData[6]+"','"+insertData[7]+"','"+insertData[8]+"','"+insertData[9]+"','"+Integer.parseInt(insertData[10])+"','"+Integer.parseInt(insertData[11])+"','"+Integer.parseInt(insertData[12])+"','"+insertData[13]+"','"+insertData[14]+"');";
			//String myQuery = "INSERT INTO automationexecutiondetails VALUES ('"+insertData[0]+"','"+insertData[1]+"','"+insertData[2]+"','"+insertData[3]+"','"+insertData[4]+"','"+insertData[5]+"','"+insertData[6]+"','"+insertData[7]+"',"+Integer.parseInt(insertData[8])+","+Integer.parseInt(insertData[9])+","+Integer.parseInt(insertData[10])+",'"+insertData[11]+"','"+insertData[12]+"')";

			Class.forName(driver).newInstance();
			myConnection = DriverManager.getConnection(url,username,password);
			myPreparedStatement = myConnection.prepareStatement(myQuery);
			myPreparedStatement.executeQuery();


			System.out.println("Data Successfully Inserted");

		} 
		catch (Exception e)
		{

			System.out.println("Data Inserted To Database with exception");
		}

	}

}
