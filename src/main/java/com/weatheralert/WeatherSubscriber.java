package com.weatheralert;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class WeatherSubscriber {

    public static void main(String[] args) {
        Jedis subscriberJedis = new Jedis("localhost", 6379);
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Received message from " + channel + ": " + message);
            }
        };

        System.out.println("Subscribing to weather_alerts and weather_updates channels...");
        subscriberJedis.subscribe(jedisPubSub, "weather_alerts", "weather_updates");
    }
}
