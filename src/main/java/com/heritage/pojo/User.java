package com.heritage.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heritage.model.AccountEntity;
import com.heritage.util.Heritageconstants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.heritage.util.Heritageconstants.SHOULD_BE_GREATERTHAN_0;
import static com.heritage.util.Heritageconstants.SHOULD_NOT_BE_NULL;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:08 PM
 */

@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class User {


    @Min(value = 1, message = SHOULD_BE_GREATERTHAN_0)
    private Long id;
    @NotBlank(message = SHOULD_NOT_BE_NULL)
    private String firstName;
    @NotBlank(message = SHOULD_NOT_BE_NULL)
    private String lastName;
    @NotBlank(message = SHOULD_NOT_BE_NULL)
    private String userName;
    @NotBlank(message = SHOULD_NOT_BE_NULL)
    private String password;
    private Character active;
    private Character isAdmin;
    private List<AccountEntity> accountEntities;
}
