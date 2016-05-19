import org.journey.dao.redis.achieve.IRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * @author wudan-mac
 * @ClassName: JedisClusterTester
 * @ClassNameExplain:
 * @Description:
 * @date 16/5/16 下午6:04
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/config/spring-redis-cluster.xml"}) //加载配置文件
public class JedisClusterTester {

    @Resource
    IRedisDao redisDao;

    @Test
    public void cluster() throws Exception {

        //System.out.println(redisDao.flushDB());

       //DEUser deUser = new DEUser();
        /*deUser.setId(1);
        deUser.setUserName("吴丹 小");
        deUser.setCreateTime(new Date());
        deUser.setCurrentAccount(200d);*/

        //System.out.println(redisDao.bset(deUser));

        //deUser = redisDao.bget(1 + "", DEUser.class);

       //System.out.println(new Gson().toJson(deUser));

        //System.out.println(redisDao.set("111", "2222"));
        //System.out.println(redisDao.bincrBy(DEUser.class, 1+"", DEUser.VERSION_FIELD_NAME, 1l));

        //System.out.println(redisDao.bincrByFloat(DEUser.class, 1+"", DEUser.CURRENT_ACCOUNT_FIELD_NAME, 1d));

        /*for (int i = 1; i <= 10000; i++) {

            long start = System.currentTimeMillis();
            redisDao.set("key:" + i, "value" + i);
            System.out.println("k:" + i);
            System.out.print("set " + i +"th value in " + (System.currentTimeMillis() - start) + " ms");
            start = System.currentTimeMillis();
            redisDao.get("key:" + i);
            System.out.println(", get " + i +"th value in " + (System.currentTimeMillis() - start) + " ms");
        }*/

    }

}
