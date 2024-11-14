package com.weatheralert;

import redis.clients.jedis.Jedis;

public class WeatherAlertSystem {

    public static void main(String[] args) {
        // Get Redis connection
        Jedis jedis = RedisConnectionManager.getJedisConnection();

        // Start the WeatherFetcher to fetch weather data and publish alerts
        WeatherFetcher fetcher = new WeatherFetcher(jedis);
        fetcher.startFetching();

        // Start the AlertNotifier to listen for alerts and send notifications
        AlertNotifier notifier = new AlertNotifier();
        notifier.startListening();
    }
}

