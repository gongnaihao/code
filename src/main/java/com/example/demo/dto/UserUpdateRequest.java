package com.example.demo.dto;


import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserUpdateRequest extends UserRequest implements Serializable {
  /**
   * ユーザーID
   */

  private Long id;
}
