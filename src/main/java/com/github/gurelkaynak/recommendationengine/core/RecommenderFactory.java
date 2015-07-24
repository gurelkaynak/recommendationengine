/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CachingItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.CachingUserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


/**
 *
 * @author gurel
 */
public class RecommenderFactory {
    
    private double thresholdValue = 0.3;
    private int nearestNUserValue = 100;
    
    //can be "threshold" or "nearestnuser"
    private String userNeighborhoodAlgorithm = "threshold";
    
    
    public Recommender buildUserBasedRecommender(DataModel dataModel){
        Recommender recommender;
        recommender = null;
        
        try{
            UserSimilarity similarity = new CachingUserSimilarity(new PearsonCorrelationSimilarity(dataModel, Weighting.WEIGHTED), dataModel);
            UserNeighborhood neighborhood;
            
            switch (this.userNeighborhoodAlgorithm) {
                case "threshold":
                    neighborhood = new ThresholdUserNeighborhood(this.thresholdValue, similarity, dataModel);
                    break;
                case "nearestnuser":
                    neighborhood = new NearestNUserNeighborhood(this.nearestNUserValue, similarity, dataModel);
                    break;
                default:
                    neighborhood = new ThresholdUserNeighborhood(this.thresholdValue, similarity, dataModel);
                    break;
            }
            
            recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            
        }catch(TasteException exception){
            System.err.println(exception);
        }
        
        return recommender;
    }
    
    public ItemBasedRecommender buildItemBasedRecommender(DataModel dataModel){
        ItemBasedRecommender recommender;
        recommender = null;
        
        try{
            ItemSimilarity similarity = new CachingItemSimilarity(new TanimotoCoefficientSimilarity(dataModel), dataModel);            
            recommender = new GenericItemBasedRecommender(dataModel, similarity);
            
        }catch(TasteException exception){
            System.err.println(exception);
        }
        
        return recommender;
    }

    @JsonProperty
    public double getThresholdValue() {
        return thresholdValue;
    }

    @JsonProperty
    public void setThresholdValue(double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    @JsonProperty
    public int getNearestNUserValue() {
        return nearestNUserValue;
    }

    @JsonProperty
    public void setNearestNUserValue(int nearestNUserValue) {
        this.nearestNUserValue = nearestNUserValue;
    }

    @JsonProperty
    public String getUserNeighborhoodAlgorithm() {
        return userNeighborhoodAlgorithm;
    }

    @JsonProperty
    public void setUserNeighborhoodAlgorithm(String userNeighborhoodAlgorithm) {
        this.userNeighborhoodAlgorithm = userNeighborhoodAlgorithm;
    }
    
    
}
