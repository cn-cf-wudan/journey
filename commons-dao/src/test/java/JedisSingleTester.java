import org.journey.dao.redis.achieve.IRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wudan-mac
 * @ClassName: JedisSingleTester
 * @ClassNameExplain:
 * @Description:
 * @date 16/5/18 下午8:00
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/config/spring-redis-single.xml"}) //加载配置文件
public class JedisSingleTester {

    @Resource
    IRedisDao redisDao;

    @Test
    public void single() throws Exception {
        /*for (int i=0;i<10000;i++){
            System.out.println(redisDao.set("key:"+i, "啊"+i));
        }*/
        System.out.println(redisDao.flushAll());

    }
}
