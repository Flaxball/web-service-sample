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

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet,"/computer-service");
    }

    @Bean(name = "computers")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema computersSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ComputersPort");
        wsdl11Definition.setLocationUri("/computers-service");
        wsdl11Definition.setTargetNamespace(ComputerEndpoint.SERVICE_ENDPOINT);
        wsdl11Definition.setSchema(computersSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema computersSchema(){
        return new SimpleXsdSchema(new ClassPathResource("computers.xsd"));
    }
}
