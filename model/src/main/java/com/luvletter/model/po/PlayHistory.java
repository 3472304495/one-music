package com.luvletter.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 用户播放历史表
 * </p>
 *
 * @author xiaohuai
 * @since 2025-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("play_history")
@ApiModel(value="PlayHistory对象", description="用户播放历史表")
public class PlayHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "歌曲ID")
    @TableField("song_id")
    private Long songId;

    @ApiModelProperty(value = "播放时间")
    @TableField("play_time")
    private LocalDateTime playTime;

    @ApiModelProperty(value = "播放时长（秒）")
    @TableField("play_duration")
    private Integer playDuration;

    @ApiModelProperty(value = "播放次数")
    @TableField("play_count")
    private Integer playCount;


}
