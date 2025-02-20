package com.easypost.model.beta;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.ApiKey;
import com.easypost.model.BaseUser;
import com.easypost.model.CreditCardPriority;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import com.easypost.model.Utilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referral extends BaseUser {
    private List<ApiKey> apiKeys;

    /**
     * Get the api keys of the Referral user.
     *
     * @return the api keys of the Referral user.
     */
    public List<ApiKey> getApiKeys() {
        return apiKeys;
    }

    /**
     * Set the api keys of the Referral user.
     *
     * @param apiKeys the api keys of the Referral user.
     */
    public void setApiKeys(List<ApiKey> apiKeys) {
        this.apiKeys = apiKeys;
    }

    /**
     * Create a Referral object from parameter map. This function requires the Partner User's API key.
     *
     * @param params Map of the referral user parameters.
     * @return Referral object.
     * @throws EasyPostException when the request fails.
     */
    public static Referral create(Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a Referral object from parameter map. This function requires the Partner User's API key.
     *
     * @param params Map of the referral user parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Referral object.
     * @throws EasyPostException when the request fails.
     */
    public static Referral create(Map<String, Object> params, String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("user", params);

        return request(RequestMethod.POST, String.format("%s/%s", EasyPost.BETA_API_BASE, "referral_customers"),
                wrappedParams, Referral.class, apiKey);
    }

    /**
     * Update a Referral object email. This function requires the Partner User's API key.
     *
     * @param email  Email of the referral user to update.
     * @param userId ID of the referral user to update.
     * @return true if success.
     * @throws EasyPostException when the request fails.
     */
    public static boolean updateEmail(String email, String userId) throws EasyPostException {
        return updateEmail(email, userId, null);
    }

    /**
     * Update a Referral object email. This function requires the Partner User's API key.
     *
     * @param email  Email of the referral user to update.
     * @param userId ID of the referral user to update.
     * @param apiKey API key to use in request (overrides default API key).
     * @return true if success.
     * @throws EasyPostException when the request fails.
     */
    public static boolean updateEmail(String email, String userId, String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        wrappedParams.put("user", params);

        request(RequestMethod.PUT, String.format("%s/%s/%s", EasyPost.BETA_API_BASE, "referral_customers", userId),
                wrappedParams, Referral.class, apiKey);

        return true;
    }

    /**
     * List all Referral objects. This function requires the Partner User's API key.
     *
     * @param params Map of parameters.
     * @return List<Referral> object.
     * @throws EasyPostException when the request fails.
     */
    public static List<Referral> all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all Referral objects. This function requires the Partner User's API key.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return List<Referral> object.
     * @throws EasyPostException when the request fails.
     */
    public static List<Referral> all(final Map<String, Object> params, String apiKey) throws EasyPostException {
        Referral[] response =
                request(RequestMethod.GET, String.format("%s/%s", EasyPost.BETA_API_BASE, "referral_customers"), params,
                        Referral[].class, apiKey);

        return Arrays.asList(response);
    }

    /**
     * Add credit card to a referral user. This function requires the Referral User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param number          Credit card number.
     * @param expirationMonth Expiration month of the credit card.
     * @param expirationYear  Expiration year of the credit card.
     * @param cvc             CVC of the credit card.
     * @return CreditCard object.
     * @throws Exception when the request fails.
     * @deprecated Use {@link #addCreditCardToUser(String, String, int, int, String)} instead.
     * Last working version: v5.5.0. Removal: v7.0.0.
     */
    @Deprecated
    public static CreditCard addCreditCard(String referralApiKey, String number, int expirationMonth,
                                           int expirationYear, String cvc) throws Exception {
        return addCreditCard(referralApiKey, number, expirationMonth, expirationYear, cvc, CreditCardPriority.PRIMARY);
    }

    /**
     * Add credit card to a referral user. This function requires the Referral User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param number          Credit card number.
     * @param expirationMonth Expiration month of the credit card.
     * @param expirationYear  Expiration year of the credit card.
     * @param cvc             CVC of the credit card.
     * @return PaymentMethodObject object.
     * @throws Exception when the request fails.
     */
    public static PaymentMethodObject addCreditCardToUser(String referralApiKey, String number, int expirationMonth,
                                                          int expirationYear, String cvc) throws Exception {
        return addCreditCardToUser(referralApiKey, number, expirationMonth, expirationYear, cvc,
                PaymentMethod.Priority.PRIMARY);
    }

    /**
     * Add credit card to a referral user. This function requires the Referral User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param number          Credit card number.
     * @param expirationMonth Expiration month of the credit card.
     * @param expirationYear  Expiration year of the credit card.
     * @param cvc             CVC of the credit card.
     * @param priority        Priority of this credit card.
     * @return CreditCard object.
     * @throws Exception when the request fails.
     * @deprecated Use {@link #addCreditCardToUser(String, String, int, int, String, PaymentMethod.Priority)} instead.
     * Last working version: v5.5.0. Removal: v7.0.0.
     */
    public static CreditCard addCreditCard(String referralApiKey, String number, int expirationMonth,
                                           int expirationYear, String cvc, CreditCardPriority priority)
            throws Exception {

        // Convert a CreditCardPriority enum to a PaymentMethod.Priority enum
        PaymentMethod.Priority priorityEnum = null;
        switch (priority) {
            case PRIMARY:
                priorityEnum = PaymentMethod.Priority.PRIMARY;
                break;
            case SECONDARY:
                priorityEnum = PaymentMethod.Priority.SECONDARY;
                break;
            default:
                break;
        }

        if (priorityEnum == null) {
            throw new Exception("Invalid credit card priority.");
        }

        PaymentMethodObject paymentMethodObject =
                addCreditCardToUser(referralApiKey, number, expirationMonth, expirationYear, cvc, priorityEnum);

        // Convert the new PaymentMethodObject back into the deprecated CreditCard object
        CreditCard creditCard = new CreditCard();
        creditCard.setId(paymentMethodObject.getId());
        creditCard.setObject(paymentMethodObject.getObject());
        creditCard.setCreatedAt(paymentMethodObject.getCreatedAt());
        creditCard.setUpdatedAt(paymentMethodObject.getUpdatedAt());
        creditCard.setBrand(paymentMethodObject.getBrand());
        creditCard.setLast4(paymentMethodObject.getLast4());
        creditCard.setFees(paymentMethodObject.getFees());
        creditCard.setName(paymentMethodObject.getName());

        return creditCard;
    }

    /**
     * Add credit card to a referral user. This function requires the Referral User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param number          Credit card number.
     * @param expirationMonth Expiration month of the credit card.
     * @param expirationYear  Expiration year of the credit card.
     * @param cvc             CVC of the credit card.
     * @param priority        Priority of this credit card.
     * @return PaymentMethodObject object.
     * @throws Exception when the request fails.
     */
    public static PaymentMethodObject addCreditCardToUser(String referralApiKey, String number, int expirationMonth,
                                                          int expirationYear, String cvc,
                                                          PaymentMethod.Priority priority) throws Exception {
        String easypostStripeApiKey = retrieveEasypostStripeApiKey();
        String stripeToken;

        try {
            stripeToken = createStripeToken(number, expirationMonth, expirationYear, cvc, easypostStripeApiKey);
        } catch (Exception e) {
            throw new Exception("Could not send card details to Stripe, please try again later", e);
        }

        return createEasypostCreditCard(referralApiKey, stripeToken, priority.toString().toLowerCase());
    }

    /**
     * Retrieve EasyPost Stripe API key.
     *
     * @return EasyPost Stripe API key.
     * @throws EasyPostException when the request fails.
     */
    private static String retrieveEasypostStripeApiKey() throws EasyPostException {
        @SuppressWarnings ("unchecked") Map<String, String> response =
                request(RequestMethod.GET, String.format("%s/%s", EasyPost.BETA_API_BASE, "partners/stripe_public_key"),
                        null, Map.class, null);

        return response.getOrDefault("public_key", "");
    }

    /**
     * Get credit card token from Stripe.
     *
     * @param number               Credit card number.
     * @param expirationMonth      Expiration month of the credit card.
     * @param expirationYear       Expiration year of the credit card.
     * @param cvc                  CVC of the credit card.
     * @param easypostStripeApiKey EasyPost Stripe API key.
     * @return Stripe token.
     * @throws Exception when the request fails.
     */
    private static String createStripeToken(String number, int expirationMonth, int expirationYear, String cvc,
                                            String easypostStripeApiKey) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("number", number);
        params.put("exp_month", String.valueOf(expirationMonth));
        params.put("exp_year", String.valueOf(expirationYear));
        params.put("cvc", cvc);

        URL stripeUrl = new URL("https://api.stripe.com/v1/tokens");
        HttpURLConnection conn = (HttpURLConnection) stripeUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", String.format("%s %s", "Bearer", easypostStripeApiKey));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        String encodedURL = Utilities.getEncodedURL(params, "card");
        byte[] postData = encodedURL.getBytes(StandardCharsets.UTF_8);

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        StringBuilder response;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

            String line;
            response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append(System.lineSeparator());
            }
            br.close();
        } finally {
            conn.disconnect();
        }

        String responseBody = response.toString();

        @SuppressWarnings ("unchecked") Map<String, Object> responseMap = GSON.fromJson(responseBody, Map.class);

        return responseMap.get("id").toString();
    }

    /**
     * Submit Stripe credit card token to EasyPost.
     *
     * @param referralApiKey API key of the referral user.
     * @param stripeObjectId Stripe token.
     * @param priority       Credit card priority.
     * @return CreditCard object.
     * @throws EasyPostException when the request fails.
     */
    private static PaymentMethodObject createEasypostCreditCard(String referralApiKey, String stripeObjectId,
                                                                String priority) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("stripe_object_id", stripeObjectId);
        params.put("priority", priority);

        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("credit_card", params);

        return request(RequestMethod.POST, String.format("%s/%s", EasyPost.BETA_API_BASE, "credit_cards"),
                wrappedParams, PaymentMethodObject.class, referralApiKey);
    }
}
