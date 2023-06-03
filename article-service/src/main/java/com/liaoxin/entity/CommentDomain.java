package com.liaoxin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/13
 * @Description: 文章评论表
 **/
@Data
@TableName("cms_comment")
public class CommentDomain implements Serializable {

    /** 评论主键 */
    private Long id;
    /** 文章主键 */
    private Long articleId;
    /** 用户主键 */
    private Long userId;
    /** 父评论主键 */
    private Long parentId;
    /** 评论内容 */
    private String content;
    /** 点赞数 */
    private Integer likeNum;
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
    /** 版本 */
    private Integer version;
    /** 状态 */
    private Integer status;

}
