package plus.kuailefeizhaijidi.blog.controller;


import com.qiniu.storage.model.DefaultPutRet;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Resource;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.service.IResourceService;
import plus.kuailefeizhaijidi.blog.util.QiniuUtils;

import java.io.IOException;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author dl
 * @since 2020-04-04
 */
@Api(tags = "资源接口")
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private final IResourceService resourceService;

    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ApiOperation(value = "上传资源", notes = "返回访问路径")
    @PostMapping("/singleFileUpload")
    public Result singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Claims claims = getClaims();
        if (!file.isEmpty()) {
            DefaultPutRet putRet = QiniuUtils.me().upload(file.getBytes());
            if (putRet != null) {
                if(resourceService.isExists(putRet.hash)){
                    return Result.success(Constant.RESOURCE_HOST + Constant.BACKSLASH + putRet.key);
                }
                Resource resource = new Resource();
                resource.setUserId(Long.valueOf(claims.getId()));
                resource.setResourceHash(putRet.hash);
                resource.setResourceKey(putRet.key);
                resource.setResourceName(file.getOriginalFilename());
                if(resourceService.save(resource)) {
                    return Result.success(Constant.RESOURCE_HOST + Constant.BACKSLASH + putRet.key);
                }
            }
        }

        return Result.fault();
    }

}

