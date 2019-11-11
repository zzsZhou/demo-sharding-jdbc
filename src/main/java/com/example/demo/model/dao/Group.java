package com.example.demo.model.dao;

import javax.persistence.*;

@Table(name = "group_info")
public class Group {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name;

    @Column(name = "user_number")
    private Integer userNumber;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return user_number
     */
    public Integer getUserNumber() {
        return userNumber;
    }

    /**
     * @param userNumber
     */
    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
}