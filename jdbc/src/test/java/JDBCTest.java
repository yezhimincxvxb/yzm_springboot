import com.yzm.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest(classes = JDBCTest.class)
public class JDBCTest {

    @Test
    void select() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "select * from `user`";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " +
                        rs.getString("username") + " " +
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, rs);
        }
    }

    @Test
    void insert() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "insert into `user`(username,password) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "ABC");
            ps.setString(2, "abc");
            boolean execute = ps.execute();
            if (execute) {
                System.out.println("新增成功");
            } else {
                System.out.println("新增失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    @Test
    void update() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "update `user` set password = ? where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "566");
            ps.setString(2, "ABC");
            if (ps.execute()) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    @Test
    void delete() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "delete from `user` where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "ABC");
            if (ps.execute()) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    @Test
    void delete2() throws SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            // 开启事务，关闭自动提交
            conn.setAutoCommit(false);
            st = conn.createStatement();

            String sql = "update `user` set password = '222' where username = 'ABC'";
            String sql2 = "update `user` set password = '111' where username = 'AAA'";

            st.execute(sql);
            int i = 1/0;
            st.execute(sql2);

            // 提交事务
            conn.commit();
        } catch (Exception e) {
            // 回滚事务
            conn.rollback();
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, st, null);
        }
    }

}
