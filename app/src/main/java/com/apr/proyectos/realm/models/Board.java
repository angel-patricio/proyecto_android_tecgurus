package com.apr.proyectos.realm.models;

import com.apr.proyectos.realm.MyApplication;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by angel on 24/03/2018.
 */

public class Board extends RealmObject {

    @PrimaryKey
    private int id;

    @Required
    private String title;

    @Required
    private Date createdAt;

    public Board()  {}

    public Board(String title)  {
        this.id = MyApplication.BoardId.incrementAndGet();
        this.title = title;
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
