package com.codurance.corporatehotel.companies.exception;

public class CompanyExistsException extends RuntimeException {

  public CompanyExistsException() {
    super("A company with given identifier already exists on the system.");
  }

}
