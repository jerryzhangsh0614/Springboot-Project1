package org.example.medicineinventorymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medicineinventorymanagementsystem.Enum.MedicineTypeEnum;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

/*
    id: Integer, Primary Key
    name: String
    description: String
    quantity: Integer
    type: Enum[PRES, OTC, OTHER]
*/

// JPA(Hibernate)的实体类注解
@Entity
@Table(name = "medicine")
@AllArgsConstructor
@NoArgsConstructor
// Lombok
@Data
public class Medicine {
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    //    AUTO主键由程序控制, 是默认选项 ,不设置就是这个
    //    IDENTITY 主键由数据库生成, 采用数据库自增长, Oracle不支持这种方式
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(unique = true)
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;

    @Column
    @Size(max = 255)
    private String description;

    @Column
    @NotNull
    @Positive
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    private MedicineTypeEnum type;

    // 1 medicine can have multiple transactions
//    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<InboundTransaction> inboundTransactions;
}
