/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import com.github.gurelkaynak.recommendationengine.api.DataModel;

/**
 *
 * @author gurel
 */
@Path("/datamodel")
@Produces(MediaType.APPLICATION_JSON)
public class DataModelResource {
    
    final private ReloadFromJDBCDataModel dataModel;
    
    public DataModelResource(ReloadFromJDBCDataModel dm){
        this.dataModel = dm;
    }
    
    @GET
    @Timed
    @Path("/update/")
    public DataModel update(){
        DataModel dm = new DataModel("OK");
        this.dataModel.refresh(null);
        return dm;
    }
    
    @GET
    @Timed
    @Path("/setpreference/{userId}/{itemId}/{howMany}")
    public DataModel setPreference(@PathParam("userId") long userId, @PathParam("itemId") long itemId, @PathParam("preference") float value){
        DataModel dm;
        try{
            this.dataModel.getDelegate().setPreference(userId, itemId, value);
            dm = new DataModel("OK");
        }catch(TasteException e){
            dm = new DataModel("FAIL");
        }
        return dm;
    }
}
