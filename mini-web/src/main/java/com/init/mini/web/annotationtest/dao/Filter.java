package com.init.mini.web.annotationtest.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Table("filter")
public class Filter {

    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("age")
    private Integer age;

    @Column("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 拼接 SQL
     */
    public String appendSQL(Filter filter) throws Exception {
        String sql = "select * from ";
        StringBuilder stringBuilderSQL = new StringBuilder(sql);
        Class clazz = filter.getClass();
        boolean isExist = clazz.isAnnotationPresent(Table.class);
        if (isExist) {
            Table table = (Table)clazz.getAnnotation(Table.class);
            String tableName = table.value();
            stringBuilderSQL.append(tableName).append(" where 1=1 ");
        }

        /**
         * getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
         * getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，
         * 但是不包括父类的申明字段。
         *
         * 同样类似的还有getConstructors()和getDeclaredConstructors()、getMethods()
         * 和getDeclaredMethods()，这两者分别表示获取某个类的方法、构造函数。
         */
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isFExist = field.isAnnotationPresent(Column.class);
            if (!isExist) {
                continue;
            }
            Column column = (Column) field.getAnnotation(Column.class);
            // 列名
            String columnName = column.value();
            // 属性名
            String fieldName = field.getName();
            // 拼接属性 getter 方法
            String getMethodName = "get"+fieldName.substring(0, 1)
                    .toUpperCase()+fieldName.substring(1);
            // 获取方法对象
            Method getMethod = clazz.getMethod(getMethodName);
            // 反射执行 getMethod 获取属性值
            // 反射方法执行的参数是对象
            Object fieldValue = getMethod.invoke(filter);
            if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue==0) ) continue;

            stringBuilderSQL.append(" and ").append(fieldName);
            if (fieldValue instanceof String) {
                if (((String) fieldValue).contains(",")) {
                    String[] ins = ((String) fieldValue).split(",");
                    stringBuilderSQL.append(" in (");
                    for (String in : ins) {
                        stringBuilderSQL.append("'").append(in).append("',");
                    }
                    stringBuilderSQL.deleteCharAt(stringBuilderSQL.length()-1);
                    stringBuilderSQL.append(")");
                } else {
                    stringBuilderSQL.append(" = ").append("'").append(fieldValue).append("'");
                }
            } else if (fieldValue instanceof Integer) {
                stringBuilderSQL.append(" = ").append(fieldValue);
            }
        }
        return stringBuilderSQL.toString();
    }

    public static void main(String[] args) throws Exception {
        Filter filter = new Filter();
        filter.setName("w2");
        filter.setAge(18);
        filter.setEmail("a@qq.com,b@qq.com");
        // 使用反射技术获取注解的配置信息（表名、列名、属性、属性值（可以封装性获取也可以反射执行 getter 方法）），组装 SQL
        String sql = filter.appendSQL(filter);
        System.out.println(sql);
    }
}
