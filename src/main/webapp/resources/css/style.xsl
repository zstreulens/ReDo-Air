<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">



	<xsl:template match="flights/flight">
			<xsl:element name="flight">
				<xsl:for-each select="*">
					<xsl:attribute name="{name()}">
                    <xsl:value-of select="text()" />
                </xsl:attribute>
				</xsl:for-each>
			</xsl:element>
		</xsl:template>


</xsl:stylesheet>