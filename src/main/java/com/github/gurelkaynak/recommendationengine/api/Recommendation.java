/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine.api;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author gurel
 */
public class Recommendation {
    private List<RecommendedItem> recommendations;
    
    public Recommendation(){
        
    }
    
    public Recommendation(List<RecommendedItem> recommendations){
        this.recommendations = recommendations;
    }
    
    @JsonProperty
    public List<RecommendedItem> getRecommendations(){
        return this.recommendations;
    }
    
    @JsonProperty
    public void setRecommendations(List<RecommendedItem> r){
        this.recommendations = r;
    }
}
