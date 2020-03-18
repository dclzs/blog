package plus.kuailefeizhaijidi.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import plus.kuailefeizhaijidi.blog.controller.BaseController;
import plus.kuailefeizhaijidi.blog.entity.Result;

import java.util.Map;

/**
 * @author dl
 * @since 2020年3月17日
 */
@Controller
public class PageController extends BaseController {

    private final RestTemplate restTemplate;

    public PageController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }

    @GetMapping("article/{articleId}")
    public ModelAndView article(@PathVariable("articleId") Long articleId) {
        Result result = restTemplate.getForObject(getRequestUrl(), Result.class);
        return new ModelAndView("post",newHashMap(getData(result)));
    }

    @GetMapping("category")
    public ModelAndView classify(){
        Result result = restTemplate.getForObject(getRequestUrl(), Result.class);
        return new ModelAndView("category", newHashMap("records", getData(result)));
    }

    @GetMapping({"/","/index"})
    public String index(){
        return "redirect:/article";
    }

    @GetMapping("article")
    public ModelAndView index(@RequestParam(value = "current", defaultValue = "1") Integer current,
                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        Result result = restTemplate.getForObject(getRequestUrl("current", "size"), Result.class, newHashMap("current", current, "size", size));
        Map<String, Object> model = newHashMap(getData(result));
        model.put("title", "肥 宅 基 地");
        return new ModelAndView("index", model);
    }

    @GetMapping("article/{categoryId}/category")
    public ModelAndView index(@PathVariable("categoryId") Long categoryId,
                              @RequestParam(value = "categoryName", required = false) String categoryName,
                              @RequestParam(value = "current", defaultValue = "1") Integer current,
                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        Result result = restTemplate.getForObject(getRequestUrl("current", "size"), Result.class, newHashMap("current", current, "size", size));
        Map<String, Object> model = newHashMap(getData(result));
        model.put("title", String.join(" ",categoryName.split("")));
        return new ModelAndView("index", model);
    }

}
