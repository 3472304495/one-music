package com.luvletter.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 播放列表表
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("playlists")
@ApiModel(value="Playlists对象", description="播放列表表")
public class Playlists implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "播放列表ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "播放列表名称")
    private String name;

    @ApiModelProperty(value = "播放列表描述")
    private String description;

    @ApiModelProperty(value = "播放列表封面URL")
    private String coverUrl;

    @ApiModelProperty(value = "是否公开：0-私有，1-公开")
    private Boolean isPublic;

    @ApiModelProperty(value = "歌曲数量")
    private Integer songCount;

    @ApiModelProperty(value = "播放次数")
    private Long playCount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
