package com.yzm.mybatis.entity.table;

import java.io.Serializable;

public class Movie implements Serializable {
    private Integer id;

    private String name;

    private String director;

    private Double score;

    private String showDate;

    private static final long serialVersionUID = 1L;

    public Movie(Integer id, String name, String director, Double score, String showDate) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.score = score;
        this.showDate = showDate;
    }

    public Movie() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate == null ? null : showDate.trim();
    }
}