package com.usapi.parsers;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScheduleParser {

    private Document doc;

    public ScheduleParser(String url) throws IOException {
        this.doc = Jsoup.connect(url).postDataCharset("UTF-8").get();
    }
    public JsonElement parseSchedule() {
        Map<String, Map<String, List<Map<String, String>>>> weeklySchedule = new LinkedHashMap<>();
        weeklySchedule.put("Parzysty", new LinkedHashMap<>());
        weeklySchedule.put("Nieparzysty", new LinkedHashMap<>());



        // Selecting the table with the combined schedule for even and odd weeks
        Element combinedTable = doc.select("html body div:nth-of-type(2) div:nth-of-type(3) table tbody").first();
        if (combinedTable != null) {
            Elements rows = combinedTable.select("tr");

            // Skip the first two header rows
            for (int i = 2; i < rows.size(); i++) {
                Elements cells = rows.get(i).select("td");
                // The first cell in each row is the time slot
                String timeSlot = rows.get(i).select("th").text();

                // Each cell corresponds to a day, alternating between odd and even weeks
                for (int j = 0; j < cells.size(); j++) {
                    String weekTypeKey = (j % 2 == 0) ? "Nieparzysty" : "Parzysty";
                    String dayOfWeek = getDayOfWeek(j / 2);

                    String classInfo = cells.get(j).text();
                    if (!classInfo.isEmpty() && !classInfo.equals(" ")) { // Check for non-breaking space
                        Map<String, List<Map<String, String>>> daySchedule = weeklySchedule.get(weekTypeKey);
                        Map<String, String> classDetails = new LinkedHashMap<>();
                        classDetails.put("time", timeSlot);
                        classDetails.put("class", classInfo);

                        daySchedule.computeIfAbsent(dayOfWeek, k -> new ArrayList<>()).add(classDetails);

                    }
                }
            }
        }

        return new GsonBuilder().create().toJsonTree(weeklySchedule);
    }

    private String getDayOfWeek(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Poniedzia\u0142ek";
            case 1: return "Wtorek";
            case 2: return "\u015Aroda";
            case 3: return "Czwartek";
            case 4: return "Pi\u0105tek";
            default: return "Unknown";
        }
    }

    // Main method omitted as requested
}
