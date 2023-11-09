package com.usapi.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.usapi.parsers.ScheduleParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class ScheduleController {

    @GetMapping("/api/groupschedule/{groupNumber}")
    public ResponseEntity<String> getGroupSchedule(@PathVariable String groupNumber) {
        try {
            String url = "http://plany-student.wneiz.pl/planGrup?numerGrupy=" + groupNumber;
            ScheduleParser parser = new ScheduleParser(url);
            JsonElement schedule = parser.parseSchedule();

            // Serialize using Gson
            Gson gson = new Gson();
            String json = gson.toJson(schedule);

            return ResponseEntity.ok(json);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/api/fieldschedule/{field}/{year}/{degree}")
    public ResponseEntity<String> getGroupSchedule(@PathVariable String field, @PathVariable String year, @PathVariable String degree) {
        try {
            String url = "http://plany-student.wneiz.pl/planKierunek?Kieruek=Informatyka+w+Biznesie+%28IwB%29&rok=1+rokI";


            ScheduleParser parser = new ScheduleParser(url);
            JsonElement schedule = parser.parseSchedule();

            // Serialize using Gson
            Gson gson = new Gson();
            String json = gson.toJson(schedule);

            return ResponseEntity.ok(json);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


}