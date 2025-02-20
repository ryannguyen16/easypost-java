package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ReportCollection extends EasyPostResource {
    private List<Report> reports;
    private Boolean hasMore;

    /**
     * Get whether there are more reports to retrieve.
     *
     * @return true if there are more reports to retrieve, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more reports to retrieve.
     *
     * @param hasMore true if there are more reports to retrieve, false otherwise.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * Get this ReportCollection's Report objects.
     *
     * @return List of Report objects.
     */
    public List<Report> getReports() {
        return reports;
    }

    /**
     * Set this ReportCollection's Report objects.
     *
     * @param reports List of Report objects.
     */
    public void setReports(final List<Report> reports) {
        this.reports = reports;
    }
}
