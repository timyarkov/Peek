package com.timyarkov.peek.model.items;

import java.util.Objects;

/**
 * POJO for a post.
 */
public class Post {
    private String title;
    private PostDataType type;
    private String data;

    /**
     * Creates a post.
     * @param title Title of the post.
     * @param type Type of the post.
     * @param data Data for the post; contents should depend on the type (i.e.
     *             text type will contain post body, others will be a URL).
     */
    public Post(String title, PostDataType type, String data) {
        this.title = title;
        this.type = type;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public PostDataType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                type == post.type &&
                Objects.equals(data, post.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, data);
    }
}
