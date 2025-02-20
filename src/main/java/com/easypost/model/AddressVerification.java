package com.easypost.model;

import java.util.List;

public final class AddressVerification {
    private Boolean success;
    private List<Error> errors;
    private AddressDetail details;

    /**
     * Get the address detail object.
     *
     * @return AddressDetail object
     */
    public AddressDetail getAddressDetail() {
        return details;
    }

    /**
     * Set the address detail object.
     *
     * @param details AddressDetail object
     */
    public void setAddressDetail(final AddressDetail details) {
        this.details = details;
    }

    /**
     * Get the list of errors that occurred during the address verification.
     *
     * @return list of Error objects
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * Set the list of errors that occurred during the address verification.
     *
     * @param errors list of Error objects
     */
    public void setErrors(final List<Error> errors) {
        this.errors = errors;
    }

    /**
     * Get whether the address verification was successful.
     *
     * @return true if the address was successfully verified
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Set whether the address verification was successful.
     *
     * @param success true if the address was successfully verified
     */
    public void setSuccess(final Boolean success) {
        this.success = success;
    }
}
