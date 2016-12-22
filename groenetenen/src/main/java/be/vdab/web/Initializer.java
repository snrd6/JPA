package be.vdab.web;


import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.vdab.datasource.DataSourceConfig;
import be.vdab.services.ServicesConfig;
import repositories.RepositoriesConfig;
								//Deze registreert de DispatcherServlet als servlet bij de webserver
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
			//Deze method associeert urlpatronen met de dispatcherServlet
		 @Override
		 protected String [] getServletMappings(){
			 return new String[]{"/"};
		 }
		 
		 	//
		 @Override
		 protected Class<?>[] getRootConfigClasses() { 
		return new Class<?>[]{RepositoriesConfig.class,DataSourceConfig.class,ServicesConfig.class};
		 }
		 
		 
		 
		 
		 	//je geeft hier de classes aan die de java config code bevatten van de controllerbeans
		 @Override
		 protected Class<?>[] getServletConfigClasses() { 
			 //controllersconfig bevat  de java config code van je controllerbeans
		 return new Class<?>[] { ControllersConfig.class }; 
		 }
		 
		 
		 
		 
		 @Override
		 	//Deze method geeft een array van servletfilters terug
		 protected Filter[] getServletFilters() { 
			 //je verstuurt tekst die de gebruiker tikt in utf8 formaat
		 return new Filter[] { new CharacterEncodingFilter("UTF-8"),new OpenEntityManagerInViewFilter() }; 
		 }
	 
}
