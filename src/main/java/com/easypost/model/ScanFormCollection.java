package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ScanFormCollection extends EasyPostResource {
    private List<ScanForm> scanForms;
    private Boolean hasMore;

    /**
     * Get whether there are more ScanForms to retrieve.
     *
     * @return true if there are more ScanForms to retrieve, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more ScanForms to retrieve.
     *
     * @param hasMore true if there are more ScanForms to retrieve, false otherwise.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Get this ScanFormCollection's ScanForm objects.
     *
     * @return List of ScanForm objects.
     */
    public List<ScanForm> getScanForms() {
        return scanForms;
    }

    /**
     * Set this ScanFormCollection's ScanForm objects.
     *
     * @param scanForms List of ScanForm objects.
     */
    public void setScanForms(final List<ScanForm> scanForms) {
        this.scanForms = scanForms;
    }
}
