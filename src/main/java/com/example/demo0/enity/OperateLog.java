package com.example.demo0.enity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 2017/8/25.
 */
public class OperateLog implements Serializable{

    private Long id;

    private String operateName;

    private String descripes;

    private String content;

    private Date addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getDescripes() {
        return descripes;
    }

    public void setDescripes(String descripes) {
        this.descripes = descripes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "id=" + id +
                ", operateName='" + operateName + '\'' +
                ", descripes='" + descripes + '\'' +
                ", content='" + content + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
