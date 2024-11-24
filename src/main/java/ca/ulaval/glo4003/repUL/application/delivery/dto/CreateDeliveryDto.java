package ca.ulaval.glo4003.repUL.application.delivery.dto;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;

import java.util.List;

public record CreateDeliveryDto(
        String location,
        List<Package> packages
) {}
