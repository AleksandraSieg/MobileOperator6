package pl.madison.controller;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.madison.dao.MobileOperatorDao;
import pl.madison.domain.MobileOperator;
import pl.madison.utils.MobileOperatorComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private MobileOperatorDao mobileOperatorDao;
    @RequestMapping(value = "/showOffer", method = RequestMethod.GET)
    public String showOffer() {
        String operatorTemp = "";

        for (MobileOperator mobileOperator : mobileOperatorDao.findAll()) {
            operatorTemp = operatorTemp + mobileOperator;
        }

        return operatorTemp;
    }

    @RequestMapping(value = "/averageMonthlyPayment", method = RequestMethod.GET)
    public String average(){
        double sum = 0;
        int counter = 0;

        for (MobileOperator mobileOperator : mobileOperatorDao.findAll()) {
            sum = sum + Integer.parseInt(mobileOperator.getSubscriptionFeePerMonth());
            counter++;
        }

        double average = sum/counter;

        return "The monthly average payment is: " + average;
    }

    @RequestMapping(value = "/median", method = RequestMethod.GET)
    public String median(){

        List<MobileOperator> operators = new ArrayList<MobileOperator>();
        operators = (List<MobileOperator>)mobileOperatorDao.findAll();
        int[] numbers = new int[operators.size()];

        Collections.sort(operators, new MobileOperatorComparator());

        MobileOperator median = null;
        Integer median2 = 0;


        for (int i = 0; i < operators.size(); i++) {
            if(operators.size()%2==1){
                median2 = Integer.parseInt(operators.get((operators.size()-1)/2+1/2).getSubscriptionFeePerMonth());
            }else if(operators.size()%2==0){
                median2 = (Integer.parseInt(operators.get((operators.size()-1)/2).getSubscriptionFeePerMonth())+
                        Integer.parseInt(operators.get((operators.size()-1)/2+1).getSubscriptionFeePerMonth()))/2;
            }

        }

        return "median = " + median2;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam("id") Long id, @RequestParam("name") String name,
                         @RequestParam("subscriptionFeePerMonth") String subscriptionFeePerMonth) {
        MobileOperator tempOp = mobileOperatorDao.findOne(id);
        tempOp.setName(name);
        tempOp.setSubscriptionFeePerMonth(subscriptionFeePerMonth);
        mobileOperatorDao.save(tempOp);

        return "Your update has been completed;)";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteRoom(@RequestParam("id") Long id) {
        mobileOperatorDao.delete(mobileOperatorDao.findOne(id));
        return "You have successfully deleted operator from database";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findRoom(@RequestParam("id") Long id) {
        MobileOperator tempOp = mobileOperatorDao.findOne(id);
        return "" + tempOp;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String addRoom(@RequestParam("name") String name, @RequestParam("subscriptionFeePerMonth") String subscriptionFeePerMonth){
        MobileOperator tempOp = new MobileOperator();
        tempOp.setName(name);
        tempOp.setSubscriptionFeePerMonth(subscriptionFeePerMonth);
        mobileOperatorDao.save(tempOp);
        return "You have successfully added new operator";
    }



}
