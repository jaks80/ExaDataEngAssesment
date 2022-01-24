package com.ets.resource;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.r4.model.Bundle;

/**
 *
 * @author yusufakhond
 */
public interface ExternerResourceBuilder {

    public ExternerResourceBuilder setFhirContext(FhirContext ctx);

    public ExternerResourceBuilder setResourceFileName(String fileName);

    public Bundle build();
}
