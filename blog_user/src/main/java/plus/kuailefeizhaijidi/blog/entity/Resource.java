package plus.kuailefeizhaijidi.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author dl
 * @since 2020-04-04
 */
@ApiModel(value="Resource对象", description="资源表")
public class Resource extends Model<Resource> {

    private static final long serialVersionUID=1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "资源ID")
    private Long resourceId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "资源key")
    private String resourceKey;

    @ApiModelProperty(value = "资源hash值")
    private String resourceHash;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "上传时间")
    private LocalDateTime createTime;


    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String getResourceHash() {
        return resourceHash;
    }

    public void setResourceHash(String resourceHash) {
        this.resourceHash = resourceHash;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.resourceId;
    }

    @Override
    public String toString() {
        return "Resource{" +
        "resourceId=" + resourceId +
        ", userId=" + userId +
        ", resourceKey=" + resourceKey +
        ", resourceHash=" + resourceHash +
        ", resourceName=" + resourceName +
        ", createTime=" + createTime +
        "}";
    }
}
