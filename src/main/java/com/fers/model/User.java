/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fers.model;

/**
 *
 * @author ndukw
 */
public class User {
    private int id;
    private String name;
    private Status status;
    
    public enum Status{
        SAFE,
        NEED_HELP,
        UNKNOWN
    }
    public User(int id, String name){
        this.id = id;
        this.name = name;
        this.status = Status.UNKNOWN;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Status getStatus(){
        return status;
    }
    public void setStatus(Status status){
        this.status = status;
    }
    @Override
    public String toString(){
        return "User{"+ "id="+ ", name='"+ name + '\''+ ", status=" + status + '}';
                
    }
}
