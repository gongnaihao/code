package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * ユーザー情報 リクエストデータ
 */
@Data
public class UserRequest implements Serializable {
	@NotEmpty(message="ユーザーIDを入力されていません")
	private String userid;
	@NotEmpty(message="ユーザー名を入力されていません")
	 private String name;
	private String password;
    /**
     * 名前
     */


    /**
     * 住所
     */
    private String address;

    /**
     * 電話番号
     */
    @NotEmpty(message="電話番号を入力されていません")
    private String phone;

    @DateTimeFormat(pattern="yyyy/MM/dd")
    @NotNull(message="yyyy/MM/ddで入力してください。")
    private Date day;
}