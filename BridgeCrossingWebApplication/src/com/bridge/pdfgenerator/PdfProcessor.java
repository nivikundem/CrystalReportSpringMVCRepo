package com.bridge.pdfgenerator;


import com.bridge.merge.MergePdfs;

public class PdfProcessor {

	/**
	 * processSelectedInvoices
	 * @param selectedPdf
	 */
	public void processSelectedInvoices(int selectedPdf) {	
		GeneratePdfs generatePdfs = new GeneratePdfs();		
		generatePdfs.sendPdfForVrnReport("A", selectedPdf);
	    generatePdfs.sendPdfForVrnReport("B", selectedPdf);
	
		// get pdf file names of parts A and B
		String pdfPartA = generatePdfs.getFileName("A") + "_" + selectedPdf + ".pdf";
		String pdfPartB = generatePdfs.getFileName("B") + "_" + selectedPdf + ".pdf";	
		
		// if parts A and B exist then attempt to create a merged pdf
		if (pdfPartA != null & pdfPartB != null) {
			MergePdfs mergePdfs = new MergePdfs();
			mergePdfs.MergePdf(
					pdfPartA,
					pdfPartB,
				
					MergePdfs.getMergedFileName()
							+ "_"
							+ selectedPdf
							+ ".pdf");
		}	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		// delete files;
		try {
			generatePdfs.deleteFiles(pdfPartA);
			generatePdfs.deleteFiles(pdfPartB);			

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
}
