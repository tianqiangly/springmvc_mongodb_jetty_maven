package ac.config.dao;

import ac.config.bean.T2Bean;
import ac.config.bean.TestBean;
import ac.config.controller.TestController;
import ac.config.utils.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by tianq on 11/11/16.
 */

@Repository("TestDaoMongo")
public class TestDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDao.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    public void insert(TestBean entity) throws Exception {
        //save函数根据参数条件,调用了insert或update函数:有则改之,无则加之
        this.mongoTemplate.insert(entity);

    }

    public void findAll() throws Exception {
        //save函数根据参数条件,调用了insert或update函数:有则改之,无则加之
        this.mongoTemplate.findAll(TestDao.class);
        LOGGER.info("findAll");
    }

    public void findAll(TestBean entity) throws Exception {
        //save函数根据参数条件,调用了insert或update函数:有则改之,无则加之
        this.mongoTemplate.findAll(TestDao.class);
    }

    public void update(TestBean entity) throws Exception {
        //save函数根据参数条件,调用了insert或update函数:有则改之,无则加之

        Query query = new Query(where("username").is(entity.getUsername()));
//        Update update = new Update();
//        update.set("userId",entity.getUserId());
//        update.set("t2",entity.getT2());

//        DBObject dbDoc = new BasicDBObject();
//        mongoTemplate.getConverter().write(entity, dbDoc); //it is the one spring use for convertions.
//        Update update = Update.fromDBObject(dbDoc, "_id", "_class");

        Update update = MongoUtils.updateFromObj(entity);
        WriteResult writeResult = this.mongoTemplate.updateFirst(query, update, TestBean.class);
    }

    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(false);
        context.load("classpath:*.xml");
        context.refresh();

        TestDao testDao = context.getBean(TestDao.class);
        TestBean t = new TestBean();
        t.setUsername("zapper2");
        t.setUserId(1243);
        T2Bean t2 = new T2Bean();
        t2.setUserId(1112111);
        t2.setUsername("za");
        //t.setT2(t2);
       // testDao.insert(t);
        testDao.update(t);
    }
}
