class step018 {
    public static void main(String[] args) {
        step017 mapper = new step017();

        step010 shop = mapper.selectOne("select", new Object[]{1}, new Mapper<step010>() {

            @Override
            public step010 mapping(int row, ResultSet rs) {

                step010 shop = new step010();
                
                try {
                    shop.setShopNo(rs.getInt("SHOP_NO"));
                    shop.setShopName(rs.getString("SHOP_NAME"));
                    shop.setShopLocation(rs.getString("SHOP_LOCATION"));
                    shop.setShopStatus(rs.getString("SHOP_STATUS"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
                return shop;
            }
        });
    }
}