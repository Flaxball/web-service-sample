package io.acari;

import io.acari.simple.web_service.AllComputersRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Java6Assertions.assertThat;

//One Integration Test.
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebServiceSampleApplicationTests {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

    @LocalServerPort
    private int port = 0;

    @Before
    public void setUp() throws Exception {
        jaxb2Marshaller.setPackagesToScan(ClassUtils.getPackageName(AllComputersRequest.class));
        jaxb2Marshaller.afterPropertiesSet();
    }

    @Test
    public void contextLoads() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller);
        AllComputersRequest getAllComputerRequest = new AllComputersRequest();
        assertThat(webServiceTemplate.marshalSendAndReceive(
                "http://localhost:" + port + WebServiceConfig.COMPUTER_SERVICE, getAllComputerRequest)).isNotNull();
    }

}
