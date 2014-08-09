<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output encoding="iso-8859-1" />
	
	<xsl:attribute-set name="default-table-attribute-set">
		<xsl:attribute name="table-omit-header-at-break">false</xsl:attribute>		
		<xsl:attribute name="start-indent">0.19cm</xsl:attribute>
		<xsl:attribute name="space-after">1pt</xsl:attribute>
	</xsl:attribute-set>	
	<xsl:attribute-set name="border-set">
		<xsl:attribute name="border-color">black</xsl:attribute>	
		<xsl:attribute name="border-width">1pt</xsl:attribute>
		 <xsl:attribute name="border-style">solid</xsl:attribute>       
	</xsl:attribute-set>
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="14pt" font-family="verdana" color="black" margin-top="10mm"
					space-before="5mm" space-after="5mm" text-align="center">
					  Activity List
					</fo:block>
					<fo:block>
						<fo:table xsl:use-attribute-sets="default-table-attribute-set">
							<fo:table-column column-width="15mm" />
							<fo:table-column column-width="30mm" />
							<fo:table-column column-width="50mm" />
							<fo:table-column column-width="30mm" />
							<fo:table-column column-width="30mm" />
							<fo:table-column column-width="50mm" />
							<fo:table-header>
								<fo:table-row>
									<fo:table-cell xsl:use-attribute-sets="border-set">
										<fo:block font-weight="bold">UID</fo:block>
									</fo:table-cell>
									<fo:table-cell  xsl:use-attribute-sets="border-set">
										<fo:block font-weight="bold">VRN</fo:block>
									</fo:table-cell>
									<fo:table-cell  xsl:use-attribute-sets="border-set">
										<fo:block font-weight="bold">Vehicle Type</fo:block>
									</fo:table-cell>
									<fo:table-cell  xsl:use-attribute-sets="border-set">
										<fo:block font-weight="bold">Gate Number</fo:block>
									</fo:table-cell>
									<fo:table-cell  xsl:use-attribute-sets="border-set">
										<fo:block font-weight="bold">Direction</fo:block>
									</fo:table-cell>
									<fo:table-cell  xsl:use-attribute-sets="border-set">
										<fo:block font-weight="bold">Date</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-header>

							<fo:table-body>
								<xsl:for-each select="//activitylist/activity">
									<fo:table-row>									
									   	<fo:table-cell  xsl:use-attribute-sets="border-set">
											<fo:block>
												<xsl:value-of select="uid" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell  xsl:use-attribute-sets="border-set">
											<fo:block>
												<xsl:value-of select="vrn" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell  xsl:use-attribute-sets="border-set">
											<fo:block>
												<xsl:value-of select="vehicleType" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell  xsl:use-attribute-sets="border-set">
											<fo:block>
												<xsl:value-of select="gateNumber" />
											</fo:block>
										</fo:table-cell>									
										<fo:table-cell  xsl:use-attribute-sets="border-set">
											<fo:block>
												<xsl:value-of select="direction" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell  xsl:use-attribute-sets="border-set">
											<fo:block>
												<xsl:value-of select="crossingDateAndTime" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>