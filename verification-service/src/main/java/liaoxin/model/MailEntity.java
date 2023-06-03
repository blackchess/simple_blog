package liaoxin.model;

import lombok.Data;

import java.io.File;
import java.util.Map;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/19
 **/
@Data
public class MailEntity {
    /**
     * 接受用户
     */
    private String sendTo;
    /**
     * 主题
     */
    private String subject;
    /**
     * 信息内容
     */
    private String text;
    /**
     * 附件文件
     */
    private Map<String, File> attach;

    /**
     * 邮件类型（1：simple 2：HTML 3：attach）
     */
    private String kind;

}
