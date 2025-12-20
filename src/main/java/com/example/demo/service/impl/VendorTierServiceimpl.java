@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repository;

    public VendorTierServiceImpl(VendorTierRepository repository) {
        this.repository = repository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new ValidationException("between 0 and 100");
        }
        tier.setActive(true);
        return repository.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = getTierById(id);
        existing.setTierName(tier.getTierName());
        existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        return repository.save(existing);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return repository.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        repository.save(tier);
    }
}
