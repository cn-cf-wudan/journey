import org.journey.dao.redis.achieve.IRedisDao;
import org.journey.po.demo.share.DEUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wudan-mac
 * @ClassName: JedisSentinelTester
 * @ClassNameExplain:
 * @Description:
 * @date 16/5/20 上午10:40
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/config/spring-redis-sentinel.xml"}) //加载配置文件
public class JedisSentinelTester {

    @Resource
    IRedisDao redisDao;

    @Test
    public void single() throws Exception {
        /*for (int i=0;i<10000;i++){
            System.out.println(redisDao.set("key:"+i, "啊"+i));
        }*/
        // System.out.println(redisDao.flushAll());

        DEUser deUser = new DEUser();
        deUser.setId(2);
        deUser.setUserName("吴丹 大");
        deUser.setCreateTime(new Date());
        deUser.setCurrentAccount(200d);

        System.out.println(redisDao.bset(deUser));

        /*deUser = redisDao.bget(1+"", DEUser.class);
        System.out.println(deUser);*/

        // System.out.println(redisDao.bincrBy(DEUser.class, 1+"", DEUser.VERSION_FIELD_NAME, 1l));

        // System.out.println(redisDao.bincrByFloat(DEUser.class, 1+"", DEUser.CURRENT_ACCOUNT_FIELD_NAME, 1d));

    }
}
