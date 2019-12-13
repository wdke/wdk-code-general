package com.wdk.general.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * created by wdk on 2019/12/13
 */
@Data
public class ProjectMetadata implements Serializable {

    private String group;

    private String artifact;

    private String type;

    private String language;

    private String packaging;

    private String javaVersion;

    private String version;

    private String name;

    private String description;

    private String packages;




}
