package com.codurance.corporatehotel.hotels.model;

import javax.validation.constraints.NotNull;

public class Hotel {

  @NotNull(message = "cannot be null")
  private Integer id;
  @NotNull(message = "cannot be null")
  private String name;

  public Hotel(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Hotel() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
