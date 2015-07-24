/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gurelkaynak.recommendationengine.jdbi;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;
import org.postgresql.ds.PGPoolingDataSource;


/**
 *
 * @author gurel
 */
public class DataSourceFactory {
    
    @NotEmpty
    private String host;
    
    @NotEmpty
    private String db;
    
    @NotEmpty
    private String user;
    
    @NotEmpty
    private String password;
    
    private int port = 5432;
    
    private int maxConnections = 10;

    public PGPoolingDataSource build(Environment environment) {
        final PGPoolingDataSource pg_ds = new PGPoolingDataSource();
        pg_ds.setDataSourceName("pg_ds");
        pg_ds.setServerName(this.getHost());
        pg_ds.setDatabaseName(this.getDb());
        pg_ds.setUser(this.getUser());
        pg_ds.setPassword(this.getPassword());
        pg_ds.setPortNumber(this.getPort());
        pg_ds.setMaxConnections(this.getMaxConnections());
        environment.lifecycle().manage(new Managed() {
            @Override
            public void start() {
            }

            @Override
            public void stop() {
                pg_ds.close();
            }
        });
        return pg_ds;
    }

    @JsonProperty
    public String getDb() {
        return db;
    }

    @JsonProperty
    public void setDb(String db) {
        this.db = db;
    }
    
    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public String getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }

    @JsonProperty
    public int getMaxConnections() {
        return maxConnections;
    }

    @JsonProperty
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }
}
