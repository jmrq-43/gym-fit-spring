package com.gym_fit;

import com.gym_fit.gui.GymFitForm;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(ZonaFitSwing.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);
        SwingUtilities.invokeLater(() -> {
            GymFitForm gymFitForm = applicationContext.getBean(GymFitForm.class);
            gymFitForm.setVisible(true);
        });
    }
}
