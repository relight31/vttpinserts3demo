package com.vttp.inserts3demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static com.vttp.inserts3demo.repository.Queries.*;

import java.sql.ResultSet;
import java.util.Optional;

import com.vttp.inserts3demo.model.Post;

@Repository
public class PostRepository {
    @Autowired
    private JdbcTemplate template;

    public boolean insertPost(Post post) {
        int updCount = template.update(SQL_INSERT_POST,
                post.getImage(),
                post.getComment(),
                post.getPoster(),
                post.getImageType());
        return updCount == 1;
        // insert only 1 record, expect 1
    }

    public Optional<Post> getPostByID(int postID) {
        return template.query(
                SQL_GET_POST_BY_POSTID,
                (ResultSet rs) -> {
                    if (!rs.next()) {
                        return Optional.empty();
                    } else {
                        final Post post = Post.populate(rs);
                        return Optional.of(post);
                    }
                }, 
                postID);
    }
}
