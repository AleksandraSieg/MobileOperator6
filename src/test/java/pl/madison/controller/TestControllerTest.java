package pl.madison.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.madison.dao.MobileOperatorDao;
import pl.madison.domain.MobileOperator;
import pl.madison.services.ImobileOperatorServices;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerTest {

    @InjectMocks
    private TestController testController;    //-> mokujemy testcotrollera i on pochlania dao

    @Mock
    private MobileOperatorDao dao;     //-> mokujemy dao

    @Mock
    private ImobileOperatorServices imobile;

    private MockMvc mockMvc;    //-> w calosci mokuje springa

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();} //-> mokuje start aplikacji

    @org.junit.Test
    public void showOffer() throws Exception {
        MobileOperator m1 = MobileOperator.builder().name("xxx").build();
        MobileOperator m2 = MobileOperator.builder().build();

        List<MobileOperator> mobileOperators = Arrays.asList(m1, m2);

        Mockito.when(imobile.findAll()).thenReturn(mobileOperators);

        mockMvc.perform(get("/showOffer")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("xxx"));
    }

    @org.junit.Test
    public void average() throws Exception {
        MobileOperator m1 = MobileOperator.builder().subscriptionFeePerMonth(8.0).build();
        MobileOperator m2 = MobileOperator.builder().subscriptionFeePerMonth(4.0).build();

        List<MobileOperator> mobileOperators = Arrays.asList(m1, m2);

        Mockito.when(imobile.findAll()).thenReturn(mobileOperators);
        Mockito.when(imobile.count()).thenReturn(2L);

        mockMvc.perform(get("/averageMonthlyPayment")).andExpect(content().string("The monthly average payment is: 6.0")).andExpect(status().isOk());
    }

    @org.junit.Test
    public void median() throws Exception {
        MobileOperator m1 = MobileOperator.builder().subscriptionFeePerMonth(8.0).build();
        MobileOperator m2 = MobileOperator.builder().subscriptionFeePerMonth(4.0).build();
        MobileOperator m3 = MobileOperator.builder().subscriptionFeePerMonth(6.0).build();

        List<MobileOperator> mobileOperators = Arrays.asList(m1, m2, m3);

        Mockito.when(imobile.findAll()).thenReturn(mobileOperators);

        mockMvc.perform(get("/median")).andExpect(content().string("median = 6.0"));
    }

    @org.junit.Test
    public void median2() throws Exception {
        MobileOperator m1 = MobileOperator.builder().subscriptionFeePerMonth(2.0).build();
        MobileOperator m2 = MobileOperator.builder().subscriptionFeePerMonth(4.0).build();
        MobileOperator m3 = MobileOperator.builder().subscriptionFeePerMonth(5.0).build();
        MobileOperator m4 = MobileOperator.builder().subscriptionFeePerMonth(8.0).build();

        List<MobileOperator> mobileOperators = Arrays.asList(m1, m2, m3, m4);

        Mockito.when(imobile.findAll()).thenReturn(mobileOperators);

        mockMvc.perform(get("/median")).andExpect(content().string("median = 4.5"));
    }

    //test na mediane gdy jest parzysta liczba elementow

    @org.junit.Test
    public void update() throws Exception {
        Mockito.when(imobile.findOne(1L)).thenReturn(MobileOperator.builder().id(1L).name("xxx")
                .subscriptionFeePerMonth(300).build());
        mockMvc.perform(MockMvcRequestBuilders.put("/update").param("id","1")
                .param("name", "xxx")
                .param("subscriptionFeePerMonth", "300"))
                .andExpect(MockMvcResultMatchers.content().string("Your update has been completed;)"));

    }

    @org.junit.Test
    public void delete() throws Exception {
        Mockito.when(imobile.findOne(1L)).thenReturn(MobileOperator.builder().id(1L).build());
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete")
                .param("id","1")).andExpect(MockMvcResultMatchers.content()
                .string("You have successfully deleted operator from database"));
    }

    @org.junit.Test
    public void find() throws Exception {
        MobileOperator m = MobileOperator.builder().id(1L).name("xxx").subscriptionFeePerMonth(20).build();
        Mockito.when(imobile.findOne(1L)).thenReturn(m);

        mockMvc.perform(get("/find").param("id","1"))
                .andExpect(MockMvcResultMatchers.content().string(""+m));

    }

    @org.junit.Test
    public void add() throws Exception {
        MobileOperator m = MobileOperator.builder().name("xx").subscriptionFeePerMonth(30).build();
        Mockito.when(imobile.save(m)).thenReturn(m);

        mockMvc.perform(MockMvcRequestBuilders.put("/add")
                .param("name","xx")
                .param("subscriptionFeePerMonth","30"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("You have successfully added new operator"));
    }

    @Test
    public void addTest() throws Exception {
        //perform
    }

}