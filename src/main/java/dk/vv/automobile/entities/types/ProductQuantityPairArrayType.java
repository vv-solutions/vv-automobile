package dk.vv.automobile.entities.types;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ProductQuantityPairArrayType  implements UserType {


    @Override
    public int getSqlType() {
        return 0;
    }

    @Override
    public Class<?> returnedClass() {
        return ProductQuantityPair[].class;
    }

    @Override
    public boolean equals(Object x, Object y) {
        return x == y || (x != null && y != null && x.equals(y));
    }

    @Override
    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        return null;
    }


    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value == null) {
            st.setNull(index, Types.ARRAY);
        } else {
            // Use JDBC to create the array and set it in the PreparedStatement
            ProductQuantityPair[] pairs = (ProductQuantityPair[]) value;
            // Convert ProductQuantityPair[] to SQL Array
            // Example for converting:
            java.sql.Connection connection = st.getConnection();
            Object[] pairObjects = new Object[pairs.length];
            for (int i = 0; i < pairs.length; i++) {
                pairObjects[i] = connection.createStruct("product_quantity_pair", new Object[]{pairs[i].getProductId(), pairs[i].getQuantity()});
            }
            java.sql.Array array = connection.createArrayOf("product_quantity_pair", pairObjects);
            st.setArray(index, array);
        }
    }

    @Override
    public Object deepCopy(Object value) {
        if (value == null) {
            return null;
        }
        ProductQuantityPair[] pairs = (ProductQuantityPair[]) value;
        ProductQuantityPair[] copy = new ProductQuantityPair[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            copy[i] = new ProductQuantityPair(pairs[i].getProductId(), pairs[i].getQuantity());
        }
        return copy;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) {
        return (Serializable) deepCopy(value);
    }

    @Override
    public Object assemble(Serializable cached, Object owner) {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return deepCopy(original);
    }
}
