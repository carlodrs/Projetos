<!DOCTYPE  html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- HTM VERSION 5 DIV --%>
<html>
		
		<!-- TEMPLATE HEADER -->
	    	<tiles:insertAttribute name="header" />
	    <!-- ENDS TEMPLATE HEADER -->
    
   		<!-- TEMPLATE MAIN -->
    		<tiles:insertAttribute name="content" />
    	<!-- ENDS TEMPLATE MAIN -->
    
    	<!-- TEMPLATE MAIN -->
    		<tiles:insertAttribute name="footer" />
    	<!-- END TEMPLATE FOOTER -->
</html>
<%-- ENDS HTM VERSION 5 DIV --%>
