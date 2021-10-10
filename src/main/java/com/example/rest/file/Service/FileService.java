package com.example.rest.file.Service;

import com.example.rest.file.Exception.FileSaveException;
import com.google.gson.JsonParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    //return sorted files path and names
    public List<String> getFiles(String folderName) {
        Path path = Paths.get(folderName);
        try (Stream<Path> stream = Files.walk(path, Integer.MAX_VALUE)) {
            List<String> result = stream
                    .map(String::valueOf).filter(f -> f.endsWith(".json"))
                    .sorted()
                    .collect(Collectors.toList());

            result.sort(Comparator
                            .comparing(s -> s.substring(s.lastIndexOf("\\") + 1)));

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //create json file in a specific folder with a request body
    public String createFile(String file, String folderName, String body) {
        try {
            if (isValidJson(body)){
                Path newfile = Paths.get(folderName ,file);
                writeFile(newfile.toString(),body );
                return "File successfully created !!";}
            else
                return "Not a valid Json";
        } catch (IOException e) {
            throw new FileSaveException("Could not create file");
        }
    }

    //write in file the main body received
    public void writeFile(String file, String body) throws IOException {
        FileWriter filewriter = new FileWriter(file);
        filewriter.write(body);
        if (filewriter != null) {
            filewriter.close();
        }
    }

    //check whether JSON is valid
    public boolean isValidJson(String body) {
        try {
            new JSONObject(body);
        } catch (JSONException e) {
            try {
                new JSONArray(body);
            } catch (JSONException x) {
                return false;
            }
        }
        return true;
    }
}

