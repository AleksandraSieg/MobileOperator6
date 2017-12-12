package pl.madison.controller;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.madison.dao.MobileOperatorDao;
import pl.madison.domain.MobileOperator;
import pl.madison.services.ImobileOperatorServices;
import pl.madison.utils.MobileOperatorComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

//    @Autowired
//    private MobileOperatorDao mobileOperatorDao;

    @Autowired
    private ImobileOperatorServices imobileOperatorServices;

    @RequestMapping(value = "/showOffer", method = RequestMethod.GET)
    public List<MobileOperator> showOffer() {

        return (List<MobileOperator>)imobileOperatorServices.findAll();
    }

    @RequestMapping(value = "/averageMonthlyPayment", method = RequestMethod.GET)
    public String average(){
        double sum = 0;

        for (MobileOperator mobileOperator : imobileOperatorServices.findAll()) {
            sum = sum + mobileOperator.getSubscriptionFeePerMonth();
        }

        Double average = sum/imobileOperatorServices.count();

        return "The monthly average payment is: " + average;
    }

    @RequestMapping(value = "/median", method = RequestMethod.GET)
    public String median(){

        List<MobileOperator> operators = new ArrayList<MobileOperator>();
        operators = (List<MobileOperator>)imobileOperatorServices.findAll();

        Collections.sort(operators, new MobileOperatorComparator());

        double median2 = 0;


        for (int i = 0; i < operators.size(); i++) {
            if(operators.size()%2==1){
                median2 = operators.get((operators.size()-1)/2+1/2).getSubscriptionFeePerMonth();
            }else if(operators.size()%2==0){
                median2 = (operators.get((operators.size()-1)/2).getSubscriptionFeePerMonth()+
                        operators.get((operators.size()-1)/2+1).getSubscriptionFeePerMonth())/2;
            }

        }

        return "median = " + median2;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam("id") Long id, @RequestParam("name") String name,
                         @RequestParam("subscriptionFeePerMonth") double subscriptionFeePerMonth) {
        MobileOperator tempOp = MobileOperator.builder().id(id).name(name)
                .subscriptionFeePerMonth(subscriptionFeePerMonth).build();
        imobileOperatorServices.save(tempOp);

        return "Your update has been completed;)";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") Long id) {

        imobileOperatorServices.delete(id);
        return "You have successfully deleted operator from database";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(@RequestParam("id") Long id) {
        MobileOperator tempOp = imobileOperatorServices.findOne(id);
        return "" + tempOp;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String add(@RequestParam("name") String name, @RequestParam("subscriptionFeePerMonth")
            double subscriptionFeePerMonth){
        MobileOperator tempOp = MobileOperator.builder().name(name)
                .subscriptionFeePerMonth(subscriptionFeePerMonth).build();
        imobileOperatorServices.save(tempOp);
        return "You have successfully added new operator";
    }


    @RequestMapping(value="demo")
    public String addTest(){
        return "extra";
    }


}
