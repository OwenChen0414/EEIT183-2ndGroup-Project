package com.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.eclipse.tags.shaded.org.apache.bcel.verifier.exc.StaticCodeConstraintException;

import com.market.bean.PropBean;
import com.market.util.JndiToJdbc;

public class PropDao {
    private static final String GET_SELECTED_PROPS = "SELECT * FROM allProps WHERE gameId = ?";
    private static final String CREATE_PROP = "INSERT INTO allProps (gameId,propName,propType,propRarity,propDescription,propImagePath,createdTime) VALUES (?,?,?,?,?,?,GETDATE())";
    private static final String DELETE_PROP = "DELETE FROM allProps WHERE propId = ?";
    private static final String UPDATE_PROP = "UPDATE allProps SET propName=?, propType=?, propRarity=?, propDescription=?, propImagePath=?, updatedTime=GETDATE() WHERE propId=?";


    //選擇遊戲後顯示道具資料
    public static List<PropBean> getSelectedProps(int selectedGameId) throws SQLException {
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        List<PropBean> props = new ArrayList<>();
        try (Connection conn = jndiToJdbc.getConnection("db37");
             PreparedStatement stmt = conn.prepareStatement(GET_SELECTED_PROPS)) {
            stmt.setInt(1, selectedGameId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PropBean prop = new PropBean();
                    prop.setGameId(rs.getInt("gameId"));
                    prop.setPropId(rs.getInt("propId"));
                    prop.setPropName(rs.getString("propName"));
                    prop.setPropType(rs.getString("propType"));
                    prop.setPropRarity(rs.getString("propRarity"));
                    prop.setPropDescription(rs.getString("propDescription"));
                    prop.setPropImagePath(rs.getString("propImagePath"));
                    prop.setCreatedTime(rs.getTimestamp("createdTime"));
                    prop.setUpdatedTime(rs.getTimestamp("updatedTime"));
                    props.add(prop);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return props;
    }

    //創建道具
    public static void createProp(int selectedGameId, PropBean prop) throws SQLException {
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        try (Connection conn = jndiToJdbc.getConnection("db37");
             PreparedStatement stmt = conn.prepareStatement(CREATE_PROP)) {
            stmt.setInt(1, selectedGameId);
            stmt.setString(2, prop.getPropName());
            stmt.setString(3, prop.getPropType());
            stmt.setString(4, prop.getPropRarity());
            stmt.setString(5, prop.getPropDescription());
            stmt.setString(6, prop.getPropImagePath());
            stmt.executeUpdate();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    //刪除道具
    public static void deleteProp(int propId) throws SQLException, NamingException {
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        try (Connection conn = jndiToJdbc.getConnection("db37");
             PreparedStatement stmt = conn.prepareStatement(DELETE_PROP)) {
            stmt.setInt(1, propId);
            stmt.executeUpdate();
        }
    }
    
    //更新道具
    public static void updateProp(PropBean prop) throws SQLException {
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        try (Connection conn = jndiToJdbc.getConnection("db37");
             PreparedStatement stmt = conn.prepareStatement(UPDATE_PROP)) {
            stmt.setString(1, prop.getPropName());
            stmt.setString(2, prop.getPropType());
            stmt.setString(3, prop.getPropRarity());
            stmt.setString(4, prop.getPropDescription());
            stmt.setString(5, prop.getPropImagePath());
            stmt.setInt(6, prop.getPropId());
            stmt.executeUpdate();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to update prop", e);//重新拋出異常
        }
    }
}
