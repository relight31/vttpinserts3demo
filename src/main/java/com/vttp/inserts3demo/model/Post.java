package com.vttp.inserts3demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    private String poster;
    private String comment;
    private String imageType;
    private byte[] image;
    private int postID;

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageType() {
        return this.imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getPostID() {
        return this.postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public static Post populate(ResultSet rs) throws SQLException{
        Post post = new Post();
        post.setPostID(rs.getInt("post_id"));
        post.setComment(rs.getString("comment"));
        post.setImageType(rs.getString("mediatype"));
        post.setImage(rs.getBytes("photo"));
        post.setPoster(rs.getString("poster"));
        return post;
    }
}
