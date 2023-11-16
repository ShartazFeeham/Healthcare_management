package com.healtcare.community.enums;

public enum PostType {
    STATUS("status"),
    FEEDBACK("feedback"),
    ARTICLE("article"),
    FIRST_AID("firstaid"),
    FAQ("faq");

    private final String value;

    PostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}