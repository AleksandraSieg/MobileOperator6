package pl.madison.utils;

import pl.madison.domain.MobileOperator;

import java.util.Comparator;

public class MobileOperatorComparator implements Comparator<MobileOperator> {
    public int compare(MobileOperator o1, MobileOperator o2) {
        if( o1.getSubscriptionFeePerMonth()>o2.getSubscriptionFeePerMonth())
            return 1;
        if( o1.getSubscriptionFeePerMonth()<o2.getSubscriptionFeePerMonth())
            return -1;
        return 0;
    }
}
