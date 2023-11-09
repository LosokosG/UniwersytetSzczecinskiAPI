package com.usapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.usapi.parsers.ScheduleParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
@SpringBootApplication
@ComponentScan(basePackages = "com.usapi")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        try {
            String url = "http://plany-student.wneiz.pl/planGrup?numerGrupy=C172";
            ScheduleParser parser = new ScheduleParser(url);
            JsonElement jsonSchedule = parser.parseSchedule();

            // Create a Gson instance with pretty printing
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Print the JsonElement in a pretty way
            System.out.println(gson.toJson(jsonSchedule));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}