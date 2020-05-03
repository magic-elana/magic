package com.magic.elana.data;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Post {
    public static final String TITLE_KEY = "title";
    public static final String CONTENT_KEY = "content";
    public static final String REPLY_KEY = "replies";

    public abstract String title();
    public abstract String content();
    public abstract long timeStamp();

    public static Builder builder() {
        return new AutoValue_Post.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder title(String title);

        public abstract Builder content(String content);

        public abstract Builder timeStamp(long timeStamp);


        public abstract Post build();
    }
}
