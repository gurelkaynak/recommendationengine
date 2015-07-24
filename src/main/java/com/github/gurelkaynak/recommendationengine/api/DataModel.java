/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine.api;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author gurel
 */
public class DataModel {
    
    @Length(max = 3)
    private String content;    
    
    public DataModel(String content){
        this.content = content;
    }
    
    @JsonProperty
    public String getContent() {
        return content;
    }
}
