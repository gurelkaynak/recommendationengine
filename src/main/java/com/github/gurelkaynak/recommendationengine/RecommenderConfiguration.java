/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine;

import com.github.gurelkaynak.recommendationengine.jdbi.DataSourceFactory;
import io.dropwizard.Configuration;
import org.postgresql.ds.PGPoolingDataSource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.gurelkaynak.recommendationengine.jdbi.DataModelFactory;
import com.github.gurelkaynak.recommendationengine.core.RecommenderFactory;
//import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author gurel
 */
public class RecommenderConfiguration extends Configuration{
          
    //@NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();
    
    private RecommenderFactory recommenderFactory = new RecommenderFactory();
    
    private DataModelFactory dataModelFactory = new DataModelFactory();    
    
    @JsonProperty("recommender")
    public RecommenderFactory getRecommenderFactory() {
        return recommenderFactory;
    }
    
    @JsonProperty("recommender")
    public void setRecommenderFactory(RecommenderFactory recommenderFactory) {
        this.recommenderFactory = recommenderFactory;
    }

    @JsonProperty("dataModel")
    public DataModelFactory getDataModelFactory() {
        return dataModelFactory;
    }

    @JsonProperty("dataModel")
    public void setDataModelFactory(DataModelFactory dataModelFactory) {
        this.dataModelFactory = dataModelFactory;
    }
    
    @JsonProperty("dataSource")
    public DataSourceFactory getDataSourceFactory(){
        return this.dataSourceFactory;
    }
    
    @JsonProperty("dataSource")
    public void setDataSourceFactory(DataSourceFactory dataSource){
        this.dataSourceFactory = dataSource;
    }
}
