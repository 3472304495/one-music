package com.luvletter.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 歌手信息表
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("artists")
@ApiModel(value="Artists对象", description="歌手信息表")
public class Artists implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "歌手ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "歌手姓名")
    private String name;

    @ApiModelProperty(value = "英文名")
    private String englishName;

    @ApiModelProperty(value = "歌手简介")
    private String description;

    @ApiModelProperty(value = "歌手头像URL")
    private String avatarUrl;

    @ApiModelProperty(value = "背景图片URL")
    private String backgroundUrl;

    @ApiModelProperty(value = "国籍")
    private String country;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthDate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
