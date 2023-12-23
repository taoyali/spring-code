package com.tyl.pojo;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class User {

    @NotNull
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    @NotNull
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;

    @NotNull
    @Email
    private String email;

    @URL
    private String userPic;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
