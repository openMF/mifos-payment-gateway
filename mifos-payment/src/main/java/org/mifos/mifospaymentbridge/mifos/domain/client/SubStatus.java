package org.mifos.mifospaymentbridge.mifos.domain.client;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubStatus {

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("mandatory")
    private Boolean mandatory;
}
