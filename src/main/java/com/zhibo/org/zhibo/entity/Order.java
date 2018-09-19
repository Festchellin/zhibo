package com.zhibo.org.zhibo.entity;

/**
 * 支付宝订单
 */
public class Order {
    private String id;
    private String subject;
    private Product product;
    private Integer quantity;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "subject='" + subject + '\'' +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
