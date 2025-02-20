package com.easypost.model;

public final class ShipmentOptions {
    private String smartpostHub;
    private String smartpostManifest;

    /**
     * Get the SmartPost hub of the shipment.
     *
     * @return the SmartPost hub of the shipment
     */
    public String getSmartpostHub() {
        return smartpostHub;
    }

    /**
     * Set the SmartPost hub of the shipment.
     *
     * @param smartpostHub the SmartPost hub of the shipment
     */
    public void setSmartpostHub(final String smartpostHub) {
        this.smartpostHub = smartpostHub;
    }

    /**
     * Get the SmartPost manifest of the shipment.
     *
     * @return the SmartPost manifest of the shipment
     */
    public String getSmartpostManifest() {
        return smartpostManifest;
    }

    /**
     * Set the SmartPost manifest of the shipment.
     *
     * @param smartpostManifest the SmartPost manifest of the shipment
     */
    public void setSmartpostManifest(final String smartpostManifest) {
        this.smartpostManifest = smartpostManifest;
    }
}
