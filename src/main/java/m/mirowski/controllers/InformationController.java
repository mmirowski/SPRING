package m.mirowski.controllers;

import m.mirowski.TasksConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    private TasksConfigurationProperties myProperties;
    private DataSourceProperties dataSourceProperties;

    InformationController(final DataSourceProperties dsp, final TasksConfigurationProperties tcp) {
        this.dataSourceProperties = dsp;
        this.myProperties = tcp;
    }

    @GetMapping("/information/property/p1")
    boolean getAllowMultipleTasks() {
        return myProperties.getTemplate().isAllowMultipleTasks();
    }

    @GetMapping("/information/url")
    String getURL() {
        return dataSourceProperties.getUrl();
    }

}
