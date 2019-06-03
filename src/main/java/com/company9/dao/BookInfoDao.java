package com.company9.dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public class BookInfoDao {
    private JdbcTemplate jdbcTemplate;
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate=(JdbcTemplate)context.getBean("jdbcTemplate");
    }
    @org.junit.Test
    public void insert(String bookno,String bookname,String author,String sort)
    {
        if(query(bookno)){
         String sql="update bookinfo set amount=amount+1 where bookno=?" ;
            jdbcTemplate.update(sql,new Object[]{bookno});
            System.out.println("添加成功");
        }
        else{
           String sql="insert into bookinfo(bookno,bookname,author,sort,amount) values(?,?,?,?,1)";
            jdbcTemplate.update(sql,new Object[]{bookno,bookname,author,sort});
            System.out.println("添加成功");
        }
    }
    @org.junit.Test
    public void delete( String bookno){
        if(query(bookno)){
            String sql="update bookinfo set amount=amount-1 where bookno=?";
            jdbcTemplate.update(sql,new Object[]{bookno});
            System.out.println("删除成功");
        }else{
            System.out.println("查无此书");
        }

    }
    @org.junit.Test
    public void updateBookName( String bookno,String newbookname){
        if(query(bookno)){
            String sql="update bookinfo set bookname=? where bookno=?";
            jdbcTemplate.update(sql,new Object[]{newbookname,bookno});
            System.out.println("修改成功");
        }else{
            System.out.println("查无此书");
        }
    }
    @org.junit.Test
    public void updateAuthor(String bookno,String newauthor){
        if(query(bookno)){
            String sql="update bookinfo set author=? where bookno=?";
            jdbcTemplate.update(sql,new Object[]{newauthor,bookno});
            System.out.println("修改成功");
        }else{
            System.out.println("查无此书");
        }
    }
    @org.junit.Test
    public void updateSort(String bookno,String newsort){
        if(query(bookno)){
            String sql="update bookinfo set sort=? where bookno=?";
            jdbcTemplate.update(sql,new Object[]{newsort,bookno});
            System.out.println("修改成功");
        }else{
            System.out.println("查无此书");
        }
    }
    @org.junit.Test
    public void updateAmount(String bookno,int newamount){
        if(query(bookno)){
            String sql="update bookinfo set amount=? where bookno=?";
            jdbcTemplate.update(sql,new Object[]{newamount,bookno});
            System.out.println("修改成功");
        }else{
            System.out.println("查无此书");
        }
    }
    public boolean query(String bookno){
        String sql="select * from bookinfo where bookno=?";
        try{
            Map<String,Object> bookinfos=jdbcTemplate.queryForMap(sql,bookno);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
