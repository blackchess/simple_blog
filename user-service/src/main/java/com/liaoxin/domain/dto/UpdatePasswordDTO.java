package com.liaoxin.domain.dto;

import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.utils.SecretUtils;
import lombok.Data;

@Data
public class UpdatePasswordDTO {

    private Long userId;

    private String oldPassword;

    private String newPassword;

    public String getOldPasswordMD5() {
        return SecretUtils.MD5Encoding(this.oldPassword, WebConst.SECURT);
    }

    public String getNewPasswordMD5() {
        return SecretUtils.MD5Encoding(this.newPassword, WebConst.SECURT);
    }
}
