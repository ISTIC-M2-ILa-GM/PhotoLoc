package fr.istic.hbmlh.photoloc.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "address_components"})
public class Result {

    public String formatted_address;
}
