package com.balabala.entity;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class DetailDTO {

    private Item item;
    private File[] files;

}
