package bo;

import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderPlaceBoImpl;
import validation.impl.ValidationImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBo getBO(BoTypes types) {
        switch (types) {
            case ITEM:
                return (SuperBo) new ItemBoImpl();
            case CUSTOMER:
                return (SuperBo) new CustomerBoImpl();
            case PURCHASE_ORDER:
                return (SuperBo) new OrderPlaceBoImpl();
            case VALIDATION:
                return (SuperBo) new ValidationImpl();

            default:
                return null;
        }
    }

    public enum BoTypes {
        CUSTOMER, ITEM, PURCHASE_ORDER,VALIDATION
    }
}