package com.example.team16project.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Errors {
    List<Error> arrayList = new ArrayList<Error>();
}
