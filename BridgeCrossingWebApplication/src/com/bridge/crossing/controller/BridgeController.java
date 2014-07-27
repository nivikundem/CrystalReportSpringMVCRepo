package com.bridge.crossing.controller;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bridge.crossing.model.CrossingActivityObjModel;
import com.bridge.pdfgenerator.PdfProcessor;

/**
 * @param args
 * Nivi Kundem
 * http://localhost:8080/BridgeCrossingWebApplication/ActivityView - page1 url * * 
 * 
 */

@Controller
public class BridgeController {

	private static final Logger LOG = Logger.getLogger(BridgeController.class
			.getName());

	@RequestMapping(value = "/ActivityView", method = RequestMethod.GET)
	public String getCrossingDetailsList(
			@RequestParam(required = false) String quickSearchTextbox,
			@RequestParam(required = false) String sortField,		
			ModelMap model) {

		String quickSearchTextboxVar = "ALL";
		if (quickSearchTextbox != null) {
			quickSearchTextboxVar = quickSearchTextbox;
		}
		String sortString = "crossing_datetime";
		if (sortField != null) {
			sortString = sortField;
		}	
		model.addAttribute("crossingDetailsObjList", CrossingActivityObjModel
				.getActivityDetailsResultList(quickSearchTextboxVar, sortString
						));
		return "ActivityView";
	}
	
	@RequestMapping(value = "/PdfGenView", method = RequestMethod.POST)
	public String getPdfGenView(
			@RequestParam(required = false) String quickSearchTextbox,
			@RequestParam(required = false) String sortField,
			@RequestParam("pdf") int[] selectedPdfs,
			Model model) {
		
		String quickSearchTextboxVar = "ALL";
		if (quickSearchTextbox != null) {
			quickSearchTextboxVar = quickSearchTextbox;
		}
		System.out.println("sortField    in controller   " + sortField);
		LOG.info("sortField  "+sortField);
		String sortString = "crossing_datetime";
		if (sortField != null) {
			sortString = sortField;
		}
		PdfProcessor pdfProcessor = new PdfProcessor();
		if (selectedPdfs != null) {
				LOG.info("Pdfs for invoices are generating ............. ");				
				for (int selectedPdf : selectedPdfs) {
					LOG.info("pdf :: " + selectedPdf);
					pdfProcessor.processSelectedInvoices(selectedPdf);
					LOG.info("Pdf generated for  ::::  " + selectedPdf);
					model.addAttribute("crossingDetailsObjList", CrossingActivityObjModel
							.getActivityDetailsResultList(quickSearchTextboxVar, sortString
									));				
					model.addAttribute("selectedPdfs", selectedPdfs);				
				return "ActivityView";
			} 
		}
		return "ActivityView";
	}
	
	@RequestMapping(value = "/ContactView", method = RequestMethod.GET)
	public String getContactView(Model model) {		
		return "ContactView";
	}
	@RequestMapping(value = "/AboutView", method = RequestMethod.GET)
	public String getAboutView(Model model) {		
		return "AboutView";
	}
}