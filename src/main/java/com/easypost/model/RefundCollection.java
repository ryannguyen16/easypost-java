package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class RefundCollection extends EasyPostResource {
    private List<Refund> refunds;
    private Boolean hasMore;

    /**
     * Get whether there are more Refund objects to retrieve.
     *
     * @return true if there are more Refund objects to retrieve.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more Refund objects to retrieve.
     *
     * @param hasMore true if there are more Refund objects to retrieve.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Get this RefundCollection's Refund objects.
     *
     * @return a List of Refund objects.
     */
    public List<Refund> getRefunds() {
        return refunds;
    }

    /**
     * Set this RefundCollection's Refund objects.
     *
     * @param refunds a List of Refund objects.
     */
    public void setRefunds(final List<Refund> refunds) {
        this.refunds = refunds;
    }
}
