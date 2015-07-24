# Recommendation Engine Service API
Dropwizard + Apache Mahout Recommendation Engine

Served with dropwizard. Data input support for postgres database only.

# Setup
- Setup config.yml with your settings.
- build project (I used NetBeans)
- Run jar: java -jar RecommendationEngine-VERSION.jar server your_config.yml

# Get Requests
- http://localhost:9000/recommendation/user/{userId}/{howMany}
- http://localhost:9000/recommendation/item/{itemId}/{howMany}

# TODO
- Make dataSource adaptable for other database servers. 
- API endpoint for getting recommendations with desired algorithm
- Authorization/Authentication
- Dropwizard health-checks

# Thanks
To google and these posts: 
https://chameerawijebandara.wordpress.com/2014/01/15/simple-recommendation-system-with-mahout-and-netbeans/
http://xorlev.com/blog/2012/09/30/web-machine-learning/

