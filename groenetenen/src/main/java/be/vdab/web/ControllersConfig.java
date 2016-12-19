package be.vdab.web;



import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
class ControllersConfig extends WebMvcConfigurerAdapter {
	
	
	
	//Beans
	@Bean
	InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/JSP/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	MessageSource messageSource(){
		//De class ReloadableResourceBundleMessageSource leest resource bundles
		ReloadableResourceBundleMessageSource source =new ReloadableResourceBundleMessageSource();
		//Je vult basename met de locatie en de base name van de resource bundles.
		source.setBasename("classpath:teksten");
		source.setFallbackToSystemLocale(false);
		return source;
	}
	
	@Bean
	LocaleResolver localeResolver(){
		CookieLocaleResolver resolver=new CookieLocaleResolver();
		//levensduur in seconden (604800)
		resolver.setCookieMaxAge(604800);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler("/images/**").addResourceLocations("/images/"); 
		registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
		}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/info").setViewName("info");
	}
	

}
