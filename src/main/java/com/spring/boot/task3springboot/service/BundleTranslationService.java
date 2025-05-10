package com.spring.boot.task3springboot.service;
import com.spring.boot.task3springboot.model.BundleMessage;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BundleTranslationService {
    private static ResourceBundleMessageSource messageResource;

    public BundleTranslationService(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.messageResource = resourceBundleMessageSource;
    }

    public static BundleMessage getMessageArEn(String key) {
        return new BundleMessage(
                messageResource.getMessage(key, null, new Locale("ar")),
                messageResource.getMessage(key, null, new Locale("en"))
        );
    }

}
