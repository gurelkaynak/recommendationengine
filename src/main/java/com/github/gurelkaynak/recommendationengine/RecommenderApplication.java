/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine;

import com.github.gurelkaynak.recommendationengine.resources.DataModelResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.github.gurelkaynak.recommendationengine.resources.RecommendationResource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
//import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author gurel
 */
public class RecommenderApplication extends Application<RecommenderConfiguration>{
    
    public static void main(String[] args) throws Exception{
        new RecommenderApplication().run(args);
    }
    
    @Override
    public String getName(){
        return "Recommendation Engine";
    }
    
    @Override
    public void initialize(Bootstrap<RecommenderConfiguration> bootstrap) {
        // nothing to do yet
    }
    
    @Override
    public void run(RecommenderConfiguration configuration,
                    Environment environment) {
        
        PGPoolingDataSource pgPoolingDataSource = configuration.getDataSourceFactory().build(environment);
        ReloadFromJDBCDataModel dataModel;
        try {
            dataModel = configuration.getDataModelFactory().build(pgPoolingDataSource);
        } catch (TasteException e) {
            dataModel = null;
            System.err.println(e);
            System.exit(-1);
        }
        
        Recommender userBasedRecommender = configuration.getRecommenderFactory().buildUserBasedRecommender(dataModel);
        ItemBasedRecommender itemBasedRecommender = configuration.getRecommenderFactory().buildItemBasedRecommender(dataModel);
        
        final RecommendationResource userRecommendationResource = new RecommendationResource(userBasedRecommender, itemBasedRecommender);
        final DataModelResource dataModelResource = new DataModelResource(dataModel);
        environment.jersey().register(userRecommendationResource);
        environment.jersey().register(dataModelResource);
    }
}
