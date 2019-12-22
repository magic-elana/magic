package com.magic.elana.data;

import com.google.auto.value.AutoValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoValue
public abstract class Post {
    public static final String TITLE_KEY = "title";
    public static final String CONTENT_KEY = "content";
    public static final String REPLY_KEY = "replies";

    public abstract String title();
    public abstract String content();
    public abstract List<Comment> comments();

    public static Builder builder() {
        return new AutoValue_Post.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder title(String title);

        public abstract Builder content(String content);

        public abstract Builder comments(List<Comment> comments);

        public abstract Post build();
    }
}
