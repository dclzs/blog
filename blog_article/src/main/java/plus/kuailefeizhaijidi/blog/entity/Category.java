package plus.kuailefeizhaijidi.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author dl
 * @since 2020-03-10
 */
@TableName("category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="Category对象", description="分类表")
public class Category extends BaseEntity<Category> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "category_id", type = IdType.ASSIGN_ID)
    private Long categoryId;

    @ApiModelProperty(value = "用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "user_id")
    private Long userId;

    @ApiModelProperty(value = "分类名称")
    @TableField(value = "category_name")
    private String categoryName;

    @ApiModelProperty(value = "分类描述")
    @TableField(value = "category_desc")
    private String categoryDesc;

    @ApiModelProperty(value = "显示状态")
    @TableField(value = "display_status", fill = FieldFill.INSERT)
    private Integer displayStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", userId=" + userId +
                ", categoryName=" + categoryName +
                ", categoryDesc=" + categoryDesc +
                ", displayStatus=" + displayStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
