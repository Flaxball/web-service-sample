package io.acari;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

import static io.acari.ComputerEndpoint.*;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    public static final String COMPUTER_SERVICE = "/computer-service";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, COMPUTER_SERVICE + "/*");
    }

    @Bean(name = "computers")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema computersSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ComputersPort");
        wsdl11Definition.setLocationUri(COMPUTER_SERVICE);
        wsdl11Definition.setTargetNamespace(SERVICE_ENDPOINT);
        wsdl11Definition.setSchema(computersSchema);
        Properties soapActions = new Properties();
        soapActions.setProperty("computersByModel", SERVICE_ENDPOINT + "/computersByModel");
        wsdl11Definition.setSoapActions(soapActions);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema computersSchema(){
        return new SimpleXsdSchema(new ClassPathResource("computers.xsd"));
    }
}
