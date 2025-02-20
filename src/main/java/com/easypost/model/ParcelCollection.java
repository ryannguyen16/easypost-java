package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ParcelCollection extends EasyPostResource {
    private List<Parcel> parcels;
    private Boolean hasMore;

    /**
     * Get whether there are more Parcels to retrieve.
     *
     * @return true if there are more Parcels to retrieve, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more Parcels to retrieve.
     *
     * @param hasMore true if there are more Parcels to retrieve, false otherwise.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Get a list of this ParcelCollections Parcel objects.
     *
     * @return List of Parcel objects.
     */
    public List<Parcel> getParcels() {
        return parcels;
    }

    /**
     * Set this ParcelCollections Parcel objects.
     *
     * @param parcels List of Parcel objects.
     */
    public void setParcels(final List<Parcel> parcels) {
        this.parcels = parcels;
    }
}
