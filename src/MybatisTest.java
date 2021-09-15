import com.scy.bean.*;
import com.scy.bean.dto.PersonDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MybatisTest {
    private SqlSession sqlSession;   //面试题：讲一下mybatis的执行流程
    private SqlSessionFactory sqlSessionFactory;
    @Before //在@Test注解之前，执行的方法。提取重复的代码的。
    public void before() throws Exception {
        //加载并读取xml
        String path="SqlMapConfig.xml";
        //import org.apache.ibatis.io.Resources;
        InputStream is= Resources.getResourceAsStream(path);
        //sql 链接的 工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession);
        //sqlSession = org.apache.ibatis.session.defaults.DefaultSqlSession@6fc6f14e
        //sqlSession.close();
    }
    //全查
    @Test
    public void test01(){
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectAll");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    @Test
    public void test02(){
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectPersonBysex",2);
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //查总条数，这个主要学习的是返回的数据类型，
    @Test
    public void test03(){
        long o = sqlSession.selectOne("com.scy.dao.PersonDao.selectCount");
        System.out.println("o = " + o);
        sqlSession.close();
    }
    //带参数查询
    @Test
    public void test04(){
        Person person = new Person();
        person.setScore(100);
        person.setGender(2);
        long o = sqlSession.selectOne("com.scy.dao.PersonDao.selectCountByParam01",person);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    //带参数查询第2种方式:map传参--多见于多表查询,
    @Test
    public void test05() throws ParseException {
        String date = "2020-10-14";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday =sf.parse(date);
        Map map = new HashMap();
        map.put("gender",2);//key一定要和#{gender}值保持一致
        map.put("birthday",birthday);//key一定要和#{gender}值保持一致
        List<Person> lists= sqlSession.selectList("com.scy.dao.PersonDao.selectCountByParam02",map);
        for (Person list : lists){
            System.out.println("list = " + list);
        }
        sqlSession.close();
    }
    //子查询
    @Test
    public void test06(){
        List<Person> lists= sqlSession.selectList("com.scy.dao.PersonDao.selectPersonByZi");
        for (Person list : lists){
            System.out.println("list = " + list);
        }
        sqlSession.close();
    }
    //分组查询
    @Test
    public void test07(){
        List<PersonDTO> personDTOS= sqlSession.selectList("com.scy.dao.PersonDao.selectAvgScore");
        for (PersonDTO personDTO: personDTOS){
            System.out.println("personDTOS = " + personDTOS);
        }
        sqlSession.close();
    }

    //分组查询+参数
    @Test
    public void test08(){
        List<PersonDTO> personDTOS= sqlSession.selectList("com.scy.dao.PersonDao.selectAvgScoreParam",200);
        for (PersonDTO personDTO: personDTOS){
            System.out.println("personDTOS = " + personDTOS);
        }
        sqlSession.close();
    }
    //分组查询+map接收
    @Test
    public void test09(){
        List<Map> maps = sqlSession.selectList("com.scy.dao.PersonDao.selectAvgScoreParam02", 200);
        for (Map map:maps){
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }

    //查询姓孙的 所以你不要用拼接的方式写$
    @Test
    public void test10(){
        Map map = new HashMap();
        map.put("name","孙");
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectPersonByLike",map);
        //There is no getter for property named 'name'
        //   因为$是拼接的,没有getter这个概念 #相当于问号,有getter概念
        //   List<Person> personList = sqlSession.selectList("com.yyh.dao.PersonDao.selectPersonByLike","孙");
        for (Person person:personList){
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //查询姓孙的
    @Test
    public void test11(){
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectPersonByLike02", "孙");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //查询姓孙的
    @Test
    public void test12(){
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectPersonByLike03","孙");
        for (Person person:personList){
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //以上就是单表的所有查询!!!. .看好这些例子，以后模仿去公司写
    //玩增加insert into。。。。
    @Test
    public void test13(){
        Person person = new Person();
        person.setName("蔡徐坤");
        person.setGender(1);
        person.setBirthday(new Date());
        person.setAddress("河南");
        person.setScore(666);
        int insert = sqlSession.insert("com.scy.dao.PersonDao.insertPerson", person);
        System.out.println("insert = " + insert);
        sqlSession.commit();
        sqlSession.close();
    }
    //删除
    @Test
    public void test14(){
        int i = sqlSession.delete("com.scy.dao.PersonDao.deletePersonById",19);
        System.out.println("i = " + i);
        sqlSession.commit();
        sqlSession.close();
    }

    //动态sql     重点，难点，高新的起点。
    //动态sql   其实就是让达到1条xml中的语句可以实现N多种查询。
    //那么，要实现多种查询，就有硬性的条件、你的参数要多
    //参数要多》》1.放弃单个属性（int,String）改用实体类  2.参数改用map
    //今天所学的推到昨天学的，那么就需要总结昨天所学的

    //第一类:..特征1）.返回值---->.正常表的结果集，对应的是 person 实体类
    //         特征2）.都是select * from person开头
    //1.1select * from person     if 如果. . . where .后面没参数.那么就是全查
    //1.2select * from person       if 如果. . .where.后面参数是gender. .那么就是单查gender
    //1.3select * from person where gender=#{gender} and birthday<#{birthday
    //1.4select * from person where name like "%"#{name}"%"
    //1-4可以.合N为1.， ..只需要把. where .后面的参数做个.if .判断

    //第二类:..特征.1)返回值-----》.一个数，.单行单列..非person实体类，.是.一个数据类型
    //特征2：都是select count(*) from person开头
    //2.1select count(*) from person
    //2.2select count(*) from person person where gender=2 and score>100

    //综上所述!!以上 sql可以.进行..动态判断形成一个sql ! !这就叫做动态sql 。

    //动态查询
    @Test
    public void test15(){
        Person person = new Person();
        //null就是全查
        //person.setId(16);//select*from person p WHERE p.id=?
        person.setScore(200);
        person.setGender(2);
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.dongTaiSelect",person);
        for (Person person1 : personList){
            System.out.println("person1 = " + person1);
        }
    }
    //动态修改，其实就是有选择性的修改多个字段，比如 可以修改女孩子的分数等等等
    @Test
    public void test16(){
        Person person = new Person();
        person.setId(17);
        person.setAddress("家里蹲");
        person.setBirthday(new Date());
        sqlSession.update("com.scy.dao.PersonDao.dongTaiUpdata",person);
        sqlSession.commit();
        sqlSession.close();
    }
    //批量删除  delete  xxx in(2,33,43,54)
    //构造1个ids(1,2,3,4)
    @Test
    public void test17(){
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        Map map = new HashMap();
        map.put("ids",idList);
        int delete = sqlSession.delete("com.scy.dao.PersonDao.piLiangDel",map);
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }

    //以上代码不用手写，因为谁写谁垃圾
    //xml不需要你写！！！Dao不需要你写
    //但是需要你能看的懂
    //这是重点，逆向生成，公司都用
    //没有写一行代码 但是已经动态的查
    @Test
    public void test18(){
        //select count(*) from human
        //select count(*) from human where gender=2
        // select count(*) from human where gender = 2 and address="西京"
        HumanExample example = new HumanExample();//创建一个例子类
        HumanExample.Criteria criteria = example.createCriteria();//用例子类实现查询的规则或者标准
        // criteria.andAddressEqualTo("北京");
        // criteria.andScoreEqualTo(222.00);
        // criteria.andGenderEqualTo(2);   // select count(*) from human WHERE ( gender = ? )
        // criteria.andAddressEqualTo("西京") ; // select count(*) from human where gender = 2 and address like "%西京%"
        // 案例：  查询 地址 是 西京的人 有几个？
        // criteria.andGenderEqualTo(2);
        //  criteria.andAddressLike("%"+"西京"+"%"); // select count(*) from human WHERE ( gender = ? and address like ? )
        // 练习：  查询 家住在北京的 或者  分数是 222 的 人 有几个 ？
        // select * from human where address="北京"  or  score=555
        //example.or().andAddressEqualTo("北京");  // or 不要 criteria类
        // example.or().andScoreEqualTo(222.0); // select count(*) from human WHERE ( address = ? ) or( score = ? )
        // select * from human where id=1  or id=4 or id =5
        // 等于  select * from human where id in (1,2,4)   // select count(*) from human WHERE ( id in ( ? , ? , ? ) )
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(4);
        criteria.andIdIn(ids);
        //当example的criteria不用赋值的时候，则是。。Preparing: select count( * ) from  hunan
        long o = sqlSession.selectOne("com.scy.dao.HumanDAO.countByExample",example);
        System.out.println("o = " + o);
        sqlSession.close();
    }
    // 单表的所有
    // 查询：
    //  select * from human;  --- 全查
    //  select * from human where gender =2
    // 作业1： 把下面的 测试了 ！！！
    //  select * from human where gender =1
    //  select * from human where id = 1
    //  select * from human where score < 80
    //  select * from human where score > 80   and gender =1
    //  select * from human where score > 80   and gender =1 and address like “%郑州%”
    //  以上所有的 sql 语句 需要写成  1  个。
    //   List<Human> selectByExample(HumanExample example);
    @Test
    public void test19(){
        HumanExample example = new HumanExample();
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample",example);
        for (Human human:humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    @Test
    public void test19_01(){
        // select * from human where gender =2  带的gender 的参数
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        criteria.andGenderEqualTo(2);
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample",example);
        for (Human human:humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    @Test
    public void test19_02(){
        // select * from human where gender =1 带的gender 的参数
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        criteria.andGenderEqualTo(1);
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample",example);
        for (Human human:humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    @Test
    public void test19_03(){
        //select * from human where id = 1
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1);
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample",example);
        for (Human human:humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    @Test
    public void test19_04(){
        //  select * from human where score > 222
        //  select * from human where score < 222
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        //criteria.andScoreGreaterThan(222);//andScoreGreaterThan大于
        criteria.andScoreLessThan((double) 222);//andScoreLessThan小于
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample",example);
        for (Human human:humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }

    @Test
    public void test19_05(){
        //  select * from human where score < 222   and gender =1
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        //criteria.andScoreLessThan((double) 222);
        criteria.andScoreGreaterThan(222);
        criteria.andGenderEqualTo(1);
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample",example);
        for (Human human:humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    @Test
    public void test19_06(){
    //  select * from human where score > 222   and gender =1 and address like “%东京%”
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        //criteria.andScoreLessThan((double) 222);
        criteria.andScoreGreaterThan(222);
        criteria.andGenderEqualTo(1);
        criteria.andAddressLike("%"+"西京"+"%");
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample",example);
        for (Human human:humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    //工具的增加
    @Test
    public void test20(){
        Human human = new Human();
        human.setName("小鲁班");
        human.setAddress("王者峡谷");
        int insert = sqlSession.insert("com.scy.dao.HumanDAO.insertSelective", human);
        System.out.println("insert = " + insert);
        sqlSession.commit();
        sqlSession.close();
    }
    //工具的删除
    @Test
    public void test21(){
        //delete from human where id = ?
        int delete = sqlSession.delete("com.scy.dao.HumanDAO.deleteByPrimaryKey", 8);
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }
    //条件删除
    //1.删除所有的女生！ 2.删除分数小于20的  3.删除名字带有李的人   4.删除女生并且分数小于20的
    //删除女生并且分数小于20的并且名字带李的人
    @Test
    public void test22(){
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        criteria.andGenderEqualTo(2);
        criteria.andScoreLessThan(20.0);
        criteria.andNameLike("%"+"李"+"%");
        int delete = sqlSession.delete("com.scy.dao.HumanDAO.deleteByExample",example);
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }
    //按主键修改一个对象
    //吧悟空改成八戒
    @Test
    public void test23(){
        Human human = new Human();
        human.setId(4);
        human.setName("八戒jie");
        //DEBUG - ==>  Preparing: update human SET `name` = ? where id = ?
        //int update = sqlSession.update("com.scy.dao.HumanDAO.updateByPrimaryKeySelective", human);
        int update = sqlSession.update("com.scy.dao.HumanDAO.updateByPrimaryKey", human);
        System.out.println("update = " + update);
        sqlSession.commit();
        sqlSession.close();
    }
    //批量的动态修改--
    //测试不了运行不了
    @Test
    public void test24(){
        Human human = new Human();
        human.setScore(666.0);
        HumanExample humanExample = new HumanExample();
        HumanExample.Criteria criteria = humanExample.createCriteria();
        criteria.andScoreGreaterThan((int) 222.0);

        //sqlSession.update("com.scy.dao.HumanDAO.updateByExampleSelective", humanExample);
    }
    //按主键id查询
    @Test
    public void test25(){
        Human o = sqlSession.selectOne("com.scy.dao.HumanDAO.selectByPrimaryKey",4);
        System.out.println("o = " + o);
        sqlSession.close();
    }
    //动态查询
    //1.查询分数大于100的人    select id, `name`, gender, birthday, address, score from human WHERE ( score > ? )
    //2.查询分数大于100的人并且生日大于2020-10-10的人
    //select id, `name`, gender, birthday, address, score from human
    // 2.查询分数大于100的人并且生日大于2020-10-10的人并且是女生的人
    //select id, `name`, gender, birthday, address, score from human WHERE ( gender = ? )
    @Test
    public void  test26() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2020-10-10");
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        //criteria.andScoreGreaterThan(100);
        //simpleDateFormat.parse("2020-10-10");
        criteria.andGenderEqualTo(2);
        List<Human> humans  = sqlSession.selectList("com.scy.dao.HumanDAO.selectByExample", example);
        for (Human human : humans){
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    //1对多
    @Test
    public void test27(){
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectOrdersByPersonId");
        for (Person person:personList){
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //1对多对多
    @Test
    public void test28(){
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectOrdersByPersonId",1);
        for (Person person:personList){
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //三表联查平面的.....这个关系.没有.1对.对多.概念了，.只有关联关系!!﹒.平面的.结构
    @Test
    public void test29(){
        Map map = new HashMap();
        map.put("id",1);
        List<Map> selectList = sqlSession.selectList("com.scy.dao.PersonDao.selectDetailByPersonParam", map);
        System.out.println("selectList = " + selectList.size());
        for (Map map1 : selectList) {

            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }
    //多对1反向  注意：实体类中多对一
    @Test
    public void test30(){
        Orders o = sqlSession.selectOne("com.scy.dao.OrdersDAO.selectPersonByOrdersId",2);
        System.out.println("o = " + o);
        sqlSession.close();
    }
    //1vs1    可以看做简单的多对1
    //多对多 ----可以看作是带中间
    @Test
    public void test31(){
        List<Person> personList = sqlSession.selectList("com.scy.dao.PersonDao.selectRoleByName");
        for(Person person:personList){
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //1.1. 对 account 表做   逆向生成
    @Test
    public void test001(){

    }

    //2. 写出  account 表的  1） 按主键id查询的
    @Test
    public void test002(){
        Account o = sqlSession.selectOne("com.scy.dao.AccountDAO.selectByPrimaryKey",1);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    //3. 测试出  按 账户名查询的 测试类
    //4. 测试出   用户名和密码 登录的测试类
}