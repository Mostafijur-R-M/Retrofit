package com.fendonus.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllChapter {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_idx")
    @Expose
    private Integer orderIdx;
    @SerializedName("course_id")
    @Expose
    private Integer courseId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private List<Video> video = null;

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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }
}
