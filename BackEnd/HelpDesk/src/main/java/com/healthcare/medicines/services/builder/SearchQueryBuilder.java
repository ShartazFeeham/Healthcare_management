package com.healthcare.medicines.services.builder;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchQueryBuilder {
    private String url;
    private String photo;
    private String type;
    private Integer match;
    private List<String> params;
    private List<String> values;

    public SearchQueryBuilder url(String url) {
        this.url = url;
        return this;
    }

    public SearchQueryBuilder photo(String photo) {
        this.photo = photo;
        return this;
    }


    public SearchQueryBuilder match(int matchValue){
        this.match = matchValue;
        return this;
    }

    public SearchQueryBuilder type(String type){
        this.type = type;
        return this;
    }

    public SearchQueryBuilder params(List<String> params){
        this.params = params;
        return this;
    }

    public SearchQueryBuilder values(List<String> values){
        this.values = values;
        return this;
    }

    public SearchQueryBuilder build() {
        return this;
    }
}
