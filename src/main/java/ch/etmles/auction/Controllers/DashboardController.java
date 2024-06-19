package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final LotRepository lotRepository;

    @Autowired
    public DashboardController(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @GetMapping("/lotsForSale/{customerId}")
    public List<Lot> getLotsForSale(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndCustomer_Id("active", customerId);
    }

    @GetMapping("/lotsSold/{customerId}")
    public List<Lot> getLotsSold(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndCustomer_Id("inactive", customerId);
    }

    @GetMapping("/lotsAffected/{customerId}")
    public List<Lot> getLotsAffected(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndHighestBidder_Id("awaiting payment", customerId);
    }

    @GetMapping("/lotsBid/{customerId}")
    public List<Lot> getLotsBid(@PathVariable Long customerId) {
        return lotRepository.findByHighestBidder_IdAndStatusNot(customerId, "awaiting payment");
    }

    @GetMapping("/lotsOwned/{customerId}")
    public List<Lot> getLotsOwned(@PathVariable Long customerId) {
        return lotRepository.findByCustomer_Id(customerId);
    }

    @GetMapping("/lotsVendues/{customerId}")
    public List<Lot> getLotsVendues(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndCustomer_Id("inactive", customerId);
    }
}
