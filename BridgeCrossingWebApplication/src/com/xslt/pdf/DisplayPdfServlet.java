package com.xslt.pdf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.bridge.jaxb.Activitylist;


public class DisplayPdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			response.setContentType("application/pdf");
			XmlSource xmlSource = new XmlSource();
			Activitylist activityList = xmlSource.getActivityList();
			// Setup directories
			File xsltfile = new File(getServletContext().getRealPath(
					"/ActivityListFO.xsl"));
			File outDir = new File("WebContent");
			outDir.mkdirs();
			// configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();

			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			// configure foUserAgent as desired

			// Setup output
			OutputStream out = response.getOutputStream();
			try {
				// Construct fop with desired output format
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF,
						foUserAgent, out);

				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory
						.newTransformer(new StreamSource(xsltfile));

				// Set the value of a <param> in the stylesheet
				transformer.setParameter("versionParam", "2.0");

				// Setup input for XSLT transformation
				JAXBContext jc = JAXBContext.newInstance(Activitylist.class);
				JAXBSource source = new JAXBSource(jc, activityList);
				// Resulting SAX events (the generated FO) must be piped through
				// to FOP
				Result res = new SAXResult(fop.getDefaultHandler());
				// Start XSLT transformation and FOP processing
				transformer.transform(source, res);

			} finally {
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(-1);
		}
	}
}
