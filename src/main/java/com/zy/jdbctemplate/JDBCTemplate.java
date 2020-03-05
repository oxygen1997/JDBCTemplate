package com.zy.jdbctemplate;

import com.zy.pojo.Dept;
import com.zy.pojo.Employee;
import com.zy.util.JDBCUtil;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class JDBCTemplate {

    private  static JdbcTemplate jdbcTemplate = null;
    static{
        try {
            jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * update
     */
    @Test
    public void testUpdate1() {
        try {
            String updateByPrimaryKey = "update tbl_employee set last_name=?,gender=? where id=?";
            int count = jdbcTemplate.update(updateByPrimaryKey,"邢道荣",1,2);
            if(count>0){
                System.out.println("更新数据成功...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * insert
     */
    @Test
    public void testUpdate2() {
        try {
            String insert = "insert into tbl_employee(last_name,email,gender,dept_id,salary) values(?,?,?,?,?)";
            int count = jdbcTemplate.update(insert,"张辽","zl@meng.com",1,3,30000);
            if(count>0){
                System.out.println("添加数据成功...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * insert
     */
    @Test
    public void testUpdate3() {
        try {
            String delete = "delete from tbl_employee where id=?";
            int count = jdbcTemplate.update(delete,1);
            if(count>0){
                System.out.println("删除数据成功...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * select   list
     */
    @Test
    public void testQuery1(){
        String queryAll = "select * from tbl_employee";
        List<Map<String, Object>> lists = jdbcTemplate.queryForList(queryAll);
        for (Map map:lists) {
            System.out.println(map);
        }
    }
    /**
     * select   map  key为查询的列名，value为数据
     * queryForMap只能查询一条记录，查询多条记录报错
     * org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 11
     */
    @Test
    public void testQuery2(){
        String query = "select * from tbl_employee where id=? ";
        Map<String, Object> map = jdbcTemplate.queryForMap(query, 3);
        System.out.println(map);
    }

    /**
     * 查询所有记录，封装为存储Employee对象的List集合   自定义实现接口
     */
    @Test
    public void testQuery3(){
//        String sql = "select e.*,d.id as did,d.dept_name from tbl_employee e left join tbl_dept d on e.dept_id=d.id";
        String sql = "select e.*,d.id as did,d.dept_name from tbl_employee as e,tbl_dept as d where e.dept_id=d.id";
        List<Employee> list = jdbcTemplate.query(sql,new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int gender = resultSet.getInt("gender");
                int deptId = resultSet.getInt("dept_id");
                String deptName = resultSet.getString("dept_name");
                String employeeEnum = resultSet.getString("employee_enum");
                Double salary = resultSet.getDouble("salary");
                Employee employee = new Employee(id,lastName,gender,email,new Dept(deptId,deptName),salary,employeeEnum);
                return employee;
            }
        });
        for (Employee e:list) {
            System.out.println(e);
        }
    }

    /**
     * 查询所有记录，封装为存储Employee对象的List集合   使用spring提供的实现类
     */
    @Test
    public void testQuery4(){
//        String sql = "select e.*,d.id as did,d.dept_name from tbl_employee e left join tbl_dept d on e.dept_id=d.id";
        String sql = "select e.*,d.id as did,d.dept_name from tbl_employee as e,tbl_dept as d where e.dept_id=d.id";
        List<Employee> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
        for (Employee e:list) {
            System.out.println(e);
        }
    }

    /**
     * select   嵌套查询
     * 查询表中薪水最高的二人的所有数据信息
     */
    @Test
    public void testQuery5(){
        String query = "SELECT * FROM tbl_employee where id in(select t.id from (SELECT * from tbl_employee ORDER BY salary desc LIMIT 0,2) as t)";
        List<Map<String, Object>> lists = jdbcTemplate.queryForList(query);
        for (Map map:lists) {
            System.out.println(map);
        }
    }

}
