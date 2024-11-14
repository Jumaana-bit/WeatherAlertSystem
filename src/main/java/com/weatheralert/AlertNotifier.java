package com.weatheralert;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class AlertNotifier {
    private static final String REDIS_CHANNEL = "weather_alerts";
    private Jedis jedis;

    public AlertNotifier() {
        // Creating a separate Jedis instance for subscribing
        this.jedis = new Jedis("localhost", 6379);
    }

    public void startListening() {
        // Subscribe to the weather_alerts channel in a separate thread
        new Thread(() -> {
            JedisPubSub jedisPubSub = new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    if (channel.equals(REDIS_CHANNEL)) {
                        sendNotification(message);
                    }
                }
            };

            // Subscribe to the channel
            jedis.subscribe(jedisPubSub, REDIS_CHANNEL);
        }).start();
    }

    public void sendNotification(String message) {
        // Simulate sending a notification (e.g., email, SMS)
        System.out.println("ALERT: " + message);
    }

    public static void main(String[] args) {
        AlertNotifier notifier = new AlertNotifier();
        notifier.startListening();
    }
}


