package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DepUpdate extends DepartmentRequest implements Serializable {
  /**
   * ユーザーID
   */

  private Long did;
}
