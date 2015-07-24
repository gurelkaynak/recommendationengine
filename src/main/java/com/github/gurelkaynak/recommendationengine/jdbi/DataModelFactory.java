/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine.jdbi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author gurel
 */
public class DataModelFactory {
    @NotEmpty
    private String table;
    
    @NotEmpty
    private String userField;
    
    @NotEmpty
    private String itemField;
    
    @NotEmpty
    private String ratingField;
    
    public ReloadFromJDBCDataModel build(PGPoolingDataSource dataSource) throws TasteException{
        return new ReloadFromJDBCDataModel(new PostgreSQLJDBCDataModel(dataSource, this.table, this.userField, this.itemField, this.ratingField, null));
    }

    @JsonProperty
    public String getTable() {
        return table;
    }

    @JsonProperty
    public void setTable(String table) {
        this.table = table;
    }

    @JsonProperty
    public String getUserField() {
        return userField;
    }

    @JsonProperty
    public void setUserField(String userField) {
        this.userField = userField;
    }

    @JsonProperty
    public String getItemField() {
        return itemField;
    }

    @JsonProperty
    public void setItemField(String itemField) {
        this.itemField = itemField;
    }

    @JsonProperty
    public String getRatingField() {
        return ratingField;
    }

    @JsonProperty
    public void setRatingField(String ratingField) {
        this.ratingField = ratingField;
    }
}
