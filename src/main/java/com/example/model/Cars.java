package com.example.model;

import com.example.controller.AdditionServlet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cars
{
    public void addCar(Car car) throws IOException
    {
        JSONObject carJson = new JSONObject();

        carJson.put("brand", car.getBrand());
        carJson.put("model", car.getModel());
        carJson.put("year", car.getYear());
        carJson.put("mileage", car.getMileage());
        carJson.put("color", car.getColor());
        carJson.put("price", car.getPrice());

        // путь к файлу JSON
        String jsonPath = getJsonPath();

        // содержимое JSON
        String jsonContent = new String(Files.readAllBytes(Path.of(jsonPath)));

        // создаем JSONArray из строки JSON
        JSONArray jsonArray = new JSONArray(jsonContent);
        jsonArray.put(carJson);

        try (FileWriter writer = new FileWriter(jsonPath))
        {
            writer.write(jsonArray.toString(4));
        }

        /*try (FileWriter writer = new FileWriter("cars.json"))
        {
            writer.write(jsonArray.toString(4));
        }*/
    }

    private String getJsonPath()
    {
        String parentPath;
        try
        {
            parentPath = new File(AdditionServlet.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParent();
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }

        return parentPath + File.separator + "cars.json";
    }
}
