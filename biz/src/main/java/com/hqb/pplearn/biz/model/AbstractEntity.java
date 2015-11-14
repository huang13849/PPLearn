package com.hqb.pplearn.biz.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class AbstractEntity implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4287712529393369951L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Date createAt = new Date();
    private Date updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreatAt(Date createAt) {
        this.createAt = createAt;
    }

    public java.util.Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(java.util.Date updateAt) {
        this.updateAt = updateAt;
    }

}
