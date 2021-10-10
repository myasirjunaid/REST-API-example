package com.example.rest.file.Controller;

import com.example.rest.file.Service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {
    FileService fileService = new FileService();

    @GetMapping("/list")
    public ResponseEntity<List<String>> getFiles(@RequestParam("folder") String folder) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getFiles(folder));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> create(@RequestParam String filename, @RequestParam String folderpath,
                                         @RequestParam String body) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.createFile(filename, folderpath, body));
    }
}
