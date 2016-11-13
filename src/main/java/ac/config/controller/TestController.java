package ac.config.controller;

import ac.config.bean.TestBean;
import ac.config.dao.TestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianq on 11/11/16.
 */

@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    @Qualifier("TestDaoMongo")
    TestDao testDao;
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> get(@PathVariable("name")String name, TestBean test) {
        LOGGER.info("test/get name = {}, test = {}", name, test);
        try {
            testDao.insert(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
