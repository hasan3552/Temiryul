package com.company;

import com.company.config.Config;
import com.company.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class App {

//    @Autowired
//    private static MainController mainController;

    public static void main(String[] args) {

        try {
            headerMethod();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void headerMethod() {

        try {

            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
            MainController mainController = (MainController) context.getBean("mainController");

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(mainController);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
