package org.mifos.mifospaymentbridge.mifos.domain.client;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Timeline {

    @JsonProperty("submittedOnDate")
    private List<Long> submittedOnDate = new ArrayList<Long>();
    @JsonProperty("submittedByUsername")
    private String submittedByUsername;
    @JsonProperty("submittedByFirstname")
    private String submittedByFirstname;
    @JsonProperty("submittedByLastname")
    private String submittedByLastname;
    @JsonProperty("activatedOnDate")
    private List<Long> activatedOnDate = new ArrayList<Long>();
    @JsonProperty("activatedByUsername")
    private String activatedByUsername;
    @JsonProperty("activatedByFirstname")
    private String activatedByFirstname;
    @JsonProperty("activatedByLastname")
    private String activatedByLastname;
}
