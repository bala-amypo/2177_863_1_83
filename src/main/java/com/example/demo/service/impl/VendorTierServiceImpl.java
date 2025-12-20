 import org.springframework.stereotype.Service;
 @Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repository;

    public VendorTierServiceImpl(VendorTierRepository repository) {
        this.repository = repository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {

        if (tier.getMinScoreThreshold() < 0 ||
            tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }

        tier.setActive(true);
        return repository.save(tier);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }
}
