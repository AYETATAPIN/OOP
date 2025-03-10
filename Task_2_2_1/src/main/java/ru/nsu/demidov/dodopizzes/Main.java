package ru.nsu.demidov.dodopizzes;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Main class.
 */

public class Main {

    /**
     * main method.
     */

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PizzeriaConfig config = objectMapper.readValue(new File("config.json"),
                PizzeriaConfig.class);
            List<BakerConfig> bakers = config.getBakers();
            List<CourierConfig> couriers = config.getCouriers();

            int warehouseCapacity = config.getWarehouseCapacity();
            int[] bakerSpeeds = bakers.stream().mapToInt(BakerConfig::getSpeed).toArray();
            int[] courierCapacities =
                couriers.stream().mapToInt(CourierConfig::getCapacity).toArray();
            int[] courierSpeeds =
                    couriers.stream().mapToInt(CourierConfig::getSpeed).toArray();

            Pizzeria pizzeria = new Pizzeria(bakers.size(), couriers.size(), warehouseCapacity,
                bakerSpeeds, courierCapacities, courierSpeeds);
            for (int i = 1; i < 11; i++) {
                pizzeria.placeOrder(new Order(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pizzeria.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}