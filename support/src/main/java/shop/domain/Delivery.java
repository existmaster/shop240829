package shop.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import shop.SupportApplication;
import shop.domain.DeliveryCancelled;
import shop.domain.DeliveryStarted;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String address;

    private Long productId;

    private Integer qty;

    private String status;

    @PostPersist
    public void onPostPersist() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(this);
        deliveryCancelled.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = SupportApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }


    public static void startDelivery(OrderPlaced orderPlaced) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderPlaced.getId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setQty(orderPlaced.getQty());
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setStatus("DELIVERY_READY");
        repository().save(delivery);

    }

    public static void cancelDelivery(OrderCancelled orderCancelled) {

        repository().deleteByOrderId(orderCancelled.getId());

    }

}

