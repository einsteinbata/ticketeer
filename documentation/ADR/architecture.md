# Architecture Decision Records

## Index
* Caching
    * Caching Strategy
        * Cache Warming
        * Cache Refresh / Cache Keep-warm
* Messaging/Queueing
    * Messaging/Queueing Strategy
        *   Purchases

## Caching

### Caching Strategy
##### Cache Warming
The initial option was to return cached events by mapping the fields on the HTTP request to a set of pre-defined cache keys, and in case of a cache-miss, the fields would then be used to generate a custom query which outputs would be stored in the cache.

The issue with this approach, is that the custom queries return combined outputs, thus making it difficult to associate the database query output items with their respective cache keys.

This approach would only be appropriate if the custom queries' outputs were being reused frequently.

A solutions to this issue, would be to make a few simple, one condition requests at first, based on the HTTP user request, and then saving them to the cache.
This would simplify the caching process, but would also reduce the performance on the very first request after the application starts up and on all subsequent reads whenever the cache expires.

A possible solution to address the reduced performance issue that may occur whenever the application starts, would be to _warm-up_ the caches when the application starts, by performing a few common database SELECT queries and saving them to the cache.

##### Cache Refresh / Cache Keep-warm
Repopulating the cache on the first request after the cache's _time_to_live_ (TTL) expires, may cause the same issue we were trying to avoid at the application startup.
For this reason, running a "cache-refresh" schedule seems like a good option. 

## Messaging/Queueing
### Messaging/Queueing Strategy
To decouple some of the applications components, a queueeing system was necessary. RabbitMQ was chosen for it's simplicity, 

#### Purchases
Since the aim was to present immediate responses on the payment layer, the purchase requests are only going to be placed on the queue for additional processing after the payment is successfully completed on the third party.

After the message is processed successfully, it is placed in the RabbitMQ queue to be processed by the worker nodes.

These nodes pull the messages from the queue and process them. If any error occurs, the node should retry a set number of times, « if the error persists, the message is sent to a dead-letter-queue where it should be analysed by an engineer.
