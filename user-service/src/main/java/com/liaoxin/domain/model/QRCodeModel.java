package com.liaoxin.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/13
 **/
@Data
public class QRCodeModel implements Serializable {

    /** 唯一标识 */
    private String uuid;
    /** 用户ID */
    private Long userid;
    /** 用户主键 */
    private LocalDateTime createTime;
    /** 状态标识 */
    private QRStatus status;

    /** 状态枚举类 */
    public enum QRStatus {
        EXPIRE,
        UNCHECK,
        CHECKED
    }
}
