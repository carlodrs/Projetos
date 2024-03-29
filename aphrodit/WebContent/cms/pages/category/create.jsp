<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<div id="createUserContent" class="cmscontent">
	
		<h3>Tela cadastro de Categorias</h3>
			
		<span><s:actionerror/></span>
		
		<s:form name="CategoryCreateCMSAction" action="CategoryCreateCMSAction" method="post" validate="true">  
	
			<s:checkbox name="isParent" fieldValue="true" key="category.is.parent"/>
			<s:textfield name="category.name" key="category.name" required="true"/>
			<s:textfield name="category.description" key="category.description" required="true"/>
			<s:textfield name="category.url" key="category.url" required="true"/>
			<s:textfield name="category.showSequence" key="category.showSequence" required="true"/>
			<s:select  key="parent.categories" required="true" headerKey="-1" headerValue="Selecione a categoria"
				list="categories" listKey="id" listValue="name" name="category.parent.id" />
		
			<s:submit key="buttonregistry" align="center"/>
		</s:form>
		
		<s:form name="ListCategoryRegisteredAction" action="ListCategoryRegisteredAction" method="post"> 
			<s:submit key="buttonlistall" align="center"/>	
		</s:form>
	
</div>