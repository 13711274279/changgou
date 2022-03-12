package com.changgou.file;

import java.io.Serializable;

public class FastDFSFile implements Serializable {
    private String name;
    private byte[] content;
    private String ext;
    private  String md5;
    private String author;

    public FastDFSFile(){

    };
    public FastDFSFile(String name, byte[] content, String ext, String md5, String author){
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public FastDFSFile(String name, byte[] content, String ext){
        this.name = name;
        this.content = content;
        this.ext = ext;
    }


    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getExt() {
        return ext;
    }

    public String getMd5() {
        return md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
