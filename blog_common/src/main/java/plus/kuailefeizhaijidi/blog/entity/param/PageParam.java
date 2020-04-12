package plus.kuailefeizhaijidi.blog.entity.param;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dl
 * @since 2020年4月3日
 */
public class PageParam {

    @ApiModelProperty(value = "当前页")
    private Long current;
    @ApiModelProperty(value = "数量")
    private Long size;
    @ApiModelProperty(value = "搜索词")
    private String keyword;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "开始日期")
    private String startDate;
    @ApiModelProperty(value = "结束日期")
    private String endDate;

    public PageParam() {
        this.size = 10L;
        this.current = 1L;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartDate() {
        if(StringUtils.isNotEmpty(startDate)) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(startDate)));
        }
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        if(StringUtils.isNotEmpty(endDate)) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(endDate)));
        }
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "size=" + size +
                ", current=" + current +
                ", keyword='" + keyword + '\'' +
                ", status=" + status +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
