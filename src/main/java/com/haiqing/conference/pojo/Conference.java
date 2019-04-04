package com.haiqing.conference.pojo;

/**
 * @description :会议管理实体类
 * @author haiqing
 * @Date: 2019/3/29
 */
public class Conference {
    private Integer id;

    private Integer meettingId;

    private Integer personnelId;

    private String title;

    private Long startTime;

    private Long endTime;

    private Long createAt;

    private Integer createBy;

    private Long updateAt;

    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeettingId() {
        return meettingId;
    }

    public void setMeettingId(Integer meettingId) {
        this.meettingId = meettingId;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", meettingId=" + meettingId +
                ", personnelId=" + personnelId +
                ", title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createAt=" + createAt +
                ", createBy=" + createBy +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                '}';
    }
}