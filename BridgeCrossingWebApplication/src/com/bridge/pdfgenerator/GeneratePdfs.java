package com.bridge.pdfgenerator;

import java.util.List;
import java.util.logging.Logger;

//imports for crystal report
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.crossing.pojos.CrossingActivityDetailsPOJO;
import com.crossing.utilities.DBDetails;
import com.crystaldecisions.sdk.occa.report.application.PrintOutputController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;



public class GeneratePdfs {
	private static final Logger LOG = Logger.getLogger(GeneratePdfs.class
			.getName());

	private static String pdfFileNameStartString = "C:\\CR_to_PDF\\PDFs\\";
	private static String crystalFileName = "C:\\CR_to_PDF\\CRs\\";

	/**
	 * sendPdfForVrnReport
	 * @param partType
	 * @param docNumber
	 */
	public void sendPdfForVrnReport(String partType, int docNumber) {
		String reportPath = crystalFileName + "VEHICLE_ACTIVITY_PART_A_REPORT.rpt";
		String procedureName = "{ call VEHICLE_ACTIVITY_PROCEDURE(?) }";
		if (partType.equals("A")) {
			reportPath = crystalFileName + "VEHICLE_ACTIVITY_PART_A_REPORT.rpt";			
		} else  {
			reportPath = crystalFileName + "VEHICLE_ACTIVITY_PART_B_REPORT.rpt";			
		} 
		ReportClientDocument rcp = new ReportClientDocument();
		try {
			rcp.open(reportPath, 0);
			DBDetails dbDetails = new DBDetails();
			Connection con = dbDetails.getDBConnection();			
			ResultSet rs = getResultSet(con, docNumber, procedureName);
			rcp.getDatabaseController().setDataSource(rs, "", "");
			System.out.println(rcp.getDatabase().getTables().getTable(0)
					.getAlias().toString());
			// send pdf file to local system			
			sendPdfFile(rcp, docNumber,  partType);
			rcp.close();
		} catch (ReportSDKException | ClassNotFoundException | SQLException
				| IOException exc) {
			exc.printStackTrace();
		}

	}

	/**
	 * getResultSet
	 * @param con
	 * @param uid
	 * @param procedureName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ResultSet getResultSet(Connection con, int uid,
			String procedureName)
			throws SQLException, ClassNotFoundException {

		System.out.println("procedureName " + procedureName);
		PreparedStatement stmt;
		stmt = con.prepareStatement(procedureName);
		stmt.setEscapeProcessing(true);
		stmt.setInt(1, uid);
		ResultSet rs = stmt.executeQuery();
		System.out.println("procedureName  " + procedureName);
		System.out.println("invoiceNumber  " + uid);
		return rs;
	}
	
	/**
	 * getResultSet
	 * @param con
	 * @param uid
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ResultSet getResultSet(Connection con, int uid) throws SQLException,
			ClassNotFoundException {
		String queryString = "SELECT uid, gate_number, vehicle_type, direction, vrn, crossing_datetime FROM qe2_bridge.bridge_crossing_performance BCP WHERE BCP.UID ="
				+ uid;
		Statement stmt;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(queryString);
		System.out.println("invoiceNumber  " + uid);
		stmt.close();
		con.close();
		return rs;
	}

	/**
	 * sendPdfFile
	 * @param rcp
	 * @param docNumber
	 * @param partType
	 * @throws IOException
	 */
	public void sendPdfFile(ReportClientDocument rcp,
			int docNumber,  String partType) throws IOException {
		System.out.println("sendPdfFile   - START");
		LOG.info("sendPdfAndExcelFile   - START");
		java.io.ByteArrayInputStream byteArrayInputStream = null;
		;
		try {
			PrintOutputController printOutputController = rcp
					.getPrintOutputController();
			LOG.info("printOutputController :::  "
					+ printOutputController.toString());
			byteArrayInputStream = (java.io.ByteArrayInputStream) printOutputController
					.export(com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat.PDF);
			LOG.info("byteArrayInputStream :::  "
					+ byteArrayInputStream.toString());
		} catch (ReportSDKException e) {
			System.out
					.println("error message ::: byteArrayInputStream failed ");
			e.printStackTrace();
		}

		String fileName = getFileName(partType)
				+ "_" + docNumber + ".pdf";		
			try {
				byteArrayInputStream = (ByteArrayInputStream) rcp
						.getPrintOutputController().export(
								ReportExportFormat.PDF);
			} catch (ReportSDKException e) {
				e.printStackTrace();
			}
			fileName = getFileName(partType)
					+ "_" + docNumber + ".pdf";
		

		File file = new File(fileName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
				byteArrayInputStream.available());
		byte[] byteArray = new byte[byteArrayInputStream.available()];
		int x = byteArrayInputStream.read(byteArray, 0,
				byteArrayInputStream.available());
		System.out.println("    x    :::  bytearrayinput stream  " + x);
		byteArrayOutputStream.write(byteArray, 0, x);
		System.out.println("here " + byteArray.length);
		byteArrayOutputStream.writeTo(fileOutputStream);
		// Close streams.
		byteArrayInputStream.close();
		byteArrayOutputStream.close();
		fileOutputStream.close();
		System.out.println("sendPdfFile   - END");
	}

	// PDFs\\
	/**
	 * getFileName
	 * @param partType
	 * @return
	 */
	public String getFileName(String partType) {	
		String pdfFileName;
		pdfFileName = pdfFileNameStartString + "VEHICLE_REPORT_A_REPORT";
		System.out.println("partType " + partType);	
			if (partType.equals("A")) {				
					pdfFileName = pdfFileNameStartString
							+ "VEHICLE_REPORT_A_REPORT";
			} else if (partType.equals("B")) {				
					pdfFileName = pdfFileNameStartString
							+ "VEHICLE_REPORT_B_REPORT";
			}		
		return pdfFileName;
	}

	
	// / delete files after merging
	/**
	 * deleteFiles
	 * @param pdfFileName
	 */
	public void deleteFiles(String pdfFileName) {
		try {
			File file = new File(pdfFileName);
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
