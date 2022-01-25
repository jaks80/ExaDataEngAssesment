package com.ets.resource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import java.io.InputStream;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.stereotype.Service;

/**
 *
 * @author yusufakhond
 */
@Service
public class ExternalResourceBundleBuilder implements ExternerResourceBuilder {

    private FhirContext ctx;
    private String fileName;
    
    public ExternalResourceBundleBuilder(){}

    @Override
    public ExternerResourceBuilder setFhirContext(FhirContext ctx) {
        this.ctx = ctx;
        return this;
    }

    @Override
    public ExternerResourceBuilder setResourceFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public Bundle build() {
        IParser parser = ctx.newJsonParser();
        InputStream is = ExternalResourceBundleBuilder.class.getClassLoader().getResourceAsStream(fileName);
        Bundle bundle = parser.parseResource(Bundle.class, is);
        return bundle;
    }
}
