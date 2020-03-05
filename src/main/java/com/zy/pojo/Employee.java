package com.zy.pojo;


import java.io.Serializable;

public class Employee implements Serializable {

    private Integer id;
    private String lastName;
    private int gender;
    private String email;
    //设置外键关联tbl_dept.id <-------> tbl_employee.dept_id时，外键的属性和长度都要一致
    private Dept dept;
    private Double salary;
    private String employee_enum;
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEmployee_enum() {
        return employee_enum;
    }

    public void setEmployee_enum(String employee_enum) {
        this.employee_enum = employee_enum;
    }




    public Employee() {
    }

    public Employee( String lastName, int gender, String email, Dept dept) {
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
    }

    public Employee(Integer id, String lastName, int gender, String email, Dept dept, Double salary, String employee_enum) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
        this.salary = salary;
        this.employee_enum = employee_enum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", employee_enum='" + employee_enum + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

}

