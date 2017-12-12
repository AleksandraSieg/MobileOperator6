package pl.madison.services;

import pl.madison.domain.MobileOperator;

import java.util.List;

public interface ImobileOperatorServices {

    List<MobileOperator> findAll();
    long count();
    MobileOperator findOne(Long id);
    MobileOperator save(MobileOperator mobileOperator);
    void delete(Long id);

}
