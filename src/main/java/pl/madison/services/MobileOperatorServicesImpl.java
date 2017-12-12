package pl.madison.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.madison.dao.MobileOperatorDao;
import pl.madison.domain.MobileOperator;

import java.util.List;

@Service
public class MobileOperatorServicesImpl implements ImobileOperatorServices {

    @Autowired
    private MobileOperatorDao mobileOperatorDao;

    public List<MobileOperator> findAll() {
        return (List<MobileOperator>) mobileOperatorDao.findAll();
    }

    public long count() {
        return mobileOperatorDao.count();
    }

    public MobileOperator findOne(Long id) {
        return mobileOperatorDao.findOne(id);
    }

    public MobileOperator save(MobileOperator mobileOperator) {
        return mobileOperatorDao.save(mobileOperator);
    }

    public void delete(Long id) {
        mobileOperatorDao.delete(id);
    }

}
