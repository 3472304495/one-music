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
 * 专辑信息表
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("albums")
@ApiModel(value="Albums对象", description="专辑信息表")
public class Albums implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专辑ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "专辑名称")
    private String title;

    @ApiModelProperty(value = "歌手ID")
    private Long artistId;

    @ApiModelProperty(value = "专辑封面URL")
    private String coverUrl;

    @ApiModelProperty(value = "发行日期")
    private LocalDate releaseDate;

    @ApiModelProperty(value = "专辑描述")
    private String description;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "流派")
    private String genre;

    @ApiModelProperty(value = "发行公司")
    private String company;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
