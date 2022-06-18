package com.carrito.app.temporizators;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TemporizatorCart {

    @Scheduled(cron = "0 56 19 * * *",zone = "America/Argentina/Buenos_Aires")
    public void eliminarCarts(){
        System.out.println("Ejecutando desde cron");
    }
}
