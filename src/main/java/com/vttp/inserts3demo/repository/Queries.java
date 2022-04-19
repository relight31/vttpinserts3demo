package com.vttp.inserts3demo.repository;

public class Queries {
    public static final String SQL_INSERT_POST = "insert into post (photo, comment, poster, mediatype) values (?,?,?,?)";
    public static final String SQL_GET_POST_BY_POSTID = "select * from post where post_id=?";
}
