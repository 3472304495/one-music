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
 * 播放列表歌曲关联表
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("playlist_songs")
@ApiModel(value="PlaylistSongs对象", description="播放列表歌曲关联表")
public class PlaylistSongs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "播放列表ID")
    private Long playlistId;

    @ApiModelProperty(value = "歌曲ID")
    private Long songId;

    @ApiModelProperty(value = "排序顺序")
    private Integer sortOrder;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addedAt;


}
