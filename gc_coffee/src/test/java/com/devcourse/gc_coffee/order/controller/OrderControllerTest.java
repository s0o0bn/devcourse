package com.devcourse.gc_coffee.order.controller;

import com.devcourse.gc_coffee.global.exception.BadRequestException;
import com.devcourse.gc_coffee.global.exception.UnauthorizedException;
import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.dto.OrderBasicDto;
import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.service.OrderReadService;
import com.devcourse.gc_coffee.order.service.OrderService;
import com.devcourse.gc_coffee.order.service.facade.OrderUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static com.devcourse.gc_coffee.fixture.order.OrderFixture.getOrderWithId;
import static com.devcourse.gc_coffee.fixture.order.OrderItemFixture.getOrderItems;
import static com.devcourse.gc_coffee.fixture.order.OrderRequestFixture.getOrderRequest;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderUseCase orderUseCase;
    @MockBean
    private OrderReadService orderReadService;
    @MockBean
    private OrderService orderService;

    @Nested
    @DisplayName("[주문 생성 테스트]")
    class CreateOrderTest {
        @Test
        @DisplayName("주문을 생성한다.")
        void create_new_order() throws Exception {
            // given
            OrderRequest request = getOrderRequest();
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(request));

            // when

            // then
            mvc.perform(post("/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
            verify(orderUseCase).createOrder(request);
        }
    }

    @Nested
    @DisplayName("[주문 조회 테스트]")
    class GetOrderListTest {
        @Test
        @DisplayName("고객의 주문 내역을 조회한다.")
        void get_customers_all_order_list() throws Exception {
            // given
            String email = "user@email.com";
            Order order = getOrderWithId();
            order.getOrderItems().addAll(getOrderItems());

            // when
            when(orderReadService.getOrdersOf(anyString())).thenReturn(List.of(OrderBasicDto.from(order)));

            // then
            mvc.perform(get("/orders")
                    .param("email", email)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.orders").exists())
                    .andExpect(jsonPath("$.orders[0].details", hasSize(2)));
            verify(orderReadService).getOrdersOf(email);
        }
    }

    @Nested
    @DisplayName("[주문 삭제 테스트]")
    class DeleteOrderTest {
        @Test
        @DisplayName("고객의 주문을 취소한다.")
        void cancel_customers_order() throws Exception {
            // given
            String id = "order id";
            String email = "user@email.com";

            // when

            // then
            mvc.perform(delete("/orders/" + id)
                    .param("email", email)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            verify(orderService).deleteOrder(id, email);
        }

        @Test
        @DisplayName("자신의 주문이 아닌 주문을 취소 시 실패한다.")
        void fail_canceling_order_when_order_is_not_mine() throws Exception {
            // given
            String id = "order id";
            String email = "user@email.com";

            // when
            doThrow(UnauthorizedException.class)
                    .when(orderService).deleteOrder(anyString(), anyString());

            // then
            mvc.perform(delete("/orders/" + id)
                            .param("email", email)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("이미 처리된 주문을 취소 시 실패한다.")
        void fail_canceling_order_when_order_is_already_processed() throws Exception {
            // given
            String id = "order id";
            String email = "user@email.com";

            // when
            doThrow(BadRequestException.class)
                    .when(orderService).deleteOrder(anyString(), anyString());

            // then
            mvc.perform(delete("/orders/" + id)
                            .param("email", email)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("존재하지 않는 주문을 취소 시 실패한다.")
        void fail_canceling_order_when_order_is_not_exists() throws Exception {
            // given
            String id = "order id";
            String email = "user@email.com";

            // when
            doThrow(NoSuchElementException.class)
                    .when(orderService).deleteOrder(anyString(), anyString());

            // then
            mvc.perform(delete("/orders/" + id)
                            .param("email", email)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("[주문 수정 테스트]")
    class UpdateOrderTest {

    }
}
