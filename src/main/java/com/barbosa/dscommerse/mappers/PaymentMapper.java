package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.PaymentDTO;
import com.barbosa.dscommerse.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO toDto(Payment payment);
    Payment toEntity(PaymentDTO paymentDTO);
}
