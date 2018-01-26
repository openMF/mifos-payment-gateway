package org.mifos.mifospaymentbridge.mifos.api;


import org.mifos.mifospaymentbridge.mifos.domain.client.Client;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClientInterface {

    /**
     * Search client information using client ID
     *
     * @param clientId Client ID
     * @param tenantIdentifier Mifos Tenant Identifier
     * @param isPretty Flag whether to format JSON
     * @return
     */
    @GET("clients/{clientId}")
    Call<Client> getClientById(@Path("clientId") Long clientId,
                               @Query("tenantIdentifier") String tenantIdentifier,
                               @Query("pretty") boolean isPretty);

    /**
     * Search client information using client ID
     *
     * @param clientId Client ID
     * @param tenantIdentifier Mifos Tenant Identifier
     * @param isPretty Flag whether to format JSON
     * @param fields Specifies the fields you want in the result set
     * @return
     */
    @GET("clients/{clientId}")
    Call<Client> getClientById(@Path("clientId") Long clientId,
                               @Query("tenantIdentifier") String tenantIdentifier,
                               @Query("pretty") boolean isPretty,
                               @Query("fields") String fields);

    /**
     * Search client information using phone number
     *
     * @param phoneNumber Client phonenumber
     * @param tenantIdentifier Mifos Tenant Identifier
     * @param isPretty Flag whether to format JSON
     * @return
     */
    @GET("clients")
    Call<Client> getClientByPhoneNumber(@Query("tenantIdentifier") String tenantIdentifier,
                               @Query("pretty") boolean isPretty,
                               @Query("sqlSearch") String phoneNumber);
}
