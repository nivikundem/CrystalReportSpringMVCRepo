package com.bridge.merge;

import java.io.*;
import java.util.logging.Logger;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class MergePdfs {
	private static final Logger LOG = Logger.getLogger(MergePdfs.class.getName());
	private static final String mergedFileNameStartString = "C:\\CR_to_PDF\\PDFs\\";

	/**
	 * MergePdf
	 * @param partAFileName
	 * @param partBFileName
	 * @param mergedFileName
	 */
	public void  MergePdf(String partAFileName, String partBFileName,
			String mergedFileName) {		
		try {
			String[] rptFiles = { partAFileName, partBFileName };
			Document pdfMergePartAB = new Document();
			// prepare a new document for the merged file
			FileOutputStream fos = new FileOutputStream(mergedFileName);
			PdfCopy pdfCopy = new PdfCopy(pdfMergePartAB, fos);
			pdfMergePartAB.open();
			PdfReader pdfReader;
			int numberOfPages;
			// for all pages of part A and part B documents, append them to the
			// merged file
		
			for (int i = 0; i < rptFiles.length; i++) {				
				pdfReader = new PdfReader(rptFiles[i]);				
				numberOfPages = pdfReader.getNumberOfPages();				
				for (int page = 0; page < numberOfPages;) {
					pdfCopy.addPage(pdfCopy.getImportedPage(pdfReader, ++page));
				}
				pdfCopy.freeReader(pdfReader);
				pdfReader.close();
			}
			// close the merged file
			pdfMergePartAB.close();
		} catch (Exception e) {
			// merge unsuccessful
			 System.out.println(e);
		}
	}

	
	
	
	public static String getMergedFileName() {		
		System.out.println("getMergedFileName  - start");
		String pdfFileName ;
		pdfFileName = mergedFileNameStartString+"VEHICLE_REPORT";
		 LOG.info("mergedPdfFileName " + pdfFileName);		 
		 System.out.println("getMergedFileName  - end");
		return pdfFileName;

	}

}
