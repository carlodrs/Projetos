<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!-- BODY -->
<body class="home">

	<!-- WRAPPER -->
		<div id="wrapper">

			<!-- HEADER -->
	  			<tiles:insertAttribute name="main.header" />
  			<!-- ENDS HEADER -->
			
			<!-- MAIN -->
			<div id="main">
				
				<!-- CONTENT -->
					<tiles:insertAttribute name="main.content" />
				<!-- ENDS  CONTENT -->

			</div>
			<!-- ENDS MAIN -->
			
			<!-- FOOTER -->
				<tiles:insertAttribute name="main.footer"/>
			<!-- ENDS FOOTER -->
		
		</div>
	<!-- ENDS WRAPPER -->

</body>
<!-- ENDS BODY -->