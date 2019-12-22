package com.magic.elana.data;

import com.google.auto.value.AutoValue;

import java.util.HashMap;
import java.util.Map;

@AutoValue
public abstract class Comment {
    public static final String CONTENT_KEY = "content";

    public abstract String content();

    public static Comment create(String content) {
        return new AutoValue_Comment(content);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(CONTENT_KEY, content());
        return map;
    }

}
