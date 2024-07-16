package com.mannazo.shopservice;

import com.mannazo.shopservice.client.UserServiceClient;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.entity.OrderItemEntity;
import com.mannazo.shopservice.entity.ProductEntity;
import com.mannazo.shopservice.repository.ImageRepository;
import com.mannazo.shopservice.repository.OrderItemRepository;
import com.mannazo.shopservice.repository.OrderRepository;
import com.mannazo.shopservice.repository.ProductRepository;
import com.mannazo.shopservice.entity.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@ActiveProfiles("test")
@SpringBootTest
public class DataSetupTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Test
    void setupData() {
        // Create products
        ProductEntity product1 = createProduct("Product 1", "Description 1", "Category 1", 100, "10000");
        ProductEntity product2 = createProduct("Product 2", "Description 2", "Category 2", 150, "15000");
        ProductEntity product3 = createProduct("Product 3", "Description 3", "Category 1", 120, "12000");
        ProductEntity product4 = createProduct("Product 4", "Description 4", "Category 3", 80, "8000");
        ProductEntity product5 = createProduct("Product 5", "Description 5", "Category 2", 200, "20000");
        ProductEntity product6 = createProduct("Product 6", "Description 6", "Category 1", 90, "9000");
        ProductEntity product7 = createProduct("Product 7", "Description 7", "Category 3", 110, "11000");
        ProductEntity product8 = createProduct("Product 8", "Description 8", "Category 2", 130, "13000");
        ProductEntity product9 = createProduct("Product 9", "Description 9", "Category 1", 180, "18000");
        ProductEntity product10 = createProduct("Product 10", "Description 10", "Category 3", 95, "9500");

        // Save products to repository
        List<ProductEntity> products = List.of(product1, product2, product3, product4, product5,
                product6, product7, product8, product9, product10);
        productRepository.saveAll(products);

        // Create orders
        OrderEntity order1 = createOrder("User 1", "123-456-7890", "user1@example.com", "Address 1", "12345", "123ABC");
        OrderEntity order2 = createOrder("User 2", "234-567-8901", "user2@example.com", "Address 2", "23456", "456DEF");
        OrderEntity order3 = createOrder("User 3", "345-678-9012", "user3@example.com", "Address 3", "34567", "789GHI");

        // Save orders to repository
        List<OrderEntity> orders = List.of(order1, order2, order3);
        orderRepository.saveAll(orders);

        // Create order items
        createOrderItem(order1, product1, 2, "10000");
        createOrderItem(order1, product2, 1, "15000");
        createOrderItem(order2, product3, 3, "12000");
        createOrderItem(order2, product4, 1, "8000");
        createOrderItem(order3, product5, 2, "20000");
        createOrderItem(order3, product6, 2, "9000");

        // Save order items to repository
        List<OrderItemEntity> orderItems = orderItemRepository.findAll();
        orderItems.forEach(orderItem -> {
            System.out.println("Order Item ID: " + orderItem.getOrderItemId() +
                    ", Product: " + orderItem.getProduct().getProductName() +
                    ", Quantity: " + orderItem.getQuantity() +
                    ", Price: " + orderItem.getPrice());
        });
    }

    private ProductEntity createProduct(String name, String description, String category, int stock, String price) {
        ProductEntity product = new ProductEntity();
        product.setProductName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setStock(stock);
        product.setPrice(price);
        product.setCreateAt(new Timestamp(System.currentTimeMillis()));
        return product;
    }

    private UUID getRandomUserId() {
        List<UUID> userIds = userServiceClient.getAllUserIds();
        Random random = new Random();
        return userIds.get(random.nextInt(userIds.size()));
    }

    private OrderStatus getRandomOrderStatus() {
        OrderStatus[] statuses = OrderStatus.values();
        Random random = new Random();
        return statuses[random.nextInt(statuses.length)];
    }

    public OrderEntity createOrder(String name, String tel, String email, String addr, String postcode, String merchantUid) {
        OrderEntity order = new OrderEntity();
        order.setUserId(getRandomUserId());
        order.setName(name);
        order.setTel(tel);
        order.setEmail(email);
        order.setAddr(addr);
        order.setPostcode(postcode);
        order.setMerchantUid(merchantUid);
        order.setOrderStatus(getRandomOrderStatus());
        return order;
    }

    private void createOrderItem(OrderEntity order, ProductEntity product, int quantity, String price) {
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(price);
        orderItem.setTotalPrice(String.valueOf(quantity * Integer.parseInt(price)));
        orderItemRepository.save(orderItem);
    }
}
