# Recommendation Engine Service API
Apache Mahout recommendations (http://mahout.apache.org/) as a RESTful web service served with dropwizard (http://www.dropwizard.io/).  

# Requirements
- Java 1.7 
- Postgres database server (required)
- Ratings table or view like "user_id, item_id, rating ..."

# Setup
- Setup config.yml with your settings
- build project (I used NetBeans)
- Run jar: java -jar RecommendationEngine-VERSION.jar server your_config.yml

# Get Requests
- http://localhost:9000/recommendation/user/{userId}/{howMany}
- http://localhost:9000/recommendation/item/{itemId}/{howMany}
- http://localhost:9000/datamodel/update/ refreshes the datamodel for new user-item pairs

# TODO
- Make dataSource adaptable for other database servers
- Updating dataModel with different algorithm at runtime or create endpoints for all or some algorithms
- Authorization/Authentication
- Dropwizard health-checks

# Thanks
To google and these posts: 
https://chameerawijebandara.wordpress.com/2014/01/15/simple-recommendation-system-with-mahout-and-netbeans/
http://xorlev.com/blog/2012/09/30/web-machine-learning/

