package com.fendonus.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_idx")
    @Expose
    private Integer orderIdx;
    @SerializedName("segment_id")
    @Expose
    private Integer segmentId;
    @SerializedName("sub_segment_id")
    @Expose
    private Integer subSegmentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("smc")
    @Expose
    private String smc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(Integer orderIdx) {
        this.orderIdx = orderIdx;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public Integer getSubSegmentId() {
        return subSegmentId;
    }

    public void setSubSegmentId(Integer subSegmentId) {
        this.subSegmentId = subSegmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSmc() {
        return smc;
    }

    public void setSmc(String smc) {
        this.smc = smc;
    }
}
