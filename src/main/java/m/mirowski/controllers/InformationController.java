package m.mirowski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    @Value("${business.property.p1}")
    private String businessProperty1;
    @Value("${business.property.p2}")
    private String businessProperty2;
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @GetMapping("/information/url")
    String getURL() {
        return dataSourceProperties.getUrl();
    }

    @GetMapping("/information/property/p1")
    String getBusinessProperty1() {
        return businessProperty1;
    }

    @GetMapping("/information/property/p2")
    String getBusinessProperty2() {
        return businessProperty2;
    }
}
