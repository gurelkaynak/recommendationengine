/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.gurelkaynak.recommendationengine.api.Recommendation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.Recommender;

/**
 *
 * @author gurel
 */
@Path("/recommendation")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {
    
    final private Recommender userBasedRecommender;
    final private ItemBasedRecommender itemBasedRecommender;
    
    public RecommendationResource(Recommender u, ItemBasedRecommender i){
        this.userBasedRecommender = u;
        this.itemBasedRecommender = i;
    }
    
    @GET
    @Timed
    @Path("/user/{userId}/{howMany}")
    public Recommendation getUserRecommendations(@PathParam("userId") long uId, @PathParam("howMany") int howMany){
        
        Recommendation n;
        try{
            n = new Recommendation(this.userBasedRecommender.recommend(uId, howMany));
        }catch(TasteException exception){
            n = null;
        }
        return n;
    }
    
    @GET
    @Timed
    @Path("/item/{itemId}/{howMany}")
    public Recommendation getItemRecommendations(@PathParam("itemId") long iId, @PathParam("howMany") int howMany){
        
        Recommendation n;
       
        try{
            n = new Recommendation(this.itemBasedRecommender.mostSimilarItems(iId, howMany));
        }catch(TasteException exception){
            n = null;
        }
        return n;
    }
}
