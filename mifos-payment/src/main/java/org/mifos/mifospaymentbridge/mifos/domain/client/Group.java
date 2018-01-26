package org.mifos.mifospaymentbridge.mifos.domain.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("accountNo")
    private String accountNo;

    @JsonProperty("name")
    private String name;
}
