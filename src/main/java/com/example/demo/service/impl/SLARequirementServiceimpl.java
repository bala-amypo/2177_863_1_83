@Service
public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repository;

    public SLARequirementServiceImpl(SLARequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement req) {
        if (req.getDeliveryDays() <= 0 || req.getQualityScore() < 0 || req.getQualityScore() > 100) {
            throw new ValidationException("between 0 and 100");
        }
        return repository.save(req);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement req) {
        SLARequirement existing = getRequirementById(id);
        existing.setRequirementName(req.getRequirementName());
        existing.setDeliveryDays(req.getDeliveryDays());
        existing.setQualityScore(req.getQualityScore());
        return repository.save(existing);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return repository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement req = getRequirementById(id);
        req.setActive(false);
        repository.save(req);
    }
}
