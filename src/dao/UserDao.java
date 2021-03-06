package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {

	public List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String mail = rs.getString("mail");
				String point = rs.getString("point");
				String libraryId = rs.getString("library_id");
				String registerDate = rs.getString("register_date");
				String stopping = rs.getString("stopping");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setAddress(address);
				user.setTel(tel);
				user.setMail(mail);
				user.setPoint(point);
				user.setLibraryId(libraryId);
				user.setRegisterDate(registerDate);
				user.setStopping(stopping);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public User toUser(ResultSet rs) throws SQLException {

		User ret = new User();

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String mail = rs.getString("mail");
				String point = rs.getString("point");
				String libraryId = rs.getString("library_id");
				String registerDate = rs.getString("register_date");
				String stopping = rs.getString("stopping");

				ret.setId(id);
				ret.setLoginId(loginId);
				ret.setPassword(password);
				ret.setName(name);
				ret.setAddress(address);
				ret.setTel(tel);
				ret.setMail(mail);
				ret.setPoint(point);
				ret.setLibraryId(libraryId);
				ret.setRegisterDate(registerDate);
				ret.setStopping(stopping);

			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO users(");
			sql.append("  login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", address");
			sql.append(", tel");
			sql.append(", mail");
			sql.append(", point");
			sql.append(", library_id");
			sql.append(", register_date");
			sql.append(", stopping");

			sql.append(")VALUES(");
			sql.append("  ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?)");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getTel());
			ps.setString(6, user.getMail());
			ps.setString(7, "0");
			ps.setString(8, user.getLibraryId());
			ps.setString(9, "0");

			ps.executeUpdate();
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			if(user.getPassword() == null || user.getPassword().isEmpty()) {
				sql.append(" login_id = ?");
				sql.append(" WHERE");
				sql.append(" id = ?");
				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getLoginId());
				ps.setInt(2, user.getId());
			}
			else if(user.getName() == null) {
				sql.append(" password = ?");
				sql.append(" , login_id = ?");
				sql.append(" WHERE");
				sql.append(" id = ?");
				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getPassword());
				ps.setString(2, user.getLoginId());
				ps.setInt(3, user.getId());
			} else {
				sql.append("  login_id = ?");
				if(!StringUtils.isEmpty(user.getPassword())) {
					sql.append(", password = ?");
				}
				sql.append(", name = ?");
				sql.append(", address = ?");
				sql.append(", tel = ?");
				sql.append(", mail = ?");
				sql.append(", point = ?");
				sql.append(", library_id = ?");
				sql.append(", stopping = ?");

				sql.append(" WHERE");
				sql.append(" id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getLoginId());
				if(!StringUtils.isEmpty(user.getPassword())) {
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getName());
					ps.setString(4, user.getAddress());
					ps.setString(5, user.getTel());
					ps.setString(6, user.getMail());
					ps.setString(7, user.getPoint());
					ps.setString(8, user.getLibraryId());
					ps.setString(9, user.getRegisterDate());
					ps.setString(10, "0");
					ps.setInt(11, user.getId());
				} else {
					ps.setString(1, user.getLoginId());
					ps.setString(2, user.getName());
					ps.setString(3, user.getAddress());
					ps.setString(4, user.getTel());
					ps.setString(5, user.getMail());
					ps.setString(6, user.getPoint());
					ps.setString(7, user.getLibraryId());
					ps.setString(8, "0");
					ps.setInt(9, user.getId());
				}
			}

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public List<User> getRefinedUser(Connection connection, String freeWord, String selectedLibrary) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users WHERE ");
			sql.append("CONCAT(login_id, password, name, address, tel, mail) LIKE ? ");
			if(!selectedLibrary.equals("0")) sql.append("AND library_id = ? ");
			sql.append("ORDER BY register_date DESC");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "%" + freeWord + "%");
			if(!selectedLibrary.equals("0")) ps.setString(2, selectedLibrary);

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<User> getSelectAllUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ORDER BY register_date DESC");

			//sql.append("SELECT * FROM users order by branch_id asc, department_id asc ");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getLoginUser(Connection connection, String loginId, String password) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users WHERE");
			sql.append(" login_id = ? AND");
			sql.append(" password = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			User ret = toUser(rs);

			if (ret.getLoginId() == null) {
				return null;
			}else return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public User selectUser(Connection connection, String userId){
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ? ";

			ps = connection.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs =ps.executeQuery();
			List<User> userList = toUserList(rs);
			if(userList.isEmpty() == true) {
				return null;
			} else if(2<= userList.size()) {
				throw new IllegalStateException("2<= userList.size()");
			} else {
				return userList.get(0);

			}
		}catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	public void stoppingUser(Connection connection, int stopping, int num, String time){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" stopping = ?");
			sql.append(", register_date =?");

			sql.append(" WHERE id=?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, num);
			ps.setString(2, time);
			ps.setInt(3, stopping);

			ps.executeUpdate();
			}catch(SQLException e){
				throw new SQLRuntimeException(e);
			}finally{
				close(ps);
			}
		}

	public void point(Connection connection, int point, int userId){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" point = ?");

			sql.append(" WHERE id=?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, (point+1));
			ps.setInt(2, userId);

			ps.executeUpdate();
			}catch(SQLException e){
				throw new SQLRuntimeException(e);
			}finally{
				close(ps);
			}
		}
	public User select(Connection connection, int userId){
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ? ";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs =ps.executeQuery();
			List<User> userList = toUserList(rs);
			if(userList.isEmpty() == true) {
				return null;
			} else if(2<= userList.size()) {
				throw new IllegalStateException("2<= userList.size()");
			} else {
				return userList.get(0);

			}
		}catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	public void update(Connection connection, int renewUserId, String renewUserLoginId) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" login_id = ?");
			sql.append(", register_date = CURRENT_TIMESTAMP");

			sql.append(" WHERE id= ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, renewUserLoginId);
			ps.setInt(2, renewUserId);

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

}


