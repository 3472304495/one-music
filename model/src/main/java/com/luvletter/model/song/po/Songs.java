package com.luvletter.model.song.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 歌曲信息表
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("songs")
@ApiModel(value="Songs对象", description="歌曲信息表")
public class Songs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "歌曲ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "歌曲名称")
    private String title;

    @ApiModelProperty(value = "歌手ID")
    private Long artistId;

    @ApiModelProperty(value = "专辑ID")
    private Long albumId;

    @ApiModelProperty(value = "歌曲时长（秒）")
    private Integer duration;

    @ApiModelProperty(value = "歌曲文件URL")
    private String fileUrl;

    @ApiModelProperty(value = "歌曲封面URL")
    private String coverUrl;

    @ApiModelProperty(value = "歌词")
    private String lyrics;

    @ApiModelProperty(value = "播放次数")
    private Long playCount;

    @ApiModelProperty(value = "下载次数")
    private Long downloadCount;

    @ApiModelProperty(value = "分享次数")
    private Long shareCount;

    @ApiModelProperty(value = "是否为VIP歌曲：0-免费，1-VIP")
    private Boolean isVip;

    @ApiModelProperty(value = "状态：0-下架，1-上架")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
