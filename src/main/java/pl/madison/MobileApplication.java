package pl.madison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.madison.dao.MobileOperatorDao;
import pl.madison.domain.MobileOperator;

@SpringBootApplication
public class MobileApplication implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }

    @Autowired
    private MobileOperatorDao mobileDao;
    public void run(String... strings) throws Exception {
        MobileOperator mobileOp1 = new MobileOperator();
        MobileOperator mobileOp2 = new MobileOperator();
        MobileOperator mobileOp3 = new MobileOperator();
        MobileOperator mobileOp4 = new MobileOperator();

        mobileOp1.setName("Apple&Peer");
        mobileOp2.setName("Baran");
        mobileOp3.setName("ApplePie");
        mobileOp4.setName("Z-Mobile");

        mobileOp1.setSubscriptionFeePerMonth("40");
        mobileOp2.setSubscriptionFeePerMonth("58");
        mobileOp3.setSubscriptionFeePerMonth("78");
        mobileOp4.setSubscriptionFeePerMonth("30");

        mobileDao.save(mobileOp1);
        mobileDao.save(mobileOp2);
        mobileDao.save(mobileOp3);
        mobileDao.save(mobileOp4);

    }
}
