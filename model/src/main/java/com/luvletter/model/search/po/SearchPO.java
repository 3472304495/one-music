package com.luvletter.model.search.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("search_po")
@ApiModel(value="SearchPO对象", description="搜索结果表")
public class SearchPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "歌手ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long artistId;

    @ApiModelProperty(value = "歌手姓名")
    private String artistName;

    @ApiModelProperty(value = "歌手头像URL")
    private String artistAvatarUrl;

    @ApiModelProperty(value = "专辑ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long albumId;

    @ApiModelProperty(value = "专辑名称")
    private String albumTitle;

    @ApiModelProperty(value = "专辑封面URL")
    private String albumCoverUrl;

    @ApiModelProperty(value = "歌曲ID，主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long songId;

    @ApiModelProperty(value = "歌曲名称")
    private String songTitle;

    @ApiModelProperty(value = "歌曲封面URL")
    private String songCoverUrl;

    @ApiModelProperty(value = "播放次数")
    private Long songPlayCount;
}
