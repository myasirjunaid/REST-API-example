package com.example.rest.file.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileSaveException extends RuntimeException {

    private String message;

}
