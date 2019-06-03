import com.company9.dao.BookInfoDao;
import com.company9.entity.BookInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestShowOnTable extends JFrame {
    JdbcTemplate jdbcTemplate;
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate=(JdbcTemplate)context.getBean("jdbcTemplate");
    }
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    public TestShowOnTable(int mNO,String info){
        super();
        setTitle("图书表");
        scrollPane=new JScrollPane();
        final String[] columNames={"图书编号","书名","作者","分类","数量"};
        List<BookInfo>books=BookInfoList(mNO,info);
        //List<BookInfo>books=queryAll();
        //List<BookInfo>books=queryByBookno();
       // List<BookInfo>books=queryByBookname();
       // List<BookInfo>books=queryByAuthor();
        //List<BookInfo>books=queryBySort();}
       String tableValues[][]=new String[books.size()][5];
       for(int i=0;i<books.size();i++){
           BookInfo book=books.get(i);
           tableValues[i][0]=book.getBookno();
           tableValues[i][1]=book.getBookname();
           tableValues[i][2]=book.getAuthor();
           tableValues[i][3]=book.getSort();
           tableValues[i][4]=Integer.toString(book.getAmount());
       }
       tableModel=new DefaultTableModel(tableValues,columNames);
       table=new JTable(tableModel);
       table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));
       scrollPane.setViewportView(table);
       getContentPane().add(scrollPane, BorderLayout.CENTER);
       table.setEnabled(false);
       setBounds(300,200,400,300);
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

        public List<BookInfo> queryAll () {
            String sql = "select * from bookinfo";
            List<BookInfo> bookInfos = jdbcTemplate.query(sql, new BookInfoRowMapper());
            return bookInfos;
        }
        public List<BookInfo> queryByBookno (String bookno){
            final String sql = "select * from bookinfo where bookno=?";
            List<BookInfo> bookInfos = jdbcTemplate.query(sql, new BookInfoRowMapper(), bookno);
            return bookInfos;
        }
        public List<BookInfo> queryByBookName (String bookname){
            final String sql = "select * from bookinfo where bookname=?";
            List<BookInfo> bookInfos = jdbcTemplate.query(sql, new BookInfoRowMapper(), bookname);
            return bookInfos;
        }
        public List<BookInfo> queryByAuthor (String author){
            final String sql = "select * from bookinfo where author=?";
            List<BookInfo> bookInfos = jdbcTemplate.query(sql, new BookInfoRowMapper(), author);
            return bookInfos;
        }
        public List<BookInfo> queryBySort (String sort){
            final String sql = "select * from bookinfo where sort=?";
            List<BookInfo> bookInfos = jdbcTemplate.query(sql, new BookInfoRowMapper(), sort);
            return bookInfos;
        }
        private class BookInfoRowMapper implements RowMapper<BookInfo> {
            public BookInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                BookInfo bookInfo = new BookInfo();
                bookInfo.setBookno(resultSet.getString("bookno"));
                bookInfo.setBookname(resultSet.getString("bookname"));
                bookInfo.setAuthor(resultSet.getString("author"));
                bookInfo.setSort(resultSet.getString("sort"));
                bookInfo.setAmount(resultSet.getInt("amount"));
                return bookInfo;
            }
        }
    public List<BookInfo> BookInfoList(int mNo,String info) {
        if (mNo == 1) {
            return queryAll();
        }
        else if (mNo == 2) {
            return queryByBookno(info);
        }
        else if (mNo == 3) {
            return queryByBookName(info);
        }
       else if (mNo == 4) {
            return queryByAuthor(info);
        }
        else if(mNo==5){
            return  queryBySort(info);
        }
        else {
            System.out.println("输入查询方式编号有误");
            return null;
        }
    }
        public static void main (String[]args){
            //BookInfoDao bookInfoDao=new BookInfoDao();
            //bookInfoDao.insert("100004","红楼梦","曹雪芹","文学");
            //bookInfoDao.delete("100004");
            //bookInfoDao.updateBookName("100004","红楼梦新版");
            //bookInfoDao.updateAuthor("100004","曹雪芹，高鹗");
            //bookInfoDao.updateSort("100004","经典文学");
            //bookInfoDao.updateAmount("100004",10);
            //new TestShowOnTable(1,null);
            //new TestShowOnTable(2,"100004");
            //new TestShowOnTable(3,"红楼梦新版");
            //new TestShowOnTable(4,"曹雪芹，高鹗");
            //new TestShowOnTable(5,"经典文学");
            //new TestShowOnTable(6,"100004");
     }
}
