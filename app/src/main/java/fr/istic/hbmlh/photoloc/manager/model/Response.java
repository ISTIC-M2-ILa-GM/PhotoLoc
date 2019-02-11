package fr.istic.hbmlh.photoloc.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = { "plus_code"})
public class Response {
    public List<Result> results;
}
