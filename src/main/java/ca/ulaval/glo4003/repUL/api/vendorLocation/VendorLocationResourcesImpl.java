package ca.ulaval.glo4003.repUL.api.vendorLocation;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.api.vendorLocation.dto.VendorLocationFound;
import ca.ulaval.glo4003.repUL.api.vendorLocation.mapper.VendorLocationDtoMapper;
import ca.ulaval.glo4003.repUL.application.vendorLocation.VendorLocationService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Protected
@Path("/vendorLocation")
public class VendorLocationResourcesImpl {
    private final VendorLocationService vendorLocationService;
    private final VendorLocationDtoMapper vendorLocationDtoMapper;

    public VendorLocationResourcesImpl() {
        this.vendorLocationDtoMapper = ServiceLocator.getInstance().getService(VendorLocationDtoMapper.class);
        this.vendorLocationService = ServiceLocator.getInstance().getService(VendorLocationService.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVendorLocations() {
        List<VendorLocationFound> vendorLocationsFound = vendorLocationService
                .getVendorLocations()
                .stream().map(vendorLocationDtoMapper::toVendorLocationFound)
                .toList();

        return Response.status(Response.Status.OK)
                .entity(vendorLocationsFound)
                .build();
    }
}
