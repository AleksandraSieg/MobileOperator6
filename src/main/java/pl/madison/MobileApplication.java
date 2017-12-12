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

    public MobileOperator createMobileOperator(String name, double subscriptionFeePerMonth){
        return MobileOperator.builder().name(name).subscriptionFeePerMonth(subscriptionFeePerMonth).build();
    }

    @Autowired
    private MobileOperatorDao mobileDao;
    public void run(String... strings) throws Exception {

       MobileOperator mobileOp1 = createMobileOperator("Apple%Peer", 40D);
       MobileOperator mobileOp2 = createMobileOperator("Baran", 58D);
       MobileOperator mobileOp3 = createMobileOperator("ApplePie", 78D);
       MobileOperator mobileOp4 = createMobileOperator("Z-mobile", 30D);


        mobileDao.save(mobileOp1);
        mobileDao.save(mobileOp2);
        mobileDao.save(mobileOp3);
        mobileDao.save(mobileOp4);

    }
}
