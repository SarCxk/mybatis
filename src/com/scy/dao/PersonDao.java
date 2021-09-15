package com.scy.dao;

import com.scy.bean.Person;
import com.scy.bean.dto.PersonDTO;
import io.swagger.models.auth.In;
import org.springframework.security.access.method.P;

import java.util.List;
import java.util.Map;

public interface PersonDao {
    //全查
    List<Person> selectAll();
    //根据性别查询
    List<Person> selectPersonBysex(int sex);//不支持带两个参数（int sex，String name）
    //查询总条数
    long selectCount();
    //查询总条数+多个参数第一种方式2个参数都是个person类中的属性，所以直接可以吧person当作参数
    long selectCountByParam01(Person person);
    //查性别和生日,当查询出的sql语句不确定是唯一的一条的时候，一定要用list
    //当多表联查的时候，请求的参数一定要为 map或者是自己写个实体类。应用场景，多表联查的多参数查询
    List<Person> selectCountByParam02(Map map);
    //1. 查询 分值最高的人是谁 ？
    List<Person> selectPersonByZi();
    //2. 男生和女生的平均分值是多少 ？
    List<PersonDTO> selectAvgScore();
    //男生女生的平均值大于200的是什么有参数
    List<PersonDTO> selectAvgScoreParam(int score);
    //男生女生的平均值大于200的是什么有参数
    List<Map> selectAvgScoreParam02(int score);
    //查询姓孙de第一种方式  不建议
    List<Person> selectPersonByLike(String name);
    ////查询姓孙de第二种方式
    List<Person> selectPersonByLike02(String name);
    ////查询姓孙de第二种方式
    List<Person> selectPersonByLike03(String name);

    //增加的方法
    int insertPerson(Person person);

    //删除的方法
    int deletePersonById(Integer id);//注意：之后讲解动态sql那么我们的
                                     // dao层接口中只有基础类型int,String不好的，不方便执行动态sql,
                                     //以后自己写代码参数一定是一个实体类，或者是个map，或者是DTO
    //动态sql
    List<Person> dongTaiSelect(Person person); //动态sql如果参数不是实体类，不是集合，是个空参数，那么没有任何意义
    //长成   返回值是List<实体类> 参数也是同样的实体类，那么这是一个典型的动态sql语句。。。
    //动态的一个修改
    int dongTaiUpdata(Person person);
    //批量删除(2,33,45,54)
    void piLiangDel(Map map);

    //1.对多，方法写在1方
    List<Person> selectOrdersByPersonId(Integer id);

    //1.把这个.改为.动态sql . , . .按id , 按name.都可以查询!!! !
    //作业2:写出2各表,city , -- 郑州...区表中原区，,营城区，.开发区。写出1对多的动念sql

    //1对多对多 适用于下拉框
    List<Person> selectDetailByPersonId(Integer id);

    //三表联查适用于数据表格 参数就是map！！双map 返回值和参数均为map  俗称万能查
    //适用于动态的sql查询   牛逼！！！！！
    List<Map> selectDetailByPersonParam(Map map);

    //多对多的查询   查询xxx都有哪些角色
    List<Person> selectRoleByName(String name);
}
