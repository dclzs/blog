package plus.kuailefeizhaijidi.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import plus.kuailefeizhaijidi.blog.common.Constant;
import plus.kuailefeizhaijidi.blog.entity.Result;
import plus.kuailefeizhaijidi.blog.util.RemoteCallUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dl
 * @since 2020年3月17日
 */
@Controller
public class PageController extends BaseController {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public PageController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("article/{articleId}")
    public ModelAndView article(@PathVariable("articleId") Long articleId) {
        Result result = restTemplate.getForObject(RemoteCallUtils.getRequestUrl(), Result.class);
        Object data = RemoteCallUtils.getData(result);
        if(data == null){
            return ERROR_404;
        }
        return new ModelAndView("post", newHashMap(data));
    }

    @GetMapping("category")
    public ModelAndView classify() throws JsonProcessingException {
        Result result = restTemplate.getForObject(RemoteCallUtils.getRequestUrl(), Result.class);
        JSONArray array = JSON.parseArray(RemoteCallUtils.getJson(result));
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            Long categoryId = object.getLong("categoryId");
            String categoryName = object.getString("categoryName");
            CATEGORY_MAP.put(categoryId, String.join(Constant.SPACE, categoryName.split("")));
        }
        return new ModelAndView("category", newHashMap("records", RemoteCallUtils.getData(result)));
    }

    @GetMapping({"/","/index"})
    public String index(){
        return "redirect:/article";
    }

    @GetMapping("article")
    public ModelAndView index(@RequestParam(value = "current", defaultValue = "1") Integer current,
                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        Result result = restTemplate.getForObject(RemoteCallUtils.getRequestUrl("current", "size"), Result.class, newHashMap("current", current, "size", size));
        Map<String, Object> model = newHashMap(RemoteCallUtils.getData(result));
        model.put("title", Constant.NAME);
        model.put("index", true);
        model.put("current", current);
        return new ModelAndView("index", model);
    }

    @GetMapping("article/{categoryId}/category")
    public ModelAndView index(@PathVariable("categoryId") Long categoryId,
                              @RequestParam(value = "current", defaultValue = "1") Integer current,
                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        Result result = restTemplate.getForObject(RemoteCallUtils.getRequestUrl("current", "size"), Result.class, newHashMap("current", current, "size", size));
        Map<String, Object> model = newHashMap(RemoteCallUtils.getData(result));
        model.put("title", CATEGORY_MAP.get(categoryId));
        model.put("current", current);
        return new ModelAndView("index", model);
    }

    private static final Map<Long, String> CATEGORY_MAP = new HashMap<>();

}
