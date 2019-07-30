package com.ffs.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    public static final int PAGE_SIZE=10;
    private int pc;
    private String url;
    private List<T> beanList;
    private int tr;
    public int getTp(){
        int tp=tr/PAGE_SIZE;
        return tr%PAGE_SIZE==0?tp:tp+1;
    }
}
