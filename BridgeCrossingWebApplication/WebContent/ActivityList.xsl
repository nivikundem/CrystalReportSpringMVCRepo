<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Activity List</h2>
				<table border="1">
					<tr>						
						<th>UId</th>
						<th>Vrn</th>
						<th>Vehicle Type</th>
						<th>Gate Number</th>
						<th>Direction</th>
						<th>Date</th>
					</tr>
					<xsl:for-each select="//activitylist/activity">
						<tr>
						    <td><xsl:value-of select="uid" /></td>
						    <td><xsl:value-of select="vrn" /></td>
						    <td><xsl:value-of select="vehicleType" /></td>
							<td><xsl:value-of select="gateNumber" /></td>							
							<td><xsl:value-of select="direction" /></td>							
							<td><xsl:value-of select="crossingDateAndTime" /></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>