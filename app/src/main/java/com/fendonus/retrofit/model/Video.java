package com.fendonus.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_idx")
    @Expose
    private Integer orderIdx;
    @SerializedName("course_id")
    @Expose
    private Integer courseId;
    @SerializedName("chapter_id")
    @Expose
    private Integer chapterId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ytd")
    @Expose
    private String ytd;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("small_thumb")
    @Expose
    private String smallThumb;
    @SerializedName("file")
    @Expose
    private Object file;
    @SerializedName("note")
    @Expose
    private Object note;

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

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYtd() {
        return ytd;
    }

    public void setYtd(String ytd) {
        this.ytd = ytd;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSmallThumb() {
        return smallThumb;
    }

    public void setSmallThumb(String smallThumb) {
        this.smallThumb = smallThumb;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

}
