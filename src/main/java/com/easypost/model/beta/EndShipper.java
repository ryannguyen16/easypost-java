package com.easypost.model.beta;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.BaseAddress;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EndShipper extends BaseAddress {
    /**
     * Create EndShipper object from parameter map.
     *
     * @param params Map of end shipper parameters.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create EndShipper object from parameter map.
     *
     * @param params Map of end shipper parameters.
     * @param apiKey Optional API key to use for this request.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        wrappedParams.put("address", params);

        return request(RequestMethod.POST, String.format("%s/%s", EasyPost.BETA_API_BASE, "end_shippers"),
                wrappedParams, EndShipper.class, apiKey);
    }

    /**
     * Retrieve EndShipper object from API.
     *
     * @param id ID of end shipper to retrieve.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve EndShipper object from API.
     *
     * @param id     ID of end shipper to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s/%s/%s", EasyPost.BETA_API_BASE, "end_shippers", id), null,
                EndShipper.class, apiKey);
    }

    /**
     * List all EndShipper objects.
     *
     * @param params Map of parameters.
     * @return List<EndShipper>.
     * @throws EasyPostException when the request fails.
     */
    public static List<EndShipper> all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all EndShipper objects.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return List<EndShipper>.
     * @throws EasyPostException when the request fails.
     */
    public static List<EndShipper> all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        EndShipper[] response =
                request(RequestMethod.GET, String.format("%s/%s", EasyPost.BETA_API_BASE, "end_shippers"), params,
                        EndShipper[].class, apiKey);

        return Arrays.asList(response);
    }

    /**
     * Update an EndShipper object.
     *
     * @param params Map of parameters.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipper update(final Map<String, Object> params) throws EasyPostException {
        return update(params, null);
    }

    /**
     * Update an EndShipper object.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipper update(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        wrappedParams.put("address", params);

        EndShipper response = request(RequestMethod.PUT,
                String.format("%s/%s/%s", EasyPost.BETA_API_BASE, "end_shippers", this.getId()), wrappedParams,
                EndShipper.class, apiKey);

        this.merge(this, response);
        return this;
    }
}
