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

public class DisplayHtmlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		XmlSource xmlSource = new XmlSource();
		com.bridge.jaxb.Activitylist activityList = null;
		try {
			activityList = xmlSource.getActivityList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Create Transformer
		TransformerFactory tf = TransformerFactory.newInstance();
		File xsltfile = new File(getServletContext().getRealPath(
				"/ActivityList.xsl"));
		StreamSource xslt = new StreamSource(xsltfile);
		try {
			Transformer transformer = tf.newTransformer(xslt);
			JAXBContext jc = JAXBContext.newInstance(com.bridge.jaxb.Activitylist.class);
			JAXBSource source = new JAXBSource(jc, activityList);
			StringWriter outWriter = new StringWriter();
			StreamResult result = new StreamResult(outWriter);
			transformer.transform(source, result);
			StringBuffer sb = outWriter.getBuffer();
			String finalstring = sb.toString();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(finalstring);
			out.close();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}