package com.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.market.bean.MarketOrderBean;
import com.market.bean.PropBean;
import com.market.util.JndiToJdbc;

public class MarketDao {
    private static final String GET_SELECTED_MARKET_ORDERS = "SELECT allProps.gameId, "
            + "marketOrders.orderId, "
            + "allProps.propName, "
            + "marketOrders.uniqueId, "
            + "allProps.propType, "
            + "allProps.propImagePath, "
            + "marketOrders.price, "
            + "marketOrders.sellerId, "
            + "marketOrders.expirationTime, "
            + "marketOrders.orderStatus "
            + "FROM allProps "
            + "RIGHT JOIN marketOrders "
            + "ON allProps.propId = marketOrders.propId "
            + "WHERE allProps.gameId = ?";

    //選擇遊戲後顯示訂單資料
    public static List<MarketOrderBean> getSelectedMarketOrders(int selectedGameId) {
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        List<MarketOrderBean> selectedMarketOrders = new ArrayList<>();
        try (Connection conn = jndiToJdbc.getConnection("db37");
             PreparedStatement stmt = conn.prepareStatement(GET_SELECTED_MARKET_ORDERS)) {
            stmt.setInt(1, selectedGameId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MarketOrderBean marketOrder = new MarketOrderBean();
                marketOrder.setGameId(rs.getInt(1));
                marketOrder.setOrderId(rs.getInt(2));
                marketOrder.setUniqueId(rs.getInt(4));
                marketOrder.setPrice(rs.getInt(7));
                marketOrder.setSellerId(rs.getInt(8));
                marketOrder.setExpirationTime(rs.getTimestamp(9));
                marketOrder.setOrderStatus(rs.getString(10));
                selectedMarketOrders.add(marketOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedMarketOrders;
    }

    //選擇遊戲後顯示道具資料
    public static List<PropBean> getSelectedMarketProps(int selectedGameId) {
        JndiToJdbc jndiToJdbc = new JndiToJdbc();
        List<PropBean> selectedMarketProps = new ArrayList<>();
        try (Connection conn = jndiToJdbc.getConnection("db37");
             PreparedStatement stmt = conn.prepareStatement(GET_SELECTED_MARKET_ORDERS)) {
            stmt.setInt(1, selectedGameId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PropBean prop = new PropBean();
                prop.setPropName(rs.getString(3));
                prop.setPropType(rs.getString(5));
                prop.setPropImagePath(rs.getString(6));
                selectedMarketProps.add(prop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedMarketProps;
    }
}