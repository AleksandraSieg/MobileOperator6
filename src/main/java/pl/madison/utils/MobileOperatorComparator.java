package pl.madison.utils;

import pl.madison.domain.MobileOperator;

import java.util.Comparator;

public class MobileOperatorComparator implements Comparator<MobileOperator> {
    public int compare(MobileOperator o1, MobileOperator o2) {
        return o1.getSubscriptionFeePerMonth().compareTo(o2.getSubscriptionFeePerMonth());
    }
}
