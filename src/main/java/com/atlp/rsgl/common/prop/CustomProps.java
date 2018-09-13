package com.atlp.rsgl.common.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "custom-props") //接收application.yml中的custom-props下面的属性
public class CustomProps {
    private List<String> accessIgnore;
    private String tysqPath;
    private String appCode;
}
