package com.magic.elana.data;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Post {
    public static final String TITLE_KEY = "title";
    public static final String CONTENT_KEY = "content";
    public static final String REPLY_KEY = "replies";

    public abstract String title();
    public abstract String content();
    public abstract String id();

    public static Builder builder() {
        return new AutoValue_Post.Builder()
                .id(RandomStringGenerator.generate(10));
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder title(String title);

        public abstract Builder content(String content);

        abstract Builder id(String id);

        public abstract Post build();
    }
}
