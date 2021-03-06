package org.xupeng.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xupeng
 * @date 2020/10/18 23:00
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {


    private Long id;

    private String serial;
}
