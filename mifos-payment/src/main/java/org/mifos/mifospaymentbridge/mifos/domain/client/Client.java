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
public class Client {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("accountNo")
    private String accountNo;

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("subStatus")
    private SubStatus subStatus;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("activationDate")
    private List<Long> activationDate = new ArrayList<Long>();

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("middlename")
    private String middlename;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("mobileNo")
    private String mobileNo;

    @JsonProperty("dateOfBirth")
    private List<Long> dateOfBirth = new ArrayList<Long>();

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("clientType")
    private ClientType clientType;

    @JsonProperty("clientClassification")
    private ClientClassification clientClassification;

    @JsonProperty("officeId")
    private Long officeId;

    @JsonProperty("officeName")
    private String officeName;

    @JsonProperty("staffId")
    private Long staffId;

    @JsonProperty("staffName")
    private String staffName;

    @JsonProperty("timeline")
    private Timeline timeline;

    @JsonProperty("savingsAccountId")
    private Long savingsAccountId;

    @JsonProperty("legalForm")
    private LegalForm legalForm;

    @JsonProperty("groups")
    private List<Group> groups = new ArrayList<Group>();

    @JsonProperty("clientNonPersonDetails")
    private ClientNonPersonDetails clientNonPersonDetails;
}
