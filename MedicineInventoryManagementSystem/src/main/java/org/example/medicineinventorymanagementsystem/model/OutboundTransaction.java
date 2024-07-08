package org.example.medicineinventorymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "outbound_transactions")
@AllArgsConstructor
@NoArgsConstructor
// Lombok
@Data
public class OutboundTransaction {
//    id: Integer, Primary Key
//    medicine_id: Integer, Foreign Key (references Medicine)
//    quantity: Integer
//    dispatched_date: Date
//    receiver: String

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Transient
    private Integer medicineId;

    // many transactions can map to 1 medicine
    // fetch表示查询时的加载模式[懒加载还是积极加载]
    // cascade属性表示要级联操作的模式，此处为删除主表后从表一并删除。
    @ManyToOne
    @JoinColumn(name="medicine_id", nullable = false)
    private Medicine medicine;

    @Column
    @NotNull
    @Min(value = 1, message="Quantity must be at least 1")
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    @Column
    @NotNull
    private Date dispatched_date;

    @Column
    @NotEmpty
    @NotNull
    private String receiver;
}
