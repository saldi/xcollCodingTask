package com.ubs.xcoll.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ubs.xcoll.orders.web.OrderController;
import com.ubs.xcoll.orders.web.RequestOrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SimpleAppITConfiguration.class)
@AutoConfigureMockMvc
public class SimpleAppIT {

    public static final String DAILY_OVERRIDED_PRODUCT = "overridedProduct";
    public static final String WEEKLY_OVERRIDED_PRODUCT = "weeklyOverridedProduct";
    @Autowired
    private OrderController orderController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contexLoads() throws Exception {
        assertThat(orderController).isNotNull();
    }

    @Test
    public void addOrderTest() throws Exception {
        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(simpleItems())))
                .andExpect(status().isOk());

        mockMvc.perform(get("/orders/test?_count")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    public void ovverideDailyVolumeTest() throws Exception {
        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(overideDailyOrder())))
                .andExpect(status().isOk());

        mockMvc.perform(get("/orders/"+ DAILY_OVERRIDED_PRODUCT +"?_count")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("15"));

        mockMvc.perform(get("/alerts/"+ DAILY_OVERRIDED_PRODUCT)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"dailyVolumeBreached\":true")));

    }

    @Test
    public void ovverideWeeklyVolumeTest() throws Exception {
        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(overideWeeklyOrder())))
                .andExpect(status().isOk());

        mockMvc.perform(get("/orders/"+WEEKLY_OVERRIDED_PRODUCT+"?_count")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("50"));

        mockMvc.perform(get("/alerts/"+WEEKLY_OVERRIDED_PRODUCT)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"weeklyVolumeBreached\":true")));

    }


    private List<RequestOrderItem> simpleItems() {
        RequestOrderItem item = new RequestOrderItem("test", 5);
        return Lists.newArrayList(item);
    }

    private List<RequestOrderItem> overideDailyOrder() {
        RequestOrderItem item = new RequestOrderItem(DAILY_OVERRIDED_PRODUCT, 15);
        return Lists.newArrayList(item);
    }

    private List<RequestOrderItem> overideWeeklyOrder() {
        RequestOrderItem item = new RequestOrderItem(WEEKLY_OVERRIDED_PRODUCT, 50);
        return Lists.newArrayList(item);
    }
}
